package ensate.ma.SpringAPI.Services;


import ensate.ma.SpringAPI.DAO.CEDDETAILS;
import ensate.ma.SpringAPI.DAO.CandidatureRequest;
import ensate.ma.SpringAPI.Model.CED;
import ensate.ma.SpringAPI.Model.Candidature;
import ensate.ma.SpringAPI.Model.StructureRecherche;
import ensate.ma.SpringAPI.Repository.CandidatureRepo;
import ensate.ma.SpringAPI.Repository.CedRepo;
import ensate.ma.SpringAPI.Repository.StructRepo;
import ensate.ma.SpringAPI.Repository.SujetRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ensate.ma.SpringAPI.DAO.StructureRechercheDTO;
import ensate.ma.SpringAPI.Exception.CandidatureNotFoundException;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CedService {
  private final StructRepo structRepo;

  @Autowired
  private final SujetRepo sujetRepo;
  @Autowired
  private CedRepo cedRepo;

  @Autowired
  private final CandidatureRepo candidatureRepo;






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
  public List<StructureRechercheDTO> getStructuresByCedId(Long cedId) {
    List<StructureRecherche> structures = structRepo.findByCedId(cedId);
    return structures.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  public List<CEDDETAILS> getallCEDinfos() {
    List<CEDDETAILS> ceddetails = new ArrayList<>();
    List<CED> ceds = cedRepo.findAll();
    for (CED ced : ceds) {
        List<StructureRechercheDTO> structuresname = new ArrayList<>();
        structRepo.findAllByCed_id(ced.getId()).forEach(structureRecherche -> {
            structuresname.add(StructureRechercheDTO.builder().id(structureRecherche.getId()).nom(structureRecherche.getNom())
              .build());
        });
        ceddetails.add(CEDDETAILS.builder().CED(ced.getNom()).id(Math.toIntExact(ced.getId())).
          structuresname(structuresname).build());
    }
    return ceddetails;
}

  public void AddCed(CED ced) {
    cedRepo.save(ced);
  }

  private StructureRechercheDTO convertToDTO(StructureRecherche structure) {
    return new StructureRechercheDTO(
            structure.getId(),
            structure.getNom()
    );
  }


  //todo : get all candidature by CED id
  public List<CandidatureRequest> getCandidaturesByCedId(Long cedId) {
    List<Candidature> candidatures = candidatureRepo.findByCedId(cedId);
    return candidatures.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  private CandidatureRequest convertToDTO(Candidature candidature) {
    CandidatureRequest dto = new CandidatureRequest();
    //dto.setId(candidature.getId());
    dto.setStatuts(candidature.getStatuts());

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // specify your date format here
    String dateString = dateFormat.format(candidature.getDate());
    dto.setDate(dateString);

    dto.setSujet_id(candidature.getSujet_id());
    dto.setCandidat_id(candidature.getCandidat_id());
    return dto;
  }

  //todo : accepte and refuse candidature
  public void accepteCandidature(Long id) {
    Candidature candidature = candidatureRepo.findById(id);
    if (candidature == null) {
      throw new CandidatureNotFoundException("Candidature with id " + id + " not found");
    }
    candidature.setStatuts("Accepted");
    candidatureRepo.save(candidature);
  }
  // todo : accepter and refuse Bourse
  // todo : get all bourse by CED id
  //
}
