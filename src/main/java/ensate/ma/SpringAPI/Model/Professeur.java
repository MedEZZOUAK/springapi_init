package ensate.ma.SpringAPI.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

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
  private Integer Centre_ID;

  //a professeur have one StructureRecherche , but a StructureRecherche can have many professeurs
  @ManyToOne
  @JoinColumn(name = "Centre_ID", referencedColumnName = "id", insertable = false, updatable = false)
  private StructureRecherche structureRecherche;

  //a professeur can have many sujets , but a sujet is owned by one professeur , max can have 9 sujets
  @OneToMany(mappedBy = "professeur")
  private List<Sujet> sujets;

  @Override
  public String toString() {
    return "Professeur{" +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            ", email='" + email + '\'' +
            ", Centre_ID=" + Centre_ID +
            '}';
  }


}
