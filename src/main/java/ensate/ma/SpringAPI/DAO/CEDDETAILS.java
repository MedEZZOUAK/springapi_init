package ensate.ma.SpringAPI.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import ensate.ma.SpringAPI.Model.StructureRecherche;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CEDDETAILS {
    @JsonProperty("ced_name")
    private String CED;
    private Integer id;
    private List<StructureRechercheDTO> structuresname;
}
