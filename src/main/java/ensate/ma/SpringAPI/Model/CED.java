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
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String Nom;
  private String email;

//A ced Have many StructureRecherche

  @OneToMany(mappedBy = "ced")
  List<StructureRecherche> structureRecherches;



}
