package ensate.ma.SpringAPI.DAO;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class StructureRechercheDTO {
    private Long id;
    private String nom;
    private String domaine;
    private String etablissement;
}
