package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatRepo extends JpaRepository<Candidat, Long> {
}
