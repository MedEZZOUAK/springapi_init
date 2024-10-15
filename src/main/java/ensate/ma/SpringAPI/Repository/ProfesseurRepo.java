package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepo extends JpaRepository<Professeur, Long> {

}
