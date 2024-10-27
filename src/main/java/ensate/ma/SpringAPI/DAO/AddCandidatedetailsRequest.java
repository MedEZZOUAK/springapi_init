package ensate.ma.SpringAPI.DAO;

import lombok.*;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCandidatedetailsRequest {

  private String situationFamiliale;
  private String nationalite;
  private String prenomArabe;
  private String nomArabe;
  private String payeNaissance;
  private String adresse;
  private Integer codePostal;
  private String handicap;
  private String professionPere;
  private String professionMere;
  private String provincePere;
  private String provinceMere;
  private String profession;
  private Date dateNaissance;
}
