package ensate.ma.SpringAPI.Services;

import ensate.ma.SpringAPI.Model.Candidat;
import ensate.ma.SpringAPI.Model.Langue;
import ensate.ma.SpringAPI.Repository.CandidatRepo;
import ensate.ma.SpringAPI.Repository.LangueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CandidatService {
    private final CandidatRepo candidatRepo;
    private final LangueRepo langueRepo;

    public CandidatService(CandidatRepo candidatRepo, LangueRepo langueRepo) {
        this.candidatRepo = candidatRepo;
      this.langueRepo = langueRepo;
    }

    public List<Candidat> findAllCandidats() {
        return candidatRepo.findAll();
    }

    public void AddCandidat(Candidat candidat) {
        candidatRepo.save(candidat);
    }

    public void deleteCandidat(Long id) {
        candidatRepo.deleteById(id);
    }

    public Candidat findCandidatById(Long id) {
        return candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));
    }

    public Candidat updateCandidat(Long id, Candidat candidatDetails) {
        Candidat candidat = candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));
        candidat.setNom(candidatDetails.getNom());
        candidat.setPrenom(candidatDetails.getPrenom());
        candidat.setEmail(candidatDetails.getEmail());
        candidat.setCIN(candidatDetails.getCIN());
        candidat.setTelephone(candidatDetails.getTelephone());
        return candidatRepo.save(candidat);
    }

    public Candidat addLangue(Long id, List<Langue> langue) {
      //set candidat_id for each langue
      langue.forEach(lang -> lang.setCandidat_id(Math.toIntExact(id)));
      //save all languages
      langueRepo.saveAll(langue);
      //get candidat by id
      return candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));
    }

}
