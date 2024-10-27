package ensate.ma.SpringAPI.Services;


import ensate.ma.SpringAPI.DAO.ProfesseurDTO;
import ensate.ma.SpringAPI.Model.Candidature;
import ensate.ma.SpringAPI.Model.Professeur;
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


  //todo  accepte and refuse candidature
  //todo  get all candidature by prof id


}
