package ensate.ma.SpringAPI.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "langues")
public class Langue {
  @ManyToOne
  @JoinColumn(name = "candidat_id")
  Candidat candidat;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String langue;
  private String niveau;
}
