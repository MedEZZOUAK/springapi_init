package ensate.ma.SpringAPI.DAO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatureRequest {

  private String Statuts;
  private String date;
  private Integer Sujet_id;
  private Integer Candidat_id;
}
