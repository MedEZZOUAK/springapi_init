package ensate.ma.SpringAPI.Services;


import ensate.ma.SpringAPI.Model.StructureRecherche;
import ensate.ma.SpringAPI.Repository.StructRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ensate.ma.SpringAPI.Repository.CedRepo;


@Service
@RequiredArgsConstructor
public class CedService {
    private final StructRepo structRepo;


    // add structure de recherche
    public void AddCed(StructureRecherche struct) {
        structRepo.save(struct);
    }
}
