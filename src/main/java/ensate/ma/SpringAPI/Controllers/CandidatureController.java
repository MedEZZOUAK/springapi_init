package ensate.ma.SpringAPI.Controllers;


import ensate.ma.SpringAPI.DAO.CandidatureRequest;
import ensate.ma.SpringAPI.Model.Candidature;
import ensate.ma.SpringAPI.Repository.CandidatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/Candidature")
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
  public ResponseEntity<String> addCandidature(CandidatureRequest candidatureRequest) {
    var candidature = Candidature.builder()
      .Statuts(candidatureRequest.getStatuts())
      .date(Date.valueOf(candidatureRequest.getDate()))
      .Sujet_id(candidatureRequest.getSujet_id())
      .Candidat_id(candidatureRequest.getCandidat_id())
      .build();
    //save the candidature
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
