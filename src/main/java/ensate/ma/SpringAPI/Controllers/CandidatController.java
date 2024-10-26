package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.DAO.CEDDETAILS;
import ensate.ma.SpringAPI.DAO.Candidatdetails;
import ensate.ma.SpringAPI.DAO.ExperienceDTO;
import ensate.ma.SpringAPI.DAO.PasswordChangeRequest;
import ensate.ma.SpringAPI.Model.Candidat;
import ensate.ma.SpringAPI.Model.Diplome;
import ensate.ma.SpringAPI.Model.Langue;
import ensate.ma.SpringAPI.Repository.CandidatRepo;
import ensate.ma.SpringAPI.Repository.LangueRepo;
import ensate.ma.SpringAPI.Services.CandidatService;
import ensate.ma.SpringAPI.Services.CedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Candidat")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class CandidatController {

  @Autowired
  private CandidatService candidatService;
  @Autowired
  private LangueRepo LangueRepo;
  @Autowired
  private CandidatRepo candidatRepo;
  @Autowired
  private CedService cedService;

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
  public ResponseEntity<String> addDiplome(@PathVariable Long id, @RequestPart("diplome") Diplome diplome, @RequestPart("file1") MultipartFile file1, @RequestPart("file2") MultipartFile file2) {
    log.warn("diplome: " + diplome);
    log.warn("file1: " + file1);
    log.warn("file2: " + file2);
    return ResponseEntity.ok().body("test");
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

  @GetMapping("/alldetails/{id}")
  public ResponseEntity<Candidatdetails> getCandidatDetails(@PathVariable Long id) {
    Candidat candidat = candidatService.getCandidatById(id);
    Candidatdetails candidatdetails = new Candidatdetails();
    candidatdetails.setNom(candidat.getNom());
    candidatdetails.setPrenom(candidat.getPrenom());
    candidatdetails.setEmail(candidat.getEmail());
    candidatdetails.setTelephone(candidat.getTelephone());
    candidatdetails.setCin(candidat.getCin());
    candidatdetails.setCandidatures(candidat.getCandidatures());
    candidatdetails.setDiplomes(candidat.getDiplomes());
    candidatdetails.setExperiences(candidat.getExperiences());
    candidatdetails.setLangues(candidat.getLangue());
    return ResponseEntity.ok().body(candidatdetails);
  }

  @PostMapping("/addDocuments/{id}")
public ResponseEntity<String> addDocuments(@PathVariable Long id, @RequestPart("cv") MultipartFile cv, @RequestPart("cin") MultipartFile cin) {
  candidatService.addCv(id, cv);
  candidatService.addCin(id, cin);
  return ResponseEntity.ok().body("Documents added successfully");
}

  @GetMapping("/getcv/{id}")
  public ResponseEntity<byte[]> getCv(@PathVariable Long id) {
    byte[] cv = candidatService.getCv(id);
    return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=cv.pdf").contentType(MediaType.APPLICATION_PDF).body(cv);
  }

  @GetMapping("/GetCED")
  public ResponseEntity<List<CEDDETAILS>> getCED() {
    List<CEDDETAILS> ceddetails = cedService.getallCEDinfos();
    return ResponseEntity.ok().body(ceddetails);
  }
  //upload Documents
  @PostMapping("/AdddiplomesFiles/{id}")
  public ResponseEntity<String> addDiplomeFiles(@PathVariable Long id, @RequestPart("bacscanne") MultipartFile bacscanne, @RequestPart("lisencescanne") MultipartFile lisencescanne, @RequestPart("masterscanne") MultipartFile masterscanne, @RequestPart("licensereleve") MultipartFile licensereleve, @RequestPart("masterreleve") MultipartFile masterreleve) {
    ;
    String message = candidatService.addBac(id, bacscanne) + " /n  " + candidatService.addLicence(id, lisencescanne, licensereleve) + " " + candidatService.addMaster(id, masterscanne, masterreleve);

    return ResponseEntity.ok().body(message);
  }




}

