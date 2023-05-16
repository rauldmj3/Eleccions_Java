package Tablas;

import DAO.VotsCandidaturesCaDAODB;

public class VotsCandidaturesCa extends VotsCandidaturesCaDAODB {
    Candidatures candidatura;
    ComAutonoma comAutonoma;
    int vots;

    // Constructors
    public VotsCandidaturesCa(ComAutonoma comAutonoma, Candidatures candidatura) {
        this.candidatura = candidatura;
        this.comAutonoma = comAutonoma;
        set(0);
    }

    public VotsCandidaturesCa(ComAutonoma comAutonoma, Candidatures candidatura, int vots) {
        this.candidatura = candidatura;
        this.comAutonoma = comAutonoma;
        set(vots);
    }

    public VotsCandidaturesCa(VotsCandidaturesCa v) {
        this.candidatura = v.candidatura;
        this.comAutonoma = v.comAutonoma;
        set(v.vots);
    }

    public void set(int vots) {
        this.vots = vots;

    }

    @Override
    public String toString() {
        return "\nVots Candidatures Comunitat Autonoma:" +
                "\n\tComunitat Autonoma: " + comAutonoma.getNom() +
                "\n\tCandidatura: " + candidatura.getNom_curt() +
                "\n\tVots: " + vots;
    }

    // Setters & Getters

    public ComAutonoma getComunitat_aut_id() {
        return comAutonoma;
    }

    public void setComunitat_aut_id(ComAutonoma comAutonoma) {
        this.comAutonoma = comAutonoma;
    }

    public Candidatures getCandidatura_id() {
        return candidatura;
    }

    public void setCandidatura_id(Candidatures candidatura_id) {
        this.candidatura = candidatura_id;
    }

    public int getVots() {
        return vots;
    }

    public void setVots(int vots) {
        this.vots = vots;
    }
}
