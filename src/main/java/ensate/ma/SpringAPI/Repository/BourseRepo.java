package ensate.ma.SpringAPI.Repository;


import ensate.ma.SpringAPI.Model.Bourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BourseRepo extends JpaRepository<Bourse, Integer> {
    Optional<Bourse> findById(Integer id);
    // get bourse by ced id
    // bourse has candidature_id, this is linked to candidatures table which has sujet_id that is linked to sujets table and sujets table has structure_recherche_id that is linked to structure_recherches table which has ced_id
    // so we can get bourse by ced_id
    @Query("SELECT b FROM Bourse b " +
            "JOIN b.candidature c " +
            "JOIN c.sujet s " +
            "JOIN s.structureRecherche sr " +
            "WHERE sr.ced_id = :cedId")
    List<Bourse> findByCedId(@Param("cedId") Integer cedId);

}
