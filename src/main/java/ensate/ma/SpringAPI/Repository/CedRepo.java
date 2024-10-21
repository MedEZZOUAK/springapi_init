package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.CED;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CedRepo extends JpaRepository<CED, Long> {

    Optional<CED> findByEmail(String email);
}
