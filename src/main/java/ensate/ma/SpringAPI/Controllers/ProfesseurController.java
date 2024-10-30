package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.DAO.*;
import ensate.ma.SpringAPI.Model.Candidature;
import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Model.Sujet;
import ensate.ma.SpringAPI.Repository.SujetRepo;
import ensate.ma.SpringAPI.Services.CandidatService;
import ensate.ma.SpringAPI.Services.ProfesseurService;
import ensate.ma.SpringAPI.Services.SujetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Professeur")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ProfesseurController {

  @Autowired
  private ProfesseurService professeurService;
  @Autowired
  private SujetService SujetService;
  @Autowired
  private SujetRepo sujetRepo;
  @Autowired
  private CandidatService candidatService;

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
  public ProfesseurDTO findProfesseurById(@PathVariable Long id) {
    Professeur professeur = professeurService.findProfesseurById(id);
    return new ProfesseurDTO(professeur.getNom(), professeur.getPrenom(), professeur.getEmail(), professeur.getCentre_id(), professeur.getStructureRecherche().getNom(), professeur.getStructureRecherche().getEtablissement(), professeur.getStructureRecherche().getId(), professeur.getStructureRecherche().getDomaine()

    );
  }

  @PutMapping("/update/{id}")
  public Professeur updateProfesseur(@PathVariable Long id, @RequestBody Professeur professeurDetails) {
    return professeurService.updateProfesseur(id, professeurDetails);
  }

  @PostMapping("/addSujet")
  public String addSujet(@RequestBody Sujet sujet) {
    SujetService.AddSujet(sujet);
    return "Sujet added successfully";
  }

  @PostMapping("/updateSujet/{id}")
  public String updateSujet(@PathVariable Long id, @RequestBody Sujet sujet) {
    SujetService.UpdateSujet(id, sujet);
    return "Sujet updated successfully";
  }

  @PostMapping("/deleteSujet/{id}")
  public String deleteSujet(@PathVariable Long id) {
    SujetService.DeleteSujet(id);
    return "sujet deleted successfully";
  }

  @GetMapping("/getSujetByProfId/{id}")
  public List<SujetDTO> getSujets(@PathVariable Long id) {
    try {
      return SujetService.getSujetByProfesseurId(id).stream().map(sujet -> new SujetDTO(sujet.getId(), sujet.getTitre(), sujet.getDescription(), sujet.getThematique())).collect(Collectors.toList());
    } catch (RuntimeException e) {
      log.error("Error fetching subjects for professor with id: " + id, e);
      return List.of();
    }
  }
  //list the entretien by professue id ( aka the candiddature preselectionner)

  @GetMapping("/entretien/{id}")
  public List<Candidature> getEntretien(@PathVariable Long id) {
    return professeurService.getEntretien(id);
  }

  //modify password professeur
  @GetMapping("/modifyPassword/{id}")
  public ResponseEntity<String> modifyPassword(@PathVariable Long id, @RequestBody PasswordChangeRequest passwordChangeRequest) {
    return ResponseEntity.ok().body(professeurService.ChangePassword(passwordChangeRequest.getPassword(), id));

  }

  // todo accepter ou refuser une candidature
  @GetMapping("/accepterCandidature/{id}")
public ResponseEntity<String> accepterCandidature(@PathVariable Long id, @RequestParam Date date) {
  return ResponseEntity.ok().body(professeurService.accepteCandidature(id, date));
}

  @GetMapping("/refuserCandidature/{id}")
  public ResponseEntity<String> refuserCandidature(@PathVariable Long id) {
    return ResponseEntity.ok().body(professeurService.refuseCandidature(id));
  }

  // get candidature by professeur id (aka the candidature that has statuts preselectionnee)
  @GetMapping("/candidature/{id}")
  public List<EntretienDTO> getCandidature(@PathVariable Long id) {
    return professeurService.getCandidatureByProfId(id);
  }

  //Candidature details (sujet + candidat details )by candidatureid
  @GetMapping("/candidatureDetails/{id}")
  public CandidatureDTO getCandidatureDetails(@PathVariable Long id) {
    return professeurService.getCandidatureDetails(id);
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

}
