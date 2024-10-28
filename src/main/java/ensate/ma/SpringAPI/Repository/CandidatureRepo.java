package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.DAO.EntretienDTO;
import ensate.ma.SpringAPI.Model.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface CandidatureRepo extends JpaRepository<Candidature, Integer> {
  @Query(value = "SELECT c.* FROM candidatures c " + "JOIN sujets s ON c.sujet_id = s.id " + "JOIN structure_recherches sr ON s.structure_recherche_id = sr.id " + "WHERE sr.ced_id = :cedId", nativeQuery = true)
  List<Candidature> findByCedId(@Param("cedId") Long cedId);

  // find candidature by id
  Optional<Candidature> findById(Long id);

  @Query(value = "SELECT * FROM candidatures WHERE professeur_id = :id AND status = :entretien", nativeQuery = true)
  List<Candidature> findByProfesseurIdAndStatus(Long id, String entretien);

// candidatures table has sujet_id that is linked to the sujets table by id and the sujets table also has professeur_id that is also linked to the professeurs table by id and statuts in candidatures table is preselectionnee
// i have to select nom prenom from candidats table, le nom de sujet from table sujets and etablissement from structure_recherches table
    // find candidature by professeur id

  @Query(value = "SELECT c.nom as nom, c.prenom as prenom, s.titre AS sujet_nom, sr.etablissement as etablissement " +
          "FROM candidatures cd " +
          "JOIN candidats c ON cd.candidat_id = c.id " +
          "JOIN sujets s ON cd.sujet_id = s.id " +
          "JOIN structure_recherches sr ON s.structure_recherche_id = sr.id " +
          "WHERE s.professeur_id = :professeurId AND cd.statuts = 'preselectionnee'", nativeQuery = true)
  List<EntretienDTO> findCandidaturesByProfesseurIdAndStatusPreselectionnee(@Param("professeurId") Long professeurId);

  List<Candidature> findByCandidatId(Long id);
  List<Candidature> findAllByCandidat_id(Long id);
}
