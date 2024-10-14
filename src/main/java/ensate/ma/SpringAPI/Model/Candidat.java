package ensate.ma.SpringAPI.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidats")
@Getter
@Setter
public class Candidat {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String nom;
  private String prenom;
  private String email;
  private String CNE;
  private String CIN;
  private String image; //learn how to store img in spring boot
  private String telephone;

  public Candidat(String nom, String prenom, String email, String CNE, String CIN, String image, String telephone) {
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.CNE = CNE;
    this.CIN = CIN;
    this.image = image;
    this.telephone = telephone;
  }


}
