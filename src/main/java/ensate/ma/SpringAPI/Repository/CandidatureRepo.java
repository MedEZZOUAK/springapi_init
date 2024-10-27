package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.DAO.CandidatureRequest;
import ensate.ma.SpringAPI.Model.Candidature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandidatureRepo extends JpaRepository<Candidature, Integer> {
    @Query(value = "SELECT c.* FROM candidatures c " +
            "JOIN sujets s ON c.sujet_id = s.id " +
            "JOIN structure_recherches sr ON s.structure_recherche_id = sr.id " +
            "WHERE sr.ced_id = :cedId",
            nativeQuery = true)
    List<Candidature> findByCedId(@Param("cedId") Long cedId);

    // find candidature by id
    Optional<Candidature> findById(Long id);


  @Query(value="SELECT * FROM candidatures WHERE professeur_id = :id AND status = :entretien", nativeQuery = true)
  List<Candidature> findByProfesseurIdAndStatus(Long id, String entretien);
}
