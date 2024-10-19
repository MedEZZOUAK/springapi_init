package ensate.ma.SpringAPI.Services;


import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Repository.ProfesseurRepo;
import ensate.ma.SpringAPI.config.AppConfig;
import ensate.ma.SpringAPI.user.Role;
import ensate.ma.SpringAPI.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ensate.ma.SpringAPI.Repository.loginRepo;

import java.util.List;

@Service
public class ProfesseurService {
  private final ProfesseurRepo professeurRepo;
  @Autowired
  private final loginRepo loginRepo;
  @Autowired
  private final PasswordEncoder passwordEncoder;

  public ProfesseurService(ProfesseurRepo professeurRepo, ensate.ma.SpringAPI.Repository.loginRepo loginRepo, PasswordEncoder passwordEncoder) {
    this.professeurRepo = professeurRepo;
    this.loginRepo = loginRepo;
    this.passwordEncoder = passwordEncoder;
  }


  public List<Professeur> findAllProfesseurs() {
    return professeurRepo.findAll();
  }

  public void AddProfesseur(Professeur professeur) {
    String password = "Welcome123";
    var login= User.builder()
      .email(professeur.getEmail())
      .password(passwordEncoder.encode(password))
      .role(Role.Professeur)
      .build();
    loginRepo.save(login);
    professeurRepo.save(professeur);
  }

  public  void deleteProfesseur(Long id) {
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


}
