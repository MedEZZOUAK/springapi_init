package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.Model.Candidat;
import ensate.ma.SpringAPI.Model.Langue;
import ensate.ma.SpringAPI.Services.CandidatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Candidat")
@Slf4j

public class CandidatController {

      @Autowired
      private CandidatService candidatService ;

      //add a new Candidat
      @PostMapping("/add")

      public String addCandidat(@RequestBody Candidat candidat) {
     //check if the candidat if the data is valid
     log.info("Candidat data is being validated");
     //print the data of the candidat
     log.info(candidat.toString());
     if (candidat.getNom() == null || candidat.getPrenom() == null || candidat.getEmail() == null ) {
        log.error("Candidat data is not valid");
        return "Candidat data is not valid";
     }
     log.info("Candidat data is valid");
     candidatService.AddCandidat(candidat);
     return "Candidat added successfully";
      }


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
        public Candidat addLangue(@PathVariable Long id, @RequestBody List<Langue> langues) {
            return candidatService.addLangue(id, langues);
        }
}
