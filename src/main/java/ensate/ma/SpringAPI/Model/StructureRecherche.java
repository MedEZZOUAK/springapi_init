package ensate.ma.SpringAPI.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "structureRecherches")
public class StructureRecherche {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String nom;
  private String domaine;
  private String Etablissement;
  private Long ced_id;

  //a structure de recherche is in a CED , but a CED can have many structures de recherche
  @ManyToOne
  @JoinColumn(name = "ced_id", referencedColumnName = "id", insertable = false, updatable = false)
  CED ced;
  //a structure de recherche can have many professeurs , but a professeur can be in one structure de recherche
  @OneToMany(mappedBy = "structureRecherche")
  List<Professeur> professeurs;

  //a structure de recherche can have many sujets , but a sujet is in one structure de recherche
  @OneToMany(mappedBy = "structureRecherche")
  List<Sujet> sujets;




}
