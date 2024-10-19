package ensate.ma.SpringAPI.Model;


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
@Table(name = "langues")
public class Langue {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String langue;
  private String niveau;
  private Integer candidat_id;
  //a candidat can have many languages , but a language can be spoken by one candidate
  @ManyToOne
  @JoinColumn(name = "candidat_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Candidat candidat;


  public void setCandidat(Candidat candidat) {
    this.candidat = candidat;
  }

}
