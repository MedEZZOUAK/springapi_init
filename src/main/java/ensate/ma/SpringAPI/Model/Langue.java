package ensate.ma.SpringAPI.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Langue {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String langue;
  private String niveau;

  @ManyToOne
  @JoinColumn(name = "candidat_id")
  private Candidat candidat;
}
