package ensate.ma.SpringAPI.Services;


import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Repository.ProfesseurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesseurService {
  private final ProfesseurRepo professeurRepo;

  public ProfesseurService(ProfesseurRepo professeurRepo) {
    this.professeurRepo = professeurRepo;
  }


  public List<Professeur> findAllProfesseurs() {
    return professeurRepo.findAll();
  }

  public void AddProfesseur(Professeur professeur) {
    professeurRepo.save(professeur);
  }

  public  void deleteProfesseur(Long id) {
    professeurRepo.deleteById(id);
  }
  public Professeur findProfesseurById(Long id) {
    return professeurRepo.findById(id).orElseThrow(() -> new RuntimeException("Professeur not found"));
  }
  public Professeur updateProfesseur(Long id, Professeur professeurDetails) {
    Professeur professeur = professeurRepo.findById(id).orElseThrow(() -> new RuntimeException("Professeur not found"));
    professeur.setNom(professeurDetails.getNom());
    professeur.setPrenom(professeurDetails.getPrenom());
    professeur.setStructureRecherche(professeurDetails.getStructureRecherche());
    professeur.setSujets(professeurDetails.getSujets());
    return professeurRepo.save(professeur);
  }



}
