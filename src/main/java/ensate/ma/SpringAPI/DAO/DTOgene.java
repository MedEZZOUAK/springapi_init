package ensate.ma.SpringAPI.DAO;

import java.sql.Date;

public class DTOgene {
    Long idcandidature;
    Long idcandidat;
    Long idprof;
    Date date;
    String nomprenomprof;
    String nomprenomcandidat;
    String titresujet;
    String nomstructure;
    String etablissement;
    String domaine;

    // Constructor
    public DTOgene(Long idcandidature, String nomprenomcandidat, String nomprenomprof,
                   String titresujet, String nomstructure, String etablissement, String domaine, Long idcandidat, Long idprof, Date date) {
        this.idcandidature = idcandidature;
        this.nomprenomcandidat = nomprenomcandidat;
        this.nomprenomprof = nomprenomprof;
        this.titresujet = titresujet;
        this.nomstructure = nomstructure;
        this.etablissement = etablissement;
        this.domaine = domaine;
        this.idcandidat = idcandidat;
        this.idprof = idprof;
        this.date = date;
    }

    // Getters and Setters
    public Long getIdcandidature() {
        return idcandidature;
    }

    public void setIdcandidature(Long idcandidature) {
        this.idcandidature = idcandidature;
    }

    public String getNomprenomcandidat() {
        return nomprenomcandidat;
    }

    public void setNomprenomcandidat(String nomprenomcandidat) {
        this.nomprenomcandidat = nomprenomcandidat;
    }

    public String getNomprenomprof() {
        return nomprenomprof;
    }

    public void setNomprenomprof(String nomprenomprof) {
        this.nomprenomprof = nomprenomprof;
    }

    public String getTitresujet() {
        return titresujet;
    }

    public void setTitresujet(String titresujet) {
        this.titresujet = titresujet;
    }

    public String getNomstructure() {
        return nomstructure;
    }

    public void setNomstructure(String nomstructure) {
        this.nomstructure = nomstructure;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public Long getIdcandidat() {
        return idcandidat;
    }

    public void setIdcandidat(Long idcandidat) {
        this.idcandidat = idcandidat;
    }

    public Long getIdprof() {
        return idprof;
    }

    public void setIdprof(Long idprof) {
        this.idprof = idprof;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
