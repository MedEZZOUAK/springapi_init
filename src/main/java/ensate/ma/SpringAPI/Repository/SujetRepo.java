package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.Sujet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SujetRepo extends JpaRepository<Sujet, Long> {
}
