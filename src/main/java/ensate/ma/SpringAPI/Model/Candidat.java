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
  @Lob
  private byte[] cvScanne;
  @Lob
  private byte[] cinScanne;
  private Date dateNaissance;
  @Lob
  private byte[] photo;

  @OneToMany(mappedBy = "candidat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)

  private List<Langue> langues = new ArrayList<>();

  @OneToMany(mappedBy = "candidat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Diplome> diplomes;

  @OneToMany(mappedBy = "candidat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<ExperienceProf> experiences;

  @OneToMany(mappedBy = "candidat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Candidature> candidatures;


}
