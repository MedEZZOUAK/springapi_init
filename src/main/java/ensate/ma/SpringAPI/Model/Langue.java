package ensate.ma.SpringAPI.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "id", nullable = false)
    private Long id;

    private String langue;
    private String niveau;

    @JoinColumn(name = "candidat_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    @JsonIgnore
    private Candidat candidat;

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }
}