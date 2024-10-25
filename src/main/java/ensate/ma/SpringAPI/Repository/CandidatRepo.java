package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidatRepo extends JpaRepository<Candidat, Long> {
  //find by email
  Optional<Candidat> findByEmail(String email);

  Optional<Candidat> findAllByEmailAndCandidaturesIsNullOrderByCinAsc(String email);


}
