package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.DAO.Candidatdetails;
import ensate.ma.SpringAPI.DAO.ExperienceDTO;
import ensate.ma.SpringAPI.DAO.PasswordChangeRequest;
import ensate.ma.SpringAPI.Model.Candidat;
import ensate.ma.SpringAPI.Model.Diplome;
import ensate.ma.SpringAPI.Model.Langue;
import ensate.ma.SpringAPI.Repository.CandidatRepo;
import ensate.ma.SpringAPI.Repository.LangueRepo;
import ensate.ma.SpringAPI.Services.CandidatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Candidat")
@Slf4j

public class CandidatController {

      @Autowired
      private CandidatService candidatService ;
      @Autowired
        private LangueRepo LangueRepo;
  @Autowired
  private CandidatRepo candidatRepo;

  @PostMapping("/delete/{id}")
      public String deleteCandidat(@PathVariable Long id) {
     candidatService.deleteCandidat(id);
     return "Candidat deleted successfully";
      }

      @GetMapping("/all")
      public List<Candidat> findAllCandidats() {
     return candidatService.findAllCandidats();
      }

      @GetMapping("/find/{id}")
        public Candidat findCandidatById(@PathVariable Long id) {
            return candidatService.findCandidatById(id);
        }

        @PutMapping("/update/{id}")
        public Candidat updateCandidat(@PathVariable Long id, @RequestBody Candidat candidatDetails) {
            return candidatService.updateCandidat(id, candidatDetails);
        }

        // add langues to a candidat
        @PostMapping("/addLangue/{id}")
        public ResponseEntity addLangue(@PathVariable Long id, @RequestBody List<Langue> langues) {
            candidatService.addLangue(id, langues);
            return ResponseEntity.ok().body("Langues added successfully");
        }

        // todo add diplome to a candidat
        @PostMapping("/addDiplome/{id}")
          public ResponseEntity<String> addDiplome(@PathVariable Long id, @RequestBody List<Diplome> diplomes) {
              String response = candidatService.addDiplome(id, diplomes);
              return ResponseEntity.ok().body(response);
        }


        // todo modify password
        @PostMapping("/modifyPassword/{id}")
        public ResponseEntity modifyPassword(@PathVariable Long id, @RequestBody PasswordChangeRequest passwordChangeRequest) {
          String password = passwordChangeRequest.getPassword();
          log.info("password: " + password);
          return ResponseEntity.ok().body(candidatService.ChangePassword(password, id));
        }

  @GetMapping("/getinfo/{id}")
  public ResponseEntity<Candidatdetails> getCandidatInfo(@PathVariable Long id) {
    Candidat candidat = candidatService.getCandidatById(id);
    Candidatdetails candidatdetails = new Candidatdetails();
    candidatdetails.setNom(candidat.getNom());
    candidatdetails.setPrenom(candidat.getPrenom());
    candidatdetails.setEmail(candidat.getEmail());
    candidatdetails.setTelephone(candidat.getTelephone());
    candidatdetails.setCin(candidat.getCin());
    candidatdetails.setCandidatures(candidat.getCandidatures());
    return ResponseEntity.ok().body(candidatdetails);

  }

  @GetMapping("/addExperience/{id}")
  public ResponseEntity<String> addExperience(@PathVariable Long id, @RequestBody List<ExperienceDTO> experience) {
    return ResponseEntity.ok().body(candidatService.addExperience(id, experience));
  }



}

