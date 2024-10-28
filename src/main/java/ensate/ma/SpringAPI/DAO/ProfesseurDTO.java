package ensate.ma.SpringAPI.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ProfesseurDTO {
    private String nom;
    private String prenom;
    private String email;
    private Integer Centre_ID;
    private String structnom;
    private String etablissement;
    private Long structid;
    private String domaine;

    public ProfesseurDTO(String nom, String email, String prenom, Integer Centre_ID, String structnom, String etablissement,Long structid) {
        this.nom = nom;
        this.prenom = prenom;
        this.Centre_ID = Centre_ID;
        this.structnom = structnom;
        this.etablissement = etablissement;
        this.email = email;
        this.structid = structid;
    }
    public ProfesseurDTO(String nom, String email, String prenom, Integer Centre_ID, String structnom, String etablissement) {
        this.nom = nom;
        this.prenom = prenom;
        this.Centre_ID = Centre_ID;
        this.structnom = structnom;
        this.etablissement = etablissement;
        this.email = email;
    }
}