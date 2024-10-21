package ensate.ma.SpringAPI.Services;


import ensate.ma.SpringAPI.Model.CED;
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


    public StructureRecherche updateStructureRecherche(Long id, StructureRecherche updatedStruct) {
        // Check if the structure exists by the given id
        StructureRecherche existingStruct = structRepo.findById(id).orElse(null);

        if (existingStruct == null) {
            return null; // Structure not found
        }

        // Update the fields
        existingStruct.setNom(updatedStruct.getNom());
        existingStruct.setDomaine(updatedStruct.getDomaine());
        existingStruct.setEtablissement(updatedStruct.getEtablissement());

        // Update the CED if it has changed
        if (updatedStruct.getCed() != null && !updatedStruct.getCed().equals(existingStruct.getCed())) {
            existingStruct.setCed(updatedStruct.getCed());
        }

        // Update professors list if necessary
        if (updatedStruct.getProfesseurs() != null) {
            existingStruct.setProfesseurs(updatedStruct.getProfesseurs());
        }

        // Update subjects list if necessary
        if (updatedStruct.getSujets() != null) {
            existingStruct.setSujets(updatedStruct.getSujets());
        }

        // Save the updated entity
        return structRepo.save(existingStruct);
    }

    public void deleteStructureRecherche(Long id) {
        structRepo.deleteById(id);
    }
}
