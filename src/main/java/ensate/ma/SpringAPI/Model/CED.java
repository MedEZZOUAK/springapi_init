package ensate.ma.SpringAPI.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ced")
@Setter
@Getter
public class CED {
  @OneToMany(mappedBy = "ced")
  List<StructureRecherche> structureRecherches;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;
  private String Nom;

//A ced Have many StructureRecherche
  private String email;


}
