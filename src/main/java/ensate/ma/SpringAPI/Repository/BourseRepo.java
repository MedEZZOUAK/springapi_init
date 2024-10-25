package ensate.ma.SpringAPI.Repository;


import ensate.ma.SpringAPI.Model.Bourse;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BourseRepo extends JpaRepository<Bourse, Long> {
}
