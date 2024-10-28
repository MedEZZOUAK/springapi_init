package ensate.ma.SpringAPI.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @JsonBackReference
  @JsonIgnore
  Candidat candidat;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;
  private String nomEtablissement;
  private String pays;
  private String academie;
  private String statut;//private or public
  private String specialite;
  private String anneeObtention;
  private String mention;
  private double moyenne;//mentionGeneral
  private String type;
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
