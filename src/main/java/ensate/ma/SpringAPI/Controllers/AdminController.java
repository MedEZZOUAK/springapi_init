package ensate.ma.SpringAPI.Controllers;


import ensate.ma.SpringAPI.Model.CED;
import ensate.ma.SpringAPI.Services.AdminService;
import ensate.ma.SpringAPI.Services.CandidatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restricted")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
  @Autowired
  private  AdminService cedService;
  @Autowired
  private  CandidatService candidatService;

  // crud for the ced
  @PostMapping("/addCed")
  public String addCed(@RequestBody CED ced) {
    cedService.AddCed(ced);
    return "Ced added successfully";
  }

  // todo : modify the ced
  @PostMapping("/updateCed/{id}")
  public String updateCed(@PathVariable Long id, @RequestBody CED ced) {
    cedService.updateCed(id, ced);
    return "Ced updated successfully";
  }

  // todo reset password for the professeur
  @PostMapping("/resetPasswordProf/{id}")
  public String resetPassword(@PathVariable Integer id) {
    cedService.resetPassword(id);
    return "Password reset successfully";
  }

  //  todo : reset password for the candidat
  @PostMapping("/resetPasswordCand/{id}")
  public String resetPasswordCandidact(@PathVariable Integer id) {
    return candidatService.resetPassword(id);
  }


}
