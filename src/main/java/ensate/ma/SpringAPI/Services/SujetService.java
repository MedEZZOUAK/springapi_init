package ensate.ma.SpringAPI.Services;

import ensate.ma.SpringAPI.Model.Sujet;
import ensate.ma.SpringAPI.Repository.SujetRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SujetService {
  private final SujetRepo sujetRepo;

  // todo : add delete update get all sujet by structure id , and by prof id
  // add sujet
  public void AddSujet(Sujet sujet) {
    sujetRepo.save(sujet);
  }

  // update sujet
  public Sujet UpdateSujet(Long id, Sujet sujetdetails) {
    Sujet sujet = sujetRepo.findById(id).orElseThrow(() -> new RuntimeException("sujet not found"));
    sujet.setDescription(sujetdetails.getDescription());
    sujet.setProfesseur_id(sujetdetails.getProfesseur_id());
    sujet.setThematique(sujetdetails.getThematique());
    sujet.setTitre(sujetdetails.getTitre());
    sujet.setStructureRecherche_id(sujetdetails.getStructureRecherche_id());
    return sujetRepo.save(sujet);
  }

  // delete sujet
  public void DeleteSujet(Long id) {
    sujetRepo.deleteById(id);
  }

  // get sujet by structure id
  public List<Sujet> getSujetByStructureId(Long structureId) {
    List<Sujet> sujets = sujetRepo.findByStructureRecherche_id(structureId);
    if (sujets.isEmpty()) {
      throw new RuntimeException("No subjects found for structure with id: " + structureId);
    }
    return sujets;
  }

  // get sujet by professor id
  public List<Sujet> getSujetByProfesseurId(Long professorId) {
    List<Sujet> sujets = sujetRepo.findByProfesseur_id(professorId);
    if (sujets.isEmpty()) {
      throw new RuntimeException("No subjects found for professor with id: " + professorId);
    }
    return sujets;
  }

}
