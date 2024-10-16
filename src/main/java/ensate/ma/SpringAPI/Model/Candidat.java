package ensate.ma.SpringAPI.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@Table(name = "candidats")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Candidat {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String nom;
  private String prenom;
  private String email;
  private String CIN;
  private String telephone;
  private String SituationFamiliale;
  private String Nationalite;
  private String PrenomArabe;
  private String NomArabe;
  private String PayeNaissance;
  private String adresse;
  private Integer CodePostal;
  private String Handicap;
  private String ProfessionPere;
  private String ProfessionMere;
  private String ProvincePere;
  private String ProvinceMere;
  private String Profession;
  private String CvScanne;
  private String CinScanne;


  //langues spoken by the candidate, a condidat can speak many languages or one language
  @OneToMany(mappedBy = "candidat")
  private List<Langue> langues;

  //diplomes owned by the candidate, a candidate can have many diplomes or one diplome
  @OneToMany(mappedBy = "candidat")
  private List<Diplome> diplomes;

  //experiences owned by the candidate, a candidate can have many experiences or one experience
  @OneToMany(mappedBy = "candidat")
  private List<ExperienceProf> experiences;


}
