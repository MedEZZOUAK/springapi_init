package ensate.ma.SpringAPI.DAO;

import ensate.ma.SpringAPI.Model.Statuts;
import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CandidaturesCandidatId {

  private String sujetNom;
  private String thematique;
  private String etablissement;
  private Statuts statuts;


}
