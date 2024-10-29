package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.DAO.*;
import ensate.ma.SpringAPI.Model.*;
import ensate.ma.SpringAPI.Repository.CandidatRepo;
import ensate.ma.SpringAPI.Repository.CandidatureRepo;
import ensate.ma.SpringAPI.Repository.LangueRepo;
import ensate.ma.SpringAPI.Services.CandidatService;
import ensate.ma.SpringAPI.Services.CedService;
import ensate.ma.SpringAPI.Services.SujetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

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
  @Autowired
  private SujetService sujetService;
  @Autowired
  private CandidatureRepo candidatureRepo;

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

  // add langues to a candidat
  @PostMapping("/addLangue/{id}")
  public ResponseEntity addLangue(@PathVariable Long id, @RequestBody List<Langue> langues) {
    candidatService.addLangue(id, langues);
    return ResponseEntity.ok().body("Langues added successfully");
  }

  // todo add diplome to a candidat list of diplomes
  @PostMapping("/addDiplome/{id}")
  public ResponseEntity<String> addDiplome(@PathVariable Long id, @RequestBody List<Diplome> diplomes) {
    return ResponseEntity.ok().body(candidatService.addDiplome(id, diplomes));
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

  @PostMapping("/addExperience/{id}")
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
    candidatdetails.setLangues(candidat.getLangues());
    candidatdetails.setSituationFamiliale(candidat.getSituationFamiliale());
    candidatdetails.setNationalite(candidat.getNationalite());
    candidatdetails.setPrenomArabe(candidat.getPrenomArabe());
    candidatdetails.setNomArabe(candidat.getNomArabe());
    candidatdetails.setPayeNaissance(candidat.getPayeNaissance());
    candidatdetails.setAdresse(candidat.getAdresse());
    candidatdetails.setCodePostal(candidat.getCodePostal());
    candidatdetails.setHandicap(candidat.getHandicap());
    candidatdetails.setProfessionPere(candidat.getProfessionPere());
    candidatdetails.setProfessionMere(candidat.getProfessionMere());
    candidatdetails.setProvincePere(candidat.getProvincePere());
    candidatdetails.setProvinceMere(candidat.getProvinceMere());
    candidatdetails.setProfession(candidat.getProfession());
    candidatdetails.setDateNaissance(candidat.getDateNaissance());
    return ResponseEntity.ok().body(candidatdetails);
  }

  @PostMapping("/addDocuments/{id}")
  public ResponseEntity<String> addDocuments(@PathVariable Long id, @RequestPart("cv") MultipartFile cv, @RequestPart("cin") MultipartFile cin, @RequestPart("photo") MultipartFile photo) {

    return ResponseEntity.ok().body(candidatService.addCv(id, cv) + " \n" + candidatService.addCin(id, cin) + " \n" + candidatService.addPhoto(id, photo));
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
    String message = candidatService.addBac(id, bacscanne) + " /n  " + candidatService.addLicence(id, lisencescanne, licensereleve) + " " + candidatService.addMaster(id, masterscanne, masterreleve);

    return ResponseEntity.ok().body(message);
  }

  @PostMapping("/addBourse/{id}")
  public ResponseEntity<String> addBourse(@PathVariable Integer id) {
    return ResponseEntity.ok().body(candidatService.demandeBourse(id));
  }

  // add details to a candidat
  @PostMapping("/addDetails/{id}")
  public ResponseEntity<Candidat> addDetails(@PathVariable Long id, @RequestBody AddCandidatedetailsRequest candidatdetails) {
    return candidatService.addDetails(id, candidatdetails);
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

  @PostMapping("/addCandidature")
  public ResponseEntity<String> addCandidature(@RequestBody CandidatureRequest request) {

    Candidature candidature = Candidature.builder()
      .statuts(Statuts.Encours)
      .date(null)
      .Sujet_id(request.getSujet_id())
      .Candidat_id(request.getCandidat_id())
      .build();

    candidatureRepo.save(candidature);
    return ResponseEntity.ok("Candidature added successfully");
  }

  @PostMapping("/deleteCandidature/{id}")
  public ResponseEntity<String> deleteCandidature(@PathVariable Long id) {
    candidatureRepo.deleteById(Math.toIntExact(id));
    return ResponseEntity.ok("Candidature deleted successfully");
  }

  // Get all candidatures by candidat id
  @GetMapping("/getCandidatures/{id}")
  public ResponseEntity<List<CandidaturesCandidatId>> getCandidatures(@PathVariable Long id) {
    List<CandidaturesCandidatId> candidatures = candidatService.getCandidatures(id);
    return ResponseEntity.ok().body(candidatures);
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


}

