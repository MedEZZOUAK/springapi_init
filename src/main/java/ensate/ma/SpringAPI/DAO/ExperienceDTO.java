package ensate.ma.SpringAPI.DAO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@RequiredArgsConstructor
@Setter
@Getter
public class ExperienceDTO {
  private String Experience;
  private String Etablissement;
  private String Fonction;
  private String SecteurActivite;
  private Date DateDebut;
  private Date DateFin;
  private int candidat_id;
}
