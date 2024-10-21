package ensate.ma.SpringAPI.Services;

import ensate.ma.SpringAPI.Model.Candidat;
import ensate.ma.SpringAPI.Model.Diplome;
import ensate.ma.SpringAPI.Model.Langue;
import ensate.ma.SpringAPI.Repository.CandidatRepo;
import ensate.ma.SpringAPI.Repository.DiplomeRepo;
import ensate.ma.SpringAPI.Repository.LangueRepo;
import ensate.ma.SpringAPI.Repository.loginRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidatService {
    private final CandidatRepo candidatRepo;
    private final LangueRepo langueRepo;
    private final loginRepo loginRepo;
  @Autowired
  private final PasswordEncoder passwordEncoder;
  private final DiplomeRepo diplomeRepo;

    public List<Candidat> findAllCandidats() {
        return candidatRepo.findAll();
    }

    public void AddCandidat(Candidat candidat) {
        candidatRepo.save(candidat);
    }

    public void deleteCandidat(Long id) {
        candidatRepo.deleteById(id);
    }

    public Candidat findCandidatById(Long id) {
        return candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));
    }

    public Candidat updateCandidat(Long id, Candidat candidatDetails) {
        Candidat candidat = candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));
        candidat.setNom(candidatDetails.getNom());
        candidat.setPrenom(candidatDetails.getPrenom());
        candidat.setEmail(candidatDetails.getEmail());
        candidat.setCIN(candidatDetails.getCIN());
        candidat.setTelephone(candidatDetails.getTelephone());
        return candidatRepo.save(candidat);
    }

    public String addLangue(Long id, List<Langue> langue) {
      //set candidat_id for each langue
      langue.forEach(lang -> lang.setCandidat(candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"))));
      //save all languages
      try {
        langueRepo.saveAll(langue);
        return "Langue added successfully";
      }catch (RuntimeException e){
        return "Error while adding langue";
      }
    }
    public String ChangePassword(String newPassword , Long id) {
      var email= candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found")).getEmail();
      //find the login by email
      var login = loginRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Login not found"));
      try {
        login.setPassword(passwordEncoder.encode(newPassword));
        loginRepo.save(login);
        return "Password changed successfully";
      }catch (RuntimeException e){
        return "Error while changing password"+e.getMessage();
      }

    }
  public String addDiplome(Long id, List<Diplome> diplomes) {
    Candidat candidat = candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));
    diplomes.forEach(diplome -> diplome.setCandidat(candidat));
    try {
      diplomeRepo.saveAll(diplomes);
      return "Diplomes added successfully";
    } catch (RuntimeException e) {
      return "Error while adding diplomes: " + e.getMessage();
    }
  }
  public Candidat getCandidatById(Long id) {
    return candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));
  }
}

