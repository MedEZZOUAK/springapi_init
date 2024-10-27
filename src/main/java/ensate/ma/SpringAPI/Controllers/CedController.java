package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.DAO.ProfesseurDTO;
import ensate.ma.SpringAPI.Model.CED;
import ensate.ma.SpringAPI.DAO.CandidatureRequest;
import ensate.ma.SpringAPI.DAO.StructureRechercheDTO;
import ensate.ma.SpringAPI.DAO.SujetDTO;
import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Model.StructureRecherche;
import ensate.ma.SpringAPI.Model.Sujet;
import ensate.ma.SpringAPI.Repository.SujetRepo;
import ensate.ma.SpringAPI.Services.CedService;
import ensate.ma.SpringAPI.Services.ProfesseurService;
import ensate.ma.SpringAPI.Services.SujetService;
import ensate.ma.SpringAPI.Services.structureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/CED")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CedController {
  @Autowired
  private ProfesseurService professeurService;
  @Autowired
  private CedService cedService;
  @Autowired
  private SujetService sujetService;
  @Autowired
  private SujetRepo sujetRepo;
  @Autowired
  private final structureService structureService;

  @PostMapping("/addProfesseur")
  public String addProfesseur(@RequestBody Professeur professeur) {
    professeurService.AddProfesseur(professeur);
    return "Professeur added successfully";
  }

  @PostMapping("/addStructure")
  public String addStructure(@RequestBody StructureRecherche struct) {
    structureService.AddStructure(struct);
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
  @GetMapping("/structures/{cedId}")
  public ResponseEntity<?> getStructuresByCedId(@PathVariable Long cedId) {
    try {
      List<StructureRechercheDTO> structures = cedService.getStructuresByCedId(cedId);
      if (structures.isEmpty()) {
        return ResponseEntity.ok()
                .body(Map.of("message", "No structures found for CED with ID: " + cedId));
      }
      return ResponseEntity.ok(structures);
    } catch (Exception e) {
      log.error("Error fetching structures for CED with id: " + cedId, e);
      return ResponseEntity.badRequest()
              .body(Map.of("error", "Error fetching structures: " + e.getMessage()));
    }
  }

  // get sujet by structure id
  @GetMapping("/structure/{id}")
  public List<SujetDTO> getSujets(@PathVariable Long id) {
    try {
      return sujetService.getSujetByStructureId(id).stream()
              .map(sujet -> new SujetDTO(
                      sujet.getId(),
                      sujet.getTitre(),
                      sujet.getDescription(),
                      sujet.getThematique()
              ))
              .collect(Collectors.toList());
    } catch (RuntimeException e) {
      log.error("Error fetching subjects for professor with id: " + id, e);
      return List.of();
    }
  }

  // get candidature by ced id
  @GetMapping("/candidature/{id}")
  public ResponseEntity<List<CandidatureRequest>> getCandidatures(@PathVariable Long id) {
    return ResponseEntity.ok(cedService.getCandidaturesByCedId(id));
  }
  //Get all the professeur by ced id
  @GetMapping("/professeur/{id}")
  public List<ProfesseurDTO> getProfesseurByCedId(@PathVariable Long id) {
    return professeurService.getProfesseurByCedId(id);
  }

  // accept candidature
    @PostMapping("/acceptCandidature/{id}")
    public String acceptCandidature(@PathVariable Long id) {
      cedService.accepteCandidature(id);
      return "Candidature accepted successfully";
    }

    // reject candidature
    @PostMapping("/refuseCandidature/{id}")
    public String rejectCandidature(@PathVariable Long id) {
      cedService.refuseCandidature(id);
      return "Candidature rejected successfully";
    }

}
