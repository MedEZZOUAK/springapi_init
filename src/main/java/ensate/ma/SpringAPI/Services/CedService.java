package ensate.ma.SpringAPI.Services;


import ensate.ma.SpringAPI.DAO.CEDDETAILS;
import ensate.ma.SpringAPI.Model.CED;
import ensate.ma.SpringAPI.Model.StructureRecherche;
import ensate.ma.SpringAPI.Repository.CedRepo;
import ensate.ma.SpringAPI.Repository.StructRepo;
import ensate.ma.SpringAPI.Repository.SujetRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CedService {
  @Autowired
  private final StructRepo structRepo;
  @Autowired
  private final CedRepo cedRepo;

  @Autowired
  private final SujetRepo sujetRepo;




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

  // todo : get all structure by ced id
  public Iterable<StructureRecherche> getStructuresByCedId(Long cedId) {
    return structRepo.findAllByCed_id(cedId);
  }

  public List<CEDDETAILS> getallCEDinfos() {
    List<CEDDETAILS> ceddetails = new ArrayList<>();
    List<CED> ceds = cedRepo.findAll();
    for (CED ced : ceds) {
        List<String> structuresname = new ArrayList<>();
        structRepo.findAllByCed_id(ced.getId()).forEach(structureRecherche -> {
            structuresname.add(structureRecherche.getNom());
        });
        ceddetails.add(CEDDETAILS.builder().CED(ced.getNom()).id(Math.toIntExact(ced.getId())).
          structuresname(structuresname).build());
    }
    return ceddetails;
}

  public void AddCed(CED ced) {
    cedRepo.save(ced);
  }
  //todo : get all candidature by CED id

  //todo : accepte and refuse candidature
  // todo : accepter and refuse Bourse
  // todo : get all bourse by CED id
  //
}
