package Tablas;

import DAO.VotsCandidaturesMunicipisDAODB;

public class VotsCandidaturesMunicipis extends VotsCandidaturesMunicipisDAODB {
    Municipi municipi;
    Candidatures candidatura;
    int eleccio_id,vots;
    // Constructors
    public VotsCandidaturesMunicipis(Municipi municipi,Candidatures candidatura) {
        this.candidatura = candidatura;
        this.municipi= municipi;
        set(0,0);
    }

    public VotsCandidaturesMunicipis(Municipi municipi,Candidatures candidatura,int vots,int eleccio_id) {
        this.eleccio_id=eleccio_id;
        this.candidatura = candidatura;
        this.municipi=municipi;
        this.vots=vots;
    }

    public VotsCandidaturesMunicipis(VotsCandidaturesMunicipis v) {
        this.candidatura = v.candidatura;
        this.municipi= v.municipi;
        set(v.vots,v.eleccio_id);
    }

    public void set(int vots,int eleccio_id) {
        this.vots=vots;
        this.eleccio_id=eleccio_id;

    }

    @Override
    public String toString() {
        return "\nVots Candidatures Municipis:" +
                "\n\tEleccio ID: " + eleccio_id +
                "\n\tMunicipi: " + municipi.getNom() +
                "\n\tCandidatura:" + candidatura.getNom_curt() +
                "\n\tCots:" + vots;
    }

    // Setters & Getters

    public int getEleccio_id() {
        return eleccio_id;
    }

    public void setEleccio_id(int eleccio_id) {
        this.eleccio_id = eleccio_id;
    }

    public Municipi getMunicipi_() {
        return municipi;
    }

    public void setMunicipi(Municipi municipi) {
        this.municipi = municipi;
    }

    public Candidatures getCandidatura() {
        return candidatura;
    }

    public void setCandidatura(Candidatures candidatura) {
        this.candidatura = candidatura;
    }

    public int getVots() {
        return vots;
    }

    public void setVots(int vots) {
        this.vots = vots;
    }
}
