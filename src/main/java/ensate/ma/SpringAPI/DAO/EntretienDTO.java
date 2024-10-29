package ensate.ma.SpringAPI.DAO;

import java.sql.Date;

import lombok.*;


public interface EntretienDTO {
  Long getId();
  String getNomPrenom();
  String getSujet_nom();
  String getEtablissement();
}
