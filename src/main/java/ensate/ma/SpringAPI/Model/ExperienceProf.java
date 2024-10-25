package ensate.ma.SpringAPI.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "experienceProfessionnelles")

public class ExperienceProf {
  //a candidat can have many experiences , but an experience can be owned by one candidate
  @ManyToOne
  @JoinColumn(name = "candidat_id", referencedColumnName = "id", insertable = false, updatable = false)
  Candidat candidat;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;
  private String Experience;
  private String Etablissement;
  private String Fonction;
  private String SecteurActivite;
  private Date DateDebut;
  private Date DateFin;
  private Integer candidat_id;

}
