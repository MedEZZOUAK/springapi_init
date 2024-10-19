package ensate.ma.SpringAPI.Services;

import ensate.ma.SpringAPI.Model.Candidat;
import ensate.ma.SpringAPI.Model.Langue;
import ensate.ma.SpringAPI.Repository.CandidatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CandidatService {
    private final CandidatRepo candidatRepo;

    public CandidatService(CandidatRepo candidatRepo) {
        this.candidatRepo = candidatRepo;
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
        Candidat candidat = candidatRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidat not found"));
        candidat.setLangues(langue);
        
    }

}
