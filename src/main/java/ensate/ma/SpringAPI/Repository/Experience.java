package ensate.ma.SpringAPI.Repository;


import ensate.ma.SpringAPI.Model.ExperienceProf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Experience extends JpaRepository<ExperienceProf, Integer> {
}
