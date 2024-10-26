package ensate.ma.SpringAPI.DAO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ProfesseurDTO {
    private String nom;
    private String prenom;
    private Integer Centre_ID;
}
