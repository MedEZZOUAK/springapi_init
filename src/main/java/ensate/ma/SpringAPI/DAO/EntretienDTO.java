package ensate.ma.SpringAPI.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class EntretienDTO {

  private String nomCandidat;
  private String nomSujet;
  private String nomEtablissement;
  private Long id_candidature;

}
