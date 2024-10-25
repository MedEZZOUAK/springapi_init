package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Model.StructureRecherche;
import ensate.ma.SpringAPI.Services.CedService;
import ensate.ma.SpringAPI.Services.ProfesseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CED")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
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
  @PostMapping("/updateStructure/{id}")
  public String updateStructure(@PathVariable Long id, @RequestBody StructureRecherche struct) {
    try {
      StructureRecherche updatedStruct = cedService.updateStructureRecherche(id, struct); // Pass id here
      if (updatedStruct != null) {
        return "Structure updated successfully";
      } else {
        return "Structure not found";
      }
    } catch (Exception e) {
      log.error("Error updating structure", e);
      return "Error updating structure: " + e.getMessage();
    }
  }

  // TODO: 19/10/2024 delete the structure
    @DeleteMapping("/deleteStructure/{id}")
    public String deleteStructure(@PathVariable Long id) {
        try {
            cedService.deleteStructureRecherche(id);
            return "Structure deleted successfully";
        } catch (Exception e) {
            log.error("Error deleting structure", e);
            return "Error deleting structure: " + e.getMessage();
        }
    }
    // todo : Get all professeurs
    @GetMapping("/professeurs")
    public Iterable<Professeur> getProfesseurs() {
        return professeurService.getAllProfesseurs();
    }

    // todo get structure de recherche by id ced
}
