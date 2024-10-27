package ensate.ma.SpringAPI.Controllers;

import ensate.ma.SpringAPI.DAO.ProfesseurDTO;
import ensate.ma.SpringAPI.DAO.SujetDTO;
import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Model.Sujet;
import ensate.ma.SpringAPI.Repository.SujetRepo;
import ensate.ma.SpringAPI.Services.ProfesseurService;
import ensate.ma.SpringAPI.Services.SujetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Professeur")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ProfesseurController {

  @Autowired
  private ProfesseurService professeurService;
  @Autowired
  private SujetService SujetService;
    @Autowired
    private SujetRepo sujetRepo;

  @PostMapping("/delete/{id}")
  public String deleteProfesseur(@PathVariable Long id) {
    professeurService.deleteProfesseur(id);
    return "Professeur deleted successfully";
  }

  @GetMapping("/all")
  public List<Professeur> findAllProfesseurs() {
    return professeurService.findAllProfesseurs();
  }

  @GetMapping("/find/{id}")
  public ProfesseurDTO findProfesseurById(@PathVariable Long id) {
    Professeur professeur = professeurService.findProfesseurById(id);
    return new ProfesseurDTO(
            professeur.getNom(),
            professeur.getPrenom(),
            professeur.getCentre_ID(),
            professeur.getStructureRecherche().getNom(),
            professeur.getStructureRecherche().getEtablissement()
    );
  }

  @PutMapping("/update/{id}")
  public Professeur updateProfesseur(@PathVariable Long id, @RequestBody Professeur professeurDetails) {
    return professeurService.updateProfesseur(id, professeurDetails);
  }

  @PostMapping("/addSujet")
  public String addSujet(@RequestBody Sujet sujet) {
    SujetService.AddSujet(sujet);
    return "Sujet added successfully";
  }

  @PostMapping("/updateSujet/{id}")
  public String updateSujet(@PathVariable Long id, @RequestBody Sujet sujet) {
    SujetService.UpdateSujet(id, sujet);
    return "Sujet updated successfully";
  }

  @PostMapping("/deleteSujet/{id}")
  public String deleteSujet(@PathVariable Long id) {
    SujetService.DeleteSujet(id);
    return "sujet deleted successfully";
  }

  @GetMapping("/getSujetByProfId/{id}")
  public List<SujetDTO> getSujets(@PathVariable Long id) {
    try {
      return SujetService.getSujetByProfesseurId(id).stream()
              .map(sujet -> new SujetDTO(
                      sujet.getId(),
                      sujet.getTitre(),
                      sujet.getDescription(),
                      sujet.getThematique()
              ))
              .collect(Collectors.toList());
    } catch (RuntimeException e) {
      log.error("Error fetching subjects for professor with id: " + id, e);
      return List.of();
    }
  }
  //list the entretien by professue id ( aka the candiddature preselectionner)
//  @GetMapping("/entretien/{id}")



}
