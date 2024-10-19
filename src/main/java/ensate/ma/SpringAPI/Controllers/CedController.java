package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Services.ProfesseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CED")
@Slf4j
public class CedController {
  @Autowired
  private ProfesseurService professeurService;

  @PostMapping("/addProfesseur")
  public String addProfesseur(@RequestBody Professeur professeur) {

    professeurService.AddProfesseur(professeur);

    return "Professeur added successfully";
  }

}
