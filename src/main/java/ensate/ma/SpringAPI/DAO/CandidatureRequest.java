package ensate.ma.SpringAPI.DAO;


import lombok.*;

import java.util.Date;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatureRequest {

  private String Statuts;
  private String date;
  private Integer Sujet_id;
  private Integer Candidat_id;
}
