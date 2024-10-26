package ensate.ma.SpringAPI.DAO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class ProfesseurDTO {
    private String nom;
    private String prenom;
    private Integer Centre_ID;
    private String structnom;
    private String etablissement;

    public ProfesseurDTO(String nom, String prenom, Integer Centre_ID, String structnom, String etablissement) {
        this.nom = nom;
        this.prenom = prenom;
        this.Centre_ID = Centre_ID;
        this.structnom = structnom;
        this.etablissement = etablissement;
    }
}