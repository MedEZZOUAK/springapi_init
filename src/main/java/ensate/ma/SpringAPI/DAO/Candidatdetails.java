package ensate.ma.SpringAPI.DAO;

import ensate.ma.SpringAPI.Model.Candidature;
import ensate.ma.SpringAPI.Model.Diplome;
import ensate.ma.SpringAPI.Model.ExperienceProf;
import ensate.ma.SpringAPI.Model.Langue;
import lombok.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
public class Candidatdetails {
  private String nom;
  private String prenom;
  private String email;
  private String cin;
  private String telephone;
  private List<Candidature> candidatures;
  private List<Langue> langues;
  private List<Diplome> diplomes;
  private List<ExperienceProf> experiences;
  private String situationFamiliale;
  private String nationalite;
  private String prenomArabe;
  private String nomArabe;
  private String payeNaissance;
  private String adresse;
  private Integer codePostal;
  private String handicap;
  private String professionPere;
  private String professionMere;
  private String provincePere;
  private String provinceMere;
  private String profession;
  private Date dateNaissance;


}
