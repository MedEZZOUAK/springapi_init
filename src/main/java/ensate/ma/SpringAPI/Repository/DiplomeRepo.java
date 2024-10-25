package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.Diplome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiplomeRepo extends JpaRepository<Diplome, Long> {
}
