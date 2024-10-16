package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Services.ProfesseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Professeur")
@Slf4j
public class ProfesseurController {

  @Autowired
  private ProfesseurService professeurService ;

  //add a new Professeur
  @PostMapping("/add")
  public String addProfesseur(@RequestBody Professeur professeur) {
    //check if the professeur if the data is valid
    log.info("Professeur data is being validated");
    //print the data of the professeur
    log.info(professeur.toString());
    if (professeur.getNom() == null || professeur.getPrenom() == null || professeur.getEmail() == null ) {
      log.error("Professeur data is not valid");
      return "Professeur data is not valid";
    }
    log.info("Professeur data is valid");
    professeurService.AddProfesseur(professeur);
    return "Professeur added successfully";
  }
  @PostMapping("/delete/{id}")
  public String deleteProfesseur(@PathVariable Long id) {
    professeurService.deleteProfesseur(id);
    return "Professeur deleted successfully";
  }
  @GetMapping("/all")
  public List<Professeur> findAllProfesseurs() {
    return professeurService.findAllProfesseurs();
  }

  @GetMapping ("/test")
  public ResponseEntity<String> testsecurity() {
    return ResponseEntity.ok("Security is working");
  }





}
