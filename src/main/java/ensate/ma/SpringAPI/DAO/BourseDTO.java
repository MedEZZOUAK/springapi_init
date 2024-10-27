package ensate.ma.SpringAPI.DAO;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BourseDTO {
    private Integer id;
    private String statut;
    private Date dateCreation;
    private CandidatureRequest candidature;
}