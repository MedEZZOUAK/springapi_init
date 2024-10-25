package ensate.ma.SpringAPI.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "diplomes")
public class Diplome {
  //a candidat can have many diplomes , but a diplome can be owned by one candidate
  @ManyToOne
  @JoinColumn(name = "candidat_id", referencedColumnName = "id", insertable = false, updatable = false)
  Candidat candidat;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;
  private String NomEtablissement;
  private String Pays;
  private String Academie;
  private String Statut;//private or public
  private String Specialite;
  private String AnneeObtention;
  private String Mention;
  private double Moyenne;//mentionGeneral
  private String Type;
  @Nullable
  @Lob
  private byte[] DiplomeScanne;
  private String DiplomeScanneName;
  @Nullable
  @Lob
  private byte[] RelevetNoteScanne;
  private String RelevetNoteScanneName;
  private Integer candidat_id;


}
