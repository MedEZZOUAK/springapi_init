package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.StructureRecherche;
import ensate.ma.SpringAPI.Model.Sujet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SujetRepo extends JpaRepository<Sujet, Long> {
    List<Sujet> findAllByStructureRecherche_id(Long id);

}
