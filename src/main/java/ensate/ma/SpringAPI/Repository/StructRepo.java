package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.CED;
import ensate.ma.SpringAPI.Model.StructureRecherche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StructRepo extends JpaRepository<StructureRecherche, Long> {
}
