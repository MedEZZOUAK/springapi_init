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
@Setter
@Getter
@Table(name = "sujets")
public class Sujet {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String titre;
  private String description;
  private String Thematique;

  private Long professeur_id;
  private Long structureRecherche_id;
  //a sujet is owned by a professeur , but a professeur can have many sujets max 9
  @ManyToOne
  @JoinColumn(name = "professeur_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Professeur professeur;

  //a sujet is in a structure de recherche , but a structure de recherche can have many sujets
  @ManyToOne
  @JoinColumn(name = "structureRecherche_id", referencedColumnName = "id", insertable = false, updatable = false)
  private StructureRecherche structureRecherche;

  //a sujet can have many candidatures , but a candidature is for one sujet
  @OneToMany(mappedBy = "sujet")
  private List<Candidature> candidatures;




}
