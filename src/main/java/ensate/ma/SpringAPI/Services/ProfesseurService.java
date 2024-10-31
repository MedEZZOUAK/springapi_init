package ensate.ma.SpringAPI.Services;


import ensate.ma.SpringAPI.DAO.*;
import ensate.ma.SpringAPI.Exception.CandidatureNotFoundException;
import ensate.ma.SpringAPI.Model.Candidature;
import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Model.Statuts;
import ensate.ma.SpringAPI.Repository.*;
import ensate.ma.SpringAPI.user.Role;
import ensate.ma.SpringAPI.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesseurService {
  @Autowired
  private final loginRepo loginRepo;
  @Autowired
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private ProfesseurRepo professeurRepo;
  @Autowired
  private CandidatureRepo candidatureRepo;
  @Autowired
  private SujetRepo sujetRepo;
  @Autowired
  private CandidatRepo candidatRepo;

  public List<Professeur> findAllProfesseurs() {
    return professeurRepo.findAll();
  }

  public void AddProfesseur(Professeur professeur) {
    // Check if email already exists in login repository
    if (loginRepo.findByEmail(professeur.getEmail()).isPresent()) {
      throw new IllegalStateException("Email " + professeur.getEmail() + " already taken");
    }

    // Check if email already exists in professeur repository
    if (professeurRepo.findByEmail(professeur.getEmail()).isPresent()) {
      throw new IllegalStateException("Email " + professeur.getEmail() + " already taken");
    }

    // If email is unique, proceed with user creation
    String password = "Welcome123";
    var login = User.builder().email(professeur.getEmail()).password(passwordEncoder.encode(password)).role(Role.Professeur).build();
    loginRepo.save(login);
    professeurRepo.save(professeur);
  }

  public void deleteProfesseur(Long id) {
    professeurRepo.deleteById(id);
  }

  public Professeur findProfesseurById(Long id) {
    return professeurRepo.findById(id).orElseThrow(() -> new RuntimeException("Professeur not found"));
  }

  public Professeur updateProfesseur(Long id, Professeur professeurDetails) {
    Professeur professeur = professeurRepo.findById(id).orElseThrow(() -> new RuntimeException("Professeur not found"));
    professeur.setNom(professeurDetails.getNom());
    professeur.setPrenom(professeurDetails.getPrenom());
    professeur.setStructureRecherche(professeurDetails.getStructureRecherche());
    professeur.setSujets(professeurDetails.getSujets());
    return professeurRepo.save(professeur);
  }

  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  public List<Professeur> getAllProfesseurs() {
    return professeurRepo.findAll();
  }

  // get professeur by ced id
  public List<ProfesseurDTO> getProfesseurByCedId(Long id) {
    return professeurRepo.findByCedId(id);
  }

  public List<Candidature> getEntretien(Long id) {
    //find all the candidature with status entretien and prof id
    return candidatureRepo.findByProfesseurIdAndStatus(id, "entretien");
  }

  public String ChangePassword(String password, Long id) {
    var email = professeurRepo.findById(id).get().getEmail();
    //find the login by email
    var login = loginRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Login not found"));
    try {
      login.setPassword(passwordEncoder.encode(password));
      loginRepo.save(login);
      return "Password changed successfully";
    } catch (RuntimeException e) {
      return "Error while changing password" + e.getMessage();
    }
  }


  //todo  accepte and refuse candidature
  public String accepteCandidature(Long id, Date date) {
    Candidature candidature = candidatureRepo.findById(id).orElseThrow(() -> new CandidatureNotFoundException("Candidature with id " + id + " not found"));
    candidature.setStatuts(Statuts.Acceptee);
    candidature.setDate(date);
    candidatureRepo.save(candidature);
    return "Candidature accepted successfully";
  }

  // todo : refuse candidature
  public String refuseCandidature(Long id) {
    Candidature candidature = candidatureRepo.findById(id).orElseThrow(() -> new CandidatureNotFoundException("Candidature with id " + id + " not found"));

    candidature.setStatuts(Statuts.Refusee);
    candidatureRepo.save(candidature);
    return "Candidature refused successfully";
  }
  //todo  get all candidature by prof id

  public List<EntretienDTO> getCandidatureByProfId(Long id) {
    return candidatureRepo.findCandidaturesByProfesseurIdAndStatusPreselectionnee(id);
  }


  public CandidatureDTO getCandidatureDetails(Long id) {
    //get candiature by id
    var candidature = candidatureRepo.findById(id).orElseThrow(() -> new CandidatureNotFoundException("Candidature with id " + id + " not found"));
    var Candidat = candidatRepo.findById(Long.valueOf(candidature.getCandidat_id())).orElseThrow(() -> new CandidatureNotFoundException("Candidat with id " + candidature.getCandidat_id() + " not found"));
    var sujet = sujetRepo.findById(Long.valueOf(candidature.getSujet_id())).orElseThrow(() -> new CandidatureNotFoundException("Sujet with id " + candidature.getSujet_id() + " not found"));
    //Build the candidatureDTO
    return CandidatureDTO.builder().candidatdetails(Candidatdetails.builder().nom(Candidat.getNom()).prenom(Candidat.getPrenom()).email(Candidat.getEmail()).cin(Candidat.getCin()).telephone(Candidat.getTelephone()).situationFamiliale(Candidat.getSituationFamiliale()).nationalite(Candidat.getNationalite()).prenomArabe(Candidat.getPrenomArabe()).nomArabe(Candidat.getNomArabe()).payeNaissance(Candidat.getPayeNaissance()).adresse(Candidat.getAdresse()).codePostal(Candidat.getCodePostal()).handicap(Candidat.getHandicap()).professionPere(Candidat.getProfessionPere()).professionMere(Candidat.getProfessionMere()).provincePere(Candidat.getProvincePere()).provinceMere(Candidat.getProvinceMere()).profession(Candidat.getProfession()).dateNaissance(Candidat.getDateNaissance()).candidat_id(Candidat.getId()).langues(Candidat.getLangues()).diplomes(Candidat.getDiplomes()).experiences(Candidat.getExperiences()).build()).sujet(SujetDTO.builder().id(sujet.getId()).titre(sujet.getTitre()).description(sujet.getDescription()).thematique(sujet.getThematique()).build()).build();
  }

  // get candidature with statuts Acceptee and by prof id
    public List<DTOgene> getCandidatureAccepteeByProfId(Long id) {
        return candidatureRepo.findCandidaturesByProfesseurIdAndStatusAcceptee(id);
    }
}
