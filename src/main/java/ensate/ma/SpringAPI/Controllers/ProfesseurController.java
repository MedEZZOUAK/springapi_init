package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Services.ProfesseurService;
import ensate.ma.SpringAPI.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Professeur")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ProfesseurController {

  @Autowired
  private ProfesseurService professeurService ;

  @PostMapping("/delete/{id}")
  public String deleteProfesseur(@PathVariable Long id) {
    professeurService.deleteProfesseur(id);
    return "Professeur deleted successfully";
  }
  @GetMapping("/all")
  public List<Professeur> findAllProfesseurs() {
    return professeurService.findAllProfesseurs();
  }

  @GetMapping("/find/{id}")
    public Professeur findProfesseurById(@PathVariable Long id) {
        return professeurService.findProfesseurById(id);
    }

    @PutMapping("/update/{id}")
    public Professeur updateProfesseur(@PathVariable Long id, @RequestBody Professeur professeurDetails) {
        return professeurService.updateProfesseur(id, professeurDetails);
    }





}
