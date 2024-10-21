package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesseurRepo extends JpaRepository<Professeur, Long> {

  Optional<Professeur> findByEmail(String email);
}
