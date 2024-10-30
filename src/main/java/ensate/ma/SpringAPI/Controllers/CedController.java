package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.DAO.*;
import ensate.ma.SpringAPI.Model.*;
import ensate.ma.SpringAPI.Repository.SujetRepo;
import ensate.ma.SpringAPI.Services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

  @Autowired
  private CandidatService candidatService;

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

  // get candidature by ced id (statuts en cours)
  @GetMapping("/candidature/{id}")
  public ResponseEntity<List<EntretienDTO>> getCandidatures(@PathVariable Long id) {
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

    // accept bourse
    @PostMapping("/acceptBourse/{id}")
    public String acceptBourse(@PathVariable Integer id) {
      cedService.accepteBourse(id);
      return "Bourse accepted successfully";
    }

    // reject bourse
    @PostMapping("/refuseBourse/{id}")
    public String rejectBourse(@PathVariable Integer id) {
      cedService.refuseBourse(id);
      return "Bourse rejected successfully";
    }

    // get bourse by ced id
    @GetMapping("/bourses/{cedId}")
    public List<BourseDTO> getBoursesByCedId(@PathVariable Integer cedId) {
      return cedService.getAllBoursesByCedId(cedId);
    }
    //candidat details by candidature id
    @GetMapping("/candidat/{id}")
    public Candidatdetails getCandidatByCandidatureId(@PathVariable Long id) {
      return cedService.getCandidatByCandidatureId(id);
    }
  @GetMapping("/GetDiplomesBacFiles/{id}")
  public ResponseEntity<byte[]> getDiplomeFiles(@PathVariable Long id) {
    byte[] bac = candidatService.getBac(id);
    return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=bac.pdf").contentType(MediaType.APPLICATION_PDF).body(bac);
  }

  @GetMapping("/GetDiplomesLicenceFiles/{id}")
  public ResponseEntity<byte[]> getLicenceFiles(@PathVariable Long id) {
    byte[] licence = candidatService.getLicence(id);
    return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=licence.pdf").contentType(MediaType.APPLICATION_PDF).body(licence);
  }

  @GetMapping("/GetDiplomesMasterFiles/{id}")
  public ResponseEntity<byte[]> getMasterFiles(@PathVariable Long id) {
    byte[] master = candidatService.getMaster(id);
    return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=master.pdf").contentType(MediaType.APPLICATION_PDF).body(master);
  }

  @GetMapping("/GetDiplomesLicenceReleveFiles/{id}")
  public ResponseEntity<byte[]> getLicenceReleveFiles(@PathVariable Long id) {
    byte[] licence = candidatService.getLicenceReleve(id);
    return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=licencereleve.pdf").contentType(MediaType.APPLICATION_PDF).body(licence);
  }

  @GetMapping("/GetDiplomesMasterReleveFiles/{id}")
  public ResponseEntity<byte[]> getMasterReleveFiles(@PathVariable Long id) {
    byte[] master = candidatService.getMasterReleve(id);
    return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=masterreleve.pdf").contentType(MediaType.APPLICATION_PDF).body(master);
  }
  // Get CIN
  @GetMapping("/getCin/{id}")
  public ResponseEntity<byte[]> getCin(@PathVariable Long id) {
    byte[] cin = candidatService.getCin(id);
    return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=cin.pdf").contentType(MediaType.APPLICATION_PDF).body(cin);
  }

  // Get Photo
  @GetMapping("/getPhoto/{id}")
  public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) {
    byte[] photo = candidatService.getPhoto(id);
    return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=photo.jpg").contentType(MediaType.IMAGE_JPEG).body(photo);
  }
  @GetMapping("/getcv/{id}")
  public ResponseEntity<byte[]> getCv(@PathVariable Long id) {
    byte[] cv = candidatService.getCv(id);
    return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=cv.pdf").contentType(MediaType.APPLICATION_PDF).body(cv);
  }
  //Candidature details (sujet + candidat details )by candidatureid
  @GetMapping("/candidatureDetails/{id}")
  public CandidatureDTO getCandidatureDetails(@PathVariable Long id) {
    return professeurService.getCandidatureDetails(id);
  }


}
