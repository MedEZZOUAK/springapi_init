package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.DAO.DTOgene;
import ensate.ma.SpringAPI.DAO.EntretienDTO;
import ensate.ma.SpringAPI.Model.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

  @Query(value = "SELECT cd.id as id, CONCAT(c.nom, ' ', c.prenom) as nom_prenom, s.titre AS sujet_nom, sr.etablissement as etablissement " +
          "FROM candidatures cd " +
          "JOIN candidats c ON cd.candidat_id = c.id " +
          "JOIN sujets s ON cd.sujet_id = s.id " +
          "JOIN structure_recherches sr ON s.structure_recherche_id = sr.id " +
          "WHERE s.professeur_id = :professeurId AND cd.statuts = 'preselectionnee'", nativeQuery = true)
  List<EntretienDTO> findCandidaturesByProfesseurIdAndStatusPreselectionnee(@Param("professeurId") Long professeurId);

  List<Candidature> findByCandidatId(Long id);
  List<Candidature> findAllByCandidat_id(Long id);
  @Query(value = "SELECT * FROM candidatures WHERE professeur_id = :id", nativeQuery = true)
  List<Candidature> findByProfesseurId(Long id);




  @Query(value = "SELECT cd.id as id, CONCAT(c.nom, ' ', c.prenom) as nom_prenom, s.titre AS sujet_nom, sr.etablissement as etablissement " +
    "FROM candidatures cd " +
    "JOIN candidats c ON cd.candidat_id = c.id " +
    "JOIN sujets s ON cd.sujet_id = s.id " +
    "JOIN structure_recherches sr ON s.structure_recherche_id = sr.id " +
    "WHERE sr.ced_id = :cedId AND cd.statuts = 'Encours'", nativeQuery = true)
  List<EntretienDTO> findCandidaturesByCEDIdAndStatusEncours(@Param("cedId") Long cedId);


  @Modifying
@Query(value = "DELETE FROM candidatures WHERE id = :id", nativeQuery = true)
void deleteCandidatureById(@Param("id") Long id);

  // find candidature by professeur id and status acceptee
  // nom prenom id from candidats table and nom prenom id from professeurs table and id candidature from candidatures table and titre from sujets table and etablissement, domaine, nom from structure_recherches table
  @Query(value = "SELECT new ensate.ma.SpringAPI.DAO.DTOgene(" +
        "cd.id, " +
        "CONCAT(c.nom, ' ', c.prenom), " +
        "CONCAT(p.nom, ' ', p.prenom), " +
        "s.titre, " +
        "sr.nom, " +
        "sr.etablissement, " +
        "sr.domaine, " +
        "c.id, " +
        "p.id, " +
        "cd.date) " +
        "FROM Candidature cd " +
        "JOIN cd.candidat c " +
        "JOIN cd.sujet s " +
        "JOIN s.professeur p " +
        "JOIN s.structureRecherche sr " +
        "WHERE p.id = :professeurId AND cd.statuts = 'Acceptee'")
List<DTOgene> findCandidaturesByProfesseurIdAndStatusAcceptee(@Param("professeurId") Long professeurId);

    // find candidature by candidat id and status acceptee
    // nom prenom id from candidats table and nom prenom id from professeurs table and id candidature from candidatures table and titre from sujets table and etablissement, domaine, nom from structure_recherches table
    @Query(value = "SELECT new ensate.ma.SpringAPI.DAO.DTOgene(" +
            "cd.id, " +
            "CONCAT(c.nom, ' ', c.prenom), " +
            "CONCAT(p.nom, ' ', p.prenom), " +
            "s.titre, " +
            "sr.nom, " +
            "sr.etablissement, " +
            "sr.domaine, " +
            "c.id, " +
            "p.id, " +
            "cd.date) " +
            "FROM Candidature cd " +
            "JOIN cd.candidat c " +
            "JOIN cd.sujet s " +
            "JOIN s.professeur p " +
            "JOIN s.structureRecherche sr " +
            "WHERE c.id = :candidatId AND cd.statuts = 'Acceptee'")
  List<DTOgene> findCandidaturesByCandidatIdAndStatusAcceptee(@Param("candidatId") Long candidatId);
}
