package ensate.ma.SpringAPI.Services;

import ensate.ma.SpringAPI.Model.CED;
import ensate.ma.SpringAPI.Repository.CedRepo;
import ensate.ma.SpringAPI.Repository.loginRepo;
import ensate.ma.SpringAPI.user.Role;
import ensate.ma.SpringAPI.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final CedRepo cedRepo;
    @Autowired
    private final loginRepo loginRepo;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public void AddCed(CED ced) {
        String password = "welcome123";
        var login = User.builder()
                .email(ced.getEmail())
                .password(passwordEncoder.encode(password))
                .role(Role.CED)
                .build();
        loginRepo.save(login);
        cedRepo.save(ced);
    }


}

