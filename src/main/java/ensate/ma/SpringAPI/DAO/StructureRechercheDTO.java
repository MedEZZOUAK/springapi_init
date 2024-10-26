package ensate.ma.SpringAPI.DAO;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StructureRechercheDTO {
    private Long id;
    private String nom;
    private String domaine;
    private String etablissement;
}