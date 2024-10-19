package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.Langue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LangueRepo extends JpaRepository<Langue, Long> {

  Optional <Langue> findByCandidat_id(Long id);
}
