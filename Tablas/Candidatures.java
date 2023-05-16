package Tablas;

import DAO.CandidaturesDAODB;

public class Candidatures extends CandidaturesDAODB {

    int eleccio_id, candidatura_id;
    String codi_candidatura, nom_curt, nom_llarg, codi_acumulacio_provincia, codi_acumulacio_ca, codi_acumulacio_nacional;

    // Constructors
    public Candidatures(int candidatura_id) {
        this.candidatura_id = candidatura_id;
        set(0, null, null, null, null, null, null);
    }

    public Candidatures(int candidatura_id, int eleccio_id, String codi_candidatura, String nom_curt, String nom_llarg, String codi_acumulacio_provincia, String codi_acumulacio_ca, String codi_acumulacio_nacional) {
        this.candidatura_id = candidatura_id;
        set(eleccio_id, codi_candidatura, nom_curt, nom_llarg, codi_acumulacio_provincia, codi_acumulacio_ca, codi_acumulacio_nacional);
    }

    public Candidatures(Candidatures c) {
        this.candidatura_id = c.candidatura_id;
        set(c.eleccio_id, c.codi_candidatura, c.nom_curt, c.nom_llarg, c.codi_acumulacio_provincia, c.codi_acumulacio_ca, c.codi_acumulacio_nacional);
    }

    public void set(int eleccio_id, String codi_candidatura, String nom_curt, String nom_llarg, String codi_acumulacio_provincia, String codi_acumulacio_ca, String codi_acumulacio_nacional) {
        this.eleccio_id = eleccio_id;
        this.codi_candidatura = codi_candidatura;
        this.nom_curt = nom_curt;
        this.nom_llarg = nom_llarg;
        this.codi_acumulacio_provincia = codi_acumulacio_provincia;
        this.codi_acumulacio_ca = codi_acumulacio_ca;
        this.codi_acumulacio_nacional = codi_acumulacio_nacional;
    }

    @Override
    public String toString() {
        return "\nCandidatures:" +
                "\n\tEleccio ID: " + eleccio_id +
                "\n\tCandidatura ID: " + candidatura_id +
                "\n\tCodi Candidatura: " + codi_candidatura +
                "\n\tNom curt: " + nom_curt +
                "\n\tNom llarg: " + nom_llarg +
                "\n\tCodi acumulacio provincia: " + codi_acumulacio_provincia +
                "\n\tCodi acumulacio comunitat autonoma: " + codi_acumulacio_ca +
                "\n\tCodi acumulacio nacional: " + codi_acumulacio_nacional;
    }

// Setters & Getters

    public int getCandidatura_id() {
        return candidatura_id;
    }

    public void setCandidatura_id(int candidatura_id) {
        this.candidatura_id = candidatura_id;
    }

    public int getEleccio_id() {
        return eleccio_id;
    }

    public void setEleccio_id(int eleccio_id) {
        this.eleccio_id = eleccio_id;
    }

    public String getCodi_candidatura() {
        return codi_candidatura;
    }

    public void setCodi_candidatura(String codi_candidatura) {
        this.codi_candidatura = codi_candidatura;
    }

    public String getNom_curt() {
        return nom_curt;
    }

    public void setNom_curt(String nom_curt) {
        this.nom_curt = nom_curt;
    }

    public String getNom_llarg() {
        return nom_llarg;
    }

    public void setNom_llarg(String nom_llarg) {
        this.nom_llarg = nom_llarg;
    }

    public String getCodi_acumulacio_provincia() {
        return codi_acumulacio_provincia;
    }

    public void setCodi_acumulacio_provincia(String codi_acumulacio_provincia) {
        this.codi_acumulacio_provincia = codi_acumulacio_provincia;
    }

    public String getCodi_acumulacio_ca() {
        return codi_acumulacio_ca;
    }

    public void setCodi_acumulacio_ca(String codi_acumulacio_ca) {
        this.codi_acumulacio_ca = codi_acumulacio_ca;
    }

    public String getCodi_acumulacio_nacional() {
        return codi_acumulacio_nacional;
    }

    public void setCodi_acumulacio_nacional(String codi_acumulacio_nacional) {
        this.codi_acumulacio_nacional = codi_acumulacio_nacional;
    }
}
