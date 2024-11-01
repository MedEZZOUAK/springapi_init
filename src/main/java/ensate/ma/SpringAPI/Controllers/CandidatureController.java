package ensate.ma.SpringAPI.Controllers;


import ensate.ma.SpringAPI.DAO.CandidatureRequest;
import ensate.ma.SpringAPI.Model.Candidature;
import ensate.ma.SpringAPI.Model.Statuts;
import ensate.ma.SpringAPI.Repository.CandidatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/Candidature")
@CrossOrigin(origins = "${link_front}")
public class CandidatureController {
  // todo add candidature
  // todo delete candidature
  // todo preview candidature
  @Autowired
  private final CandidatureRepo candidatureRepo;

  public CandidatureController(CandidatureRepo candidatureRepo) {
    this.candidatureRepo = candidatureRepo;
  }

  @PostMapping("/add")
  public ResponseEntity<String> addCandidature(@RequestBody CandidatureRequest request) {
    if (request.getDate() == null || request.getDate().isEmpty()) {
      return new ResponseEntity<>("Date cannot be null or empty", HttpStatus.BAD_REQUEST);
    }

    Date date;
    try {
      date = Date.valueOf(request.getDate());
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>("Invalid date format", HttpStatus.BAD_REQUEST);
    }

    Candidature candidature = Candidature.builder()
            .statuts(Statuts.Encours)
            .date(date)
            .Sujet_id(request.getSujet_id())
            .Candidat_id(request.getCandidat_id())
            .build();

    candidatureRepo.save(candidature);
    return ResponseEntity.ok("Candidature added successfully");
  }

  @PostMapping("/delete")
  public ResponseEntity<String> deleteCandidature(Long id) {
    candidatureRepo.deleteById(Math.toIntExact(id));
    return ResponseEntity.ok("Candidature deleted successfully");
  }

  @PostMapping("/preview")
  public ResponseEntity<Candidature> findCandidatureById(Long id) {
    var candidature = candidatureRepo.findById(Math.toIntExact(id));
    return ResponseEntity.ok(candidature.orElse(null));
  }


}
