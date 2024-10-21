package ensate.ma.SpringAPI.Controllers;


import ensate.ma.SpringAPI.Model.CED;
import ensate.ma.SpringAPI.Services.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restricted")
@Slf4j

public class AdminController {
    private final AdminService cedService;

    public AdminController(AdminService cedService) {
        this.cedService = cedService;
    }

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
    @PostMapping("/resetPassword/{id}")
    public String resetPassword(@PathVariable Integer id) {
        cedService.resetPassword(id);
        return "Password reset successfully";
    }
    //  todo : reset password for the candidat


}
