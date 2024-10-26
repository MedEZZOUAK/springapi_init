package ensate.ma.SpringAPI.Services;

import ensate.ma.SpringAPI.DAO.ExperienceDTO;
import ensate.ma.SpringAPI.Model.*;
import ensate.ma.SpringAPI.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidatService {
  private final CandidatRepo candidatRepo;
  private final LangueRepo langueRepo;
  private final loginRepo loginRepo;
  @Autowired
  private final PasswordEncoder passwordEncoder;
  private final DiplomeRepo diplomeRepo;
  private final Experience experienceRepo;
  private final BourseRepo bourseRepo;
  private final CandidatureRepo candidatureRepo;

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
    Optional<Candidat> candidatOptional = candidatRepo.findById(id);
    if (candidatOptional.isEmpty()) {
      return null;
    }
    Candidat candidat = candidatOptional.get();

    // Update all fields
    candidat.setNom(candidatDetails.getNom());
    candidat.setPrenom(candidatDetails.getPrenom());
    candidat.setCin(candidatDetails.getCin());
    candidat.setTelephone(candidatDetails.getTelephone());
    candidat.setSituationFamiliale(candidatDetails.getSituationFamiliale());
    candidat.setNationalite(candidatDetails.getNationalite());
    candidat.setPrenomArabe(candidatDetails.getPrenomArabe());
    candidat.setNomArabe(candidatDetails.getNomArabe());
    candidat.setPayeNaissance(candidatDetails.getPayeNaissance());
    candidat.setAdresse(candidatDetails.getAdresse());
    candidat.setCodePostal(candidatDetails.getCodePostal());
    candidat.setHandicap(candidatDetails.getHandicap());
    candidat.setProfessionPere(candidatDetails.getProfessionPere());
    candidat.setProfessionMere(candidatDetails.getProfessionMere());
    candidat.setProvincePere(candidatDetails.getProvincePere());
    candidat.setProvinceMere(candidatDetails.getProvinceMere());
    candidat.setProfession(candidatDetails.getProfession());
    candidat.setCvScanne(candidatDetails.getCvScanne());
    candidat.setCinScanne(candidatDetails.getCinScanne());
    candidat.setDateNaissance(candidatDetails.getDateNaissance());
    candidat.setLangues(candidatDetails.getLangues());
    candidat.setDiplomes(candidatDetails.getDiplomes());
    candidat.setExperiences(candidatDetails.getExperiences());
    candidat.setCandidatures(candidatDetails.getCandidatures());

    return candidatRepo.save(candidat);
  }

  public String addLangue(Long id, List<Langue> langue) {
    //set candidat_id for each langue
    langue.forEach(lang -> lang.setCandidat(candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"))));
    //save all languages
    try {
      langueRepo.saveAll(langue);
      return "Langue added successfully";
    } catch (RuntimeException e) {
      return "Error while adding langue";
    }
  }

  public String ChangePassword(String newPassword, Long id) {
    var email = candidatRepo.findById(id).get().getEmail();
    //find the login by email
    var login = loginRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Login not found"));
    try {
      login.setPassword(passwordEncoder.encode(newPassword));
      loginRepo.save(login);
      return "Password changed successfully";
    } catch (RuntimeException e) {
      return "Error while changing password" + e.getMessage();
    }

  }

  public String addDiplome(Long id, List<Diplome> diplomes) {
    Optional<Candidat> candidat = candidatRepo.findById(id);
    if (candidat.isEmpty()) {
      return "Candidat not found";
    }
    //set candidat_id for each diplome
    diplomes.forEach(diplome -> diplome.setCandidat_id(Math.toIntExact(id)));
    //save all diplomes
    try {
      diplomeRepo.saveAll(diplomes);
      return "Diplome added successfully";
    } catch (RuntimeException e) {
      return "Error while adding diplome" + e.getMessage();
    }
  }

  public Candidat getCandidatById(Long id) {
    return candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));
  }

  public String resetPassword(Integer id) {
    //find the candidat
    try {
      var candidat = candidatRepo.findById(Long.valueOf(id));
      if (candidat.isPresent()) {
        var email = candidat.get().getEmail();
        //find the login by email
        var login = loginRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Login not found"));
        login.setPassword(passwordEncoder.encode("password"));
        loginRepo.save(login);
        return "Password reset successfully";
      } else {
        return "Candidat not found";
      }
    } catch (RuntimeException e) {
      return "Error while resetting password" + e.getMessage();
    }
  }

  public String addExperience(Long id, List<ExperienceDTO> experience) {
    Optional<Candidat> candidat = candidatRepo.findById(id);
    if (candidat.isEmpty()) {
      return "Candidat not found";
    }
    //set candidat_id for each experience
    experience.forEach(exp -> exp.setCandidat_id(Math.toIntExact(id)));
    //save all experiences
    try {
      //trandform experienceDTO to experience
      experience.forEach(exp -> {
        var experienceProf = new ensate.ma.SpringAPI.Model.ExperienceProf();
        experienceProf.setExperience(exp.getExperience());
        experienceProf.setEtablissement(exp.getEtablissement());
        experienceProf.setFonction(exp.getFonction());
        experienceProf.setSecteurActivite(exp.getSecteurActivite());
        experienceProf.setDateDebut(exp.getDateDebut());
        experienceProf.setDateFin(exp.getDateFin());
        experienceProf.setCandidat_id(exp.getCandidat_id());
        experienceRepo.save(experienceProf);
      });
      return "Experience added successfully";
    } catch (RuntimeException e) {
      return "Error while adding experience" + e.getMessage();
    }
  }

  // todo : demande bourse ( aka add bourse)
  public String demandeBourse(long id_Candidature) {
    // Find the candidature
    Candidature candidature = candidatureRepo.findById((int) id_Candidature).orElseThrow(() -> new RuntimeException("Candidature not found"));
    var bourse = Bourse.builder()
      .date(new java.sql.Date(System.currentTimeMillis()))
      .statut("En attente")
      .candidature(candidature)
      .build();
    try {
      bourseRepo.save(bourse);
      return "Bourse added successfully";
    } catch (RuntimeException e) {
      return "Error while adding bourse: " + e.getMessage();
    }
  }

  // todo :  add cv as a file
  public String addCv(Long id, MultipartFile cv) {
    try {
      var candidat = candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));
      candidat.setCvScanne(cv.getBytes());
      candidatRepo.save(candidat);
      return "Cv added successfully";
    } catch (RuntimeException | IOException e) {
      return "Error while adding cv" + e.getMessage();
    }
  }

  // todo :  add cin as a file  // do some research about how to add file in spring boot
  public String addCin(Long id, MultipartFile cin) {
    try {
      var candidat = candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));
      candidat.setCinScanne(cin.getBytes());
      candidatRepo.save(candidat);
      return "Cin added successfully";
    } catch (RuntimeException | IOException e) {
      return "Error while adding cin" + e.getMessage();
    }
  }

  // todo :  get all candidature by candidat id
  public List<Candidature> getCandidatureByCandidatId(Long id) {
    return candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found")).getCandidatures();
  }

  // todo : get Bourse by candidat id
  public List<Bourse> getBourseByCandidatId(Long id) {
    return null;
  }


  public byte[] getCv(Long id) {
    return candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found")).getCvScanne();
  }
  public String addBac(Long id ,MultipartFile file) {
    //get the id of the bac diploma using the id of the candidat

    Optional<Diplome> optional = Optional.ofNullable(diplomeRepo.findDiplomeByCandidat_idAndType(id, "Bac").orElseThrow(() -> new RuntimeException("Diplome not found")));
    if (optional.isEmpty()) {
      return "Diplome not found";
    }
    Diplome diplome = optional.get();
    try {
      diplome.setDiplomeScanne(file.getBytes());
      diplomeRepo.save(diplome);
      return "Bac added successfully";
    } catch (IOException e) {
      return "Error while adding bac" + e.getMessage();
    }
  }

  public String addLicence(Long id, MultipartFile lisencescanne, MultipartFile licensereleve) {
    //get the id of the bac diploma using the id of the candidat
    Diplome diplome = diplomeRepo.findDiplomeByCandidat_idAndType(id, "Licence").orElseThrow(() -> new RuntimeException("Diplome not found"));
    try {
      diplome.setDiplomeScanne(lisencescanne.getBytes());
      diplome.setRelevetNoteScanne(licensereleve.getBytes());
      diplomeRepo.save(diplome);
      return "Licence added successfully";
    } catch (IOException e) {
      return "Error while adding licence" + e.getMessage();
    }
  }

  public String addMaster(Long id, MultipartFile masterscanne, MultipartFile masterreleve) {
    //get the id of the bac diploma using the id of the candidat
    Diplome diplome = diplomeRepo.findDiplomeByCandidat_idAndType(id, "Master").orElseThrow(() -> new RuntimeException("Diplome not found"));
    try {
      diplome.setDiplomeScanne(masterscanne.getBytes());
      diplome.setRelevetNoteScanne(masterreleve.getBytes());
      diplomeRepo.save(diplome);
      return "Master added successfully";
    } catch (IOException e) {
      return "Error while adding master" + e.getMessage();
    }
  }
}

