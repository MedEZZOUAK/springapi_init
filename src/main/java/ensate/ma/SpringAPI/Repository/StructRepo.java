package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.StructureRecherche;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StructRepo extends JpaRepository<StructureRecherche, Long> {
  List<StructureRecherche> findByCedId(Long cedId);
}
