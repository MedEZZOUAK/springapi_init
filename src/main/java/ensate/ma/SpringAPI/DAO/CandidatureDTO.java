package ensate.ma.SpringAPI.DAO;

import ensate.ma.SpringAPI.Model.Diplome;
import ensate.ma.SpringAPI.Model.Langue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Builder
@AllArgsConstructor
public class CandidatureDTO {
  private Candidatdetails candidatdetails;
  private SujetDTO sujet;
}
