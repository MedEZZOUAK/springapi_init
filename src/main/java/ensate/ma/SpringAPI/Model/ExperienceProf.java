package ensate.ma.SpringAPI.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @JsonBackReference
  @JsonIgnore
  Candidat candidat;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;
  private String experience;
  private String etablissement;
  private String fonction;
  private String secteurActivite;
  private Date dateDebut;
  private Date dateFin;
  private Integer candidat_id;

}
