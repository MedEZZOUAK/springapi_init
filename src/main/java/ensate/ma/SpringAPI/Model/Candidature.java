package ensate.ma.SpringAPI.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidatures")
@Setter
@Getter
@Builder
public class Candidature {
  //a candidature is for a sujet , but a sujet can have many candidatures
  @ManyToOne
  @JoinColumn(name = "sujet_id", referencedColumnName = "id", insertable = false, updatable = false)
  @JsonBackReference
  @JsonIgnore
  Sujet sujet;
  //a cancidature is from a candidat , but a candidat can have many candidatures
  @ManyToOne
  @JoinColumn(name = "candidat_id", referencedColumnName = "id", insertable = false, updatable = false)
  @JsonBackReference
  @JsonIgnore
  Candidat candidat;


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;
  @Enumerated(EnumType.STRING)
  private Statuts statuts;
  private Date date;
  private Integer Sujet_id;
  private Integer Candidat_id;

}
