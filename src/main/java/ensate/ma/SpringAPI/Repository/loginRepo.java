package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface loginRepo extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}

