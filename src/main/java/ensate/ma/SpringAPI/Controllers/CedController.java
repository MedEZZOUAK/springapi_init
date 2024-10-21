package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Model.StructureRecherche;
import ensate.ma.SpringAPI.Services.CedService;
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
  @Autowired
    private CedService cedService;

  @PostMapping("/addProfesseur")
  public String addProfesseur(@RequestBody Professeur professeur) {

    professeurService.AddProfesseur(professeur);

    return "Professeur added successfully";
  }

  @PostMapping("/addStructure")
    public String addStructure(@RequestBody StructureRecherche struct) {
        cedService.AddCed(struct);
        return "Structure added successfully";
    }
  // TODO: 19/10/2024 update the structure
  // TODO: 19/10/2024 delete the structure
  // todo : modify the ced
  // todo reset password for the professeur
  //

}