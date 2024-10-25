package ensate.ma.SpringAPI.Model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
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
  private String cin;
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
  @Lob
  private byte[] CvScanne;
  @Lob
  private byte[] CinScanne;
  private Date DateNaissance;

  @OneToMany(mappedBy = "candidat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)

  private List<Langue> langue = new ArrayList<>();

  @OneToMany(mappedBy = "candidat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Diplome> diplomes;

  @OneToMany(mappedBy = "candidat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<ExperienceProf> experiences;

  @OneToMany(mappedBy = "candidat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Candidature> candidatures;




}
