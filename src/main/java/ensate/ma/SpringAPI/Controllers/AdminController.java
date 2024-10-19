package ensate.ma.SpringAPI.Controllers;


import ensate.ma.SpringAPI.Model.CED;
import ensate.ma.SpringAPI.Services.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    // todo reset password for the professeur
    //  todo : reset password for the candidat

}
