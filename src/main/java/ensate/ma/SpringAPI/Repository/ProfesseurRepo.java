package ensate.ma.SpringAPI.Repository;

import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.DAO.ProfesseurDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfesseurRepo extends JpaRepository<Professeur, Long> {

  Optional<Professeur> findByEmail(String email);
  // find by ced id, list of professeurs

  @Query(value = "SELECT new ensate.ma.SpringAPI.DAO.ProfesseurDTO(p.nom, p.prenom, p.email, p.centre_id, s.nom, s.etablissement) " +
          "FROM Professeur  p " +
          "JOIN StructureRecherche s ON p.centre_id = s.id " +
          "WHERE s.ced_id = :cedId", nativeQuery = false)
  List<ProfesseurDTO> findByCedId(@Param("cedId") Long cedId);
}

