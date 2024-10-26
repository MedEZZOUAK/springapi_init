package ensate.ma.SpringAPI.Services;

import ensate.ma.SpringAPI.Model.StructureRecherche;
import ensate.ma.SpringAPI.Repository.StructRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class structureService {
  @Autowired
  private StructRepo structRepo;
  public String AddStructure(StructureRecherche struct) {
    structRepo.save(struct);
    return "Structure added successfully";
  }
}
