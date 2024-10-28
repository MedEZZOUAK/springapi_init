package ensate.ma.SpringAPI.Services;


import ensate.ma.SpringAPI.DAO.EntretienDTO;
import ensate.ma.SpringAPI.DAO.ProfesseurDTO;
import ensate.ma.SpringAPI.Exception.CandidatureNotFoundException;
import ensate.ma.SpringAPI.Model.Candidature;
import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Model.Statuts;
import ensate.ma.SpringAPI.Repository.CandidatureRepo;
import ensate.ma.SpringAPI.Repository.ProfesseurRepo;
import ensate.ma.SpringAPI.Repository.loginRepo;
import ensate.ma.SpringAPI.user.Role;
import ensate.ma.SpringAPI.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void accepteCandidature(Long id) {
      Candidature candidature = candidatureRepo.findById(id)
        .orElseThrow(() -> new CandidatureNotFoundException("Candidature with id " + id + " not found"));

      candidature.setStatuts(Statuts.Acceptee);
      candidatureRepo.save(candidature);
    }
  // todo : refuse candidature
  public void refuseCandidature(Long id) {
    Candidature candidature = candidatureRepo.findById(id)
      .orElseThrow(() -> new CandidatureNotFoundException("Candidature with id " + id + " not found"));

    candidature.setStatuts(Statuts.Refusee);
    candidatureRepo.save(candidature);
  }

  public String accepterCandidature(Long id) {
    try {
      var Candidature = candidatureRepo.findById(id).get();
      Candidature.setStatuts(Statuts.Acceptee);
      candidatureRepo.save(Candidature);
      return "Candidature accepted successfully";
      } catch (RuntimeException e) {
      return "Error while accepting candidature" + e.getMessage();}
  }
  public String refuserCandidature(Long id) {
    try {
      var Candidature = candidatureRepo.findById(id).get();
      Candidature.setStatuts(Statuts.Refusee);
      candidatureRepo.save(Candidature);
      return "Candidature Rejete successfully";
    } catch (RuntimeException e) {
      return "Error while refusing candidature" + e.getMessage();}
  }

  public List<EntretienDTO> getEntretienList(Long id) {
    List<Candidature> candidatures = candidatureRepo.findByProfesseurId(id);
    List<EntretienDTO> result = new ArrayList<>();
    for (Candidature candidature : candidatures) {
      if (candidature.getStatuts().equals(Statuts.preselectionnee)) {
        result.add(EntretienDTO.builder()
            .nomCandidat(candidature.getCandidat().getNom()+" "+candidature.getCandidat().getPrenom())
            .nomSujet(candidature.getSujet().getTitre())
            .nomEtablissement(candidature.getSujet().getStructureRecherche().getEtablissement())
            .id_candidature(candidature.getId())
          .build());
      }
    }
    return result;
  }
  //todo  get all candidature by prof id


}
