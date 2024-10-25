package ensate.ma.SpringAPI.Model;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bourse {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String statut;
  private Date date;
  private Integer candidature_id;


  @ManyToOne
  @JoinColumn(name = "candidature_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Candidature candidature;


}
