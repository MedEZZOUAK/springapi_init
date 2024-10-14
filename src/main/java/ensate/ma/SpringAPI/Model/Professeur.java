package ensate.ma.SpringAPI.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professeurs")
@Setter
@Getter
public class Professeur {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  @JdbcTypeCode(SqlTypes.INTEGER)
  private Long id;

  private String nom;
  private String prenom;
  private String email;
  private String Codeprof;
  private Integer CentreId ; //discuss with the prof
  private String image; //learn how to store img in spring boot



}
