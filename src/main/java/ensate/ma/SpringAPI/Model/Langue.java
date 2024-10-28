package ensate.ma.SpringAPI.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @JsonBackReference
  @JsonIgnore
  Candidat candidat;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String langue;
  private String niveau;
}
