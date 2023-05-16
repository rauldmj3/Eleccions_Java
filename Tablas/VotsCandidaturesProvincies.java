package Tablas;

import DAO.VotsCandidaturesProvinciesDAODB;

public class VotsCandidaturesProvincies extends VotsCandidaturesProvinciesDAODB {
    Provincia provincia;
    Candidatures candidatura;
    int vots,candidats_obtinguts;
    // Constructors
    public VotsCandidaturesProvincies(Provincia provincia,Candidatures candidatura) {
        this.candidatura= candidatura;
        this.provincia= provincia;
        set(0,0);
    }

    public VotsCandidaturesProvincies(Provincia provincia,Candidatures candidatura,int vots,int candidats_obtinguts) {
        this.candidatura = candidatura;
        this.provincia= provincia;
        set(vots,candidats_obtinguts);
    }

    public VotsCandidaturesProvincies(VotsCandidaturesProvincies v) {
        this.candidatura = v.candidatura;
        this.provincia= v.provincia;
        set(v.vots,v.candidats_obtinguts);
    }

    public void set(int vots,int candidats_obtinguts) {
        this.vots=vots;
        this.candidats_obtinguts=candidats_obtinguts;

    }

    @Override
    public String toString() {
        return "\nVots Candidatures Provincies:" +
                "\n\tProvincia: " + provincia.getNom() +
                "\n\tCandidatura: " + candidatura.getNom_curt() +
                "\n\tVots: " + vots +
                "\n\tCandidats obtinguts: " + candidats_obtinguts;
    }


    // Setters & Getters

    public Provincia getProvincia_id() {
        return provincia;
    }

    public void setProvincia_id(Provincia provincia) {
        this.provincia = provincia;
    }

    public Candidatures getCandidatura_id() {
        return candidatura;
    }

    public void setCandidatura_id(Candidatures candidatura) {
        this.candidatura = candidatura;
    }

    public int getVots() {
        return vots;
    }

    public void setVots(int vots) {
        this.vots = vots;
    }

    public int getCandidats_obtinguts() {
        return candidats_obtinguts;
    }

    public void setCandidats_obtinguts(int candidats_obtinguts) {
        this.candidats_obtinguts = candidats_obtinguts;
    }
}
