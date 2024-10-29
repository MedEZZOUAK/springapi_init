package ensate.ma.SpringAPI.DAO;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SujetDTO {
    private Long id;
    private String titre;
    private String description;
    private String thematique;
}
