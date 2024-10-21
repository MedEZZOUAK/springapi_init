package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatureRepo extends JpaRepository<Candidature, Integer> {
}
