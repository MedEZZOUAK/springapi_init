package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.Diplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DiplomeRepo extends JpaRepository<Diplome, Long> {

    @Query("SELECT d FROM Diplome d WHERE d.candidat_id = :id AND d.type = :type")
    Optional<Diplome> findDiplomeByCandidat_idAndType(@Param("id") Long id, @Param("type") String type);
}
