package ensate.ma.SpringAPI.DAO;

import ensate.ma.SpringAPI.Model.Candidature;
import ensate.ma.SpringAPI.Model.Diplome;
import ensate.ma.SpringAPI.Model.ExperienceProf;
import ensate.ma.SpringAPI.Model.Langue;
import lombok.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
public class Candidatdetails {
  private String nom;
  private String prenom;
  private String email;
  private String cin;
  private String telephone;
  private List<Candidature> candidatures;

}
