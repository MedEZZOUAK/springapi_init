package ensate.ma.SpringAPI.Services;


import ensate.ma.SpringAPI.DAO.*;
import ensate.ma.SpringAPI.Exception.BourseNotFoundException;
import ensate.ma.SpringAPI.Exception.CandidatureNotFoundException;
import ensate.ma.SpringAPI.Model.*;
import ensate.ma.SpringAPI.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private BourseRepo bourseRepo;
    @Autowired
    private CandidatRepo candidatRepo;






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
                            .domaine(structureRecherche.getDomaine())
                            .etablissement(structureRecherche.getEtablissement())
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
            structure.getNom(),
            structure.getDomaine(),
            structure.getEtablissement()
    );
  }


  //todo : get all candidature by CED id
  public List<EntretienDTO> getCandidaturesByCedId(Long cedId) {
    return candidatureRepo.findCandidaturesByCEDIdAndStatusEncours(cedId);
  }

  private CandidatureRequest convertToDTO(Candidature candidature) {
    CandidatureRequest dto = new CandidatureRequest();
    //dto.setId(candidature.getId());
    dto.setStatuts(String.valueOf(candidature.getStatuts()));
    dto.setDate(String.valueOf(candidature.getDate()));
    dto.setSujet_id(candidature.getSujet_id());
    dto.setCandidat_id(candidature.getCandidat_id());
    return dto;
  }

  //todo : accepter candidature
  public void accepteCandidature(Long id) {
    Candidature candidature = candidatureRepo.findById(id)
            .orElseThrow(() -> new CandidatureNotFoundException("Candidature with id " + id + " not found"));

    candidature.setStatuts(Statuts.preselectionnee);
    candidatureRepo.save(candidature);
  }

    // todo : refuse candidature
    public void refuseCandidature(Long id) {
        Candidature candidature = candidatureRepo.findById(id)
                .orElseThrow(() -> new CandidatureNotFoundException("Candidature with id " + id + " not found"));

        candidature.setStatuts(Statuts.Refusee);
        candidatureRepo.save(candidature);
    }

  // todo : accepter Bourse
  public void accepteBourse(Integer id) {
    Bourse bourse = bourseRepo.findById(id)
            .orElseThrow(() -> new BourseNotFoundException("Bourse with id " + id + " not found"));

    bourse.setStatut("Acceptée");
    bourseRepo.save(bourse);
  }

    // todo : refuse Bourse
    public void refuseBourse(Integer id) {
      Bourse bourse = bourseRepo.findById(id)
              .orElseThrow(() -> new BourseNotFoundException("Bourse with id " + id + " not found"));

      bourse.setStatut("Refusée");
      bourseRepo.save(bourse);
    }

  // todo : get all bourse by CED id
public List<BourseDTO> getAllBoursesByCedId(Integer cedId) {
  return bourseRepo.findByCedId(cedId)
          .stream()
          .map(bourse -> {
            BourseDTO dto = new BourseDTO();
            dto.setId(Math.toIntExact(bourse.getId())); // Convert Long to Integer
            dto.setStatut(bourse.getStatut());
            dto.setDateCreation(bourse.getDate()); // Ensure this method exists in Bourse

            // Ensure toCandidatureDTO method exists in CedService

            return dto;
          })
          .collect(Collectors.toList());
}

  public Candidatdetails getCandidatByCandidatureId(Long id) {
    //get candiature by id
    var candidature = candidatureRepo.findById(id).orElseThrow(() -> new CandidatureNotFoundException("Candidature with id " + id + " not found"));
    var Candidat = candidatRepo.findById(Long.valueOf(candidature.getCandidat_id())).orElseThrow(() -> new CandidatureNotFoundException("Candidat with id " + candidature.getCandidat_id() + " not found"));
    return Candidatdetails.builder()
      .nom(Candidat.getNom())
      .prenom(Candidat.getPrenom())
      .email(Candidat.getEmail())
      .cin(Candidat.getCin())
      .telephone(Candidat.getTelephone())
      .langues(Candidat.getLangues())
      .diplomes(Candidat.getDiplomes())
      .experiences(Candidat.getExperiences())
      .situationFamiliale(Candidat.getSituationFamiliale())
      .nationalite(Candidat.getNationalite())
      .prenomArabe(Candidat.getPrenomArabe())
      .nomArabe(Candidat.getNomArabe())
      .payeNaissance(Candidat.getPayeNaissance())
      .adresse(Candidat.getAdresse())
      .codePostal(Candidat.getCodePostal())
      .handicap(Candidat.getHandicap())
      .professionPere(Candidat.getProfessionPere())
      .professionMere(Candidat.getProfessionMere())
      .provincePere(Candidat.getProvincePere())
      .provinceMere(Candidat.getProvinceMere())
      .profession(Candidat.getProfession())
      .dateNaissance(Candidat.getDateNaissance())
      .id(Math.toIntExact(Candidat.getId()))
      .candidatures(null)
      .build();
  }
}
