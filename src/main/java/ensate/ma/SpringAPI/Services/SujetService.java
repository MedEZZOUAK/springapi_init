package ensate.ma.SpringAPI.Services;

import ensate.ma.SpringAPI.Model.Sujet;
import ensate.ma.SpringAPI.Repository.SujetRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
    public Sujet getSujetByStructureId(Long id){

    }

}
