package Tablas;

import DAO.ComAutonomaDAODB;

public class ComAutonoma extends ComAutonomaDAODB {
    int comAutonoma_id;
    String nom;
    String codi_ine;

    public ComAutonoma(int comAutonoma_id, String nom, String codi_ine) {
        this.comAutonoma_id = comAutonoma_id;
        this.nom = nom;
        this.codi_ine = codi_ine;
    }

    public int getComAutonoma_id() {
        return comAutonoma_id;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi_ine() {
        return codi_ine;
    }

    public void setComAutonoma_id(int comAutonoma_id) {
        this.comAutonoma_id = comAutonoma_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCodi_ine(String codi_ine) {
        this.codi_ine = codi_ine;
    }
    public void set( String nom, String codi_ine) {
        this.nom = nom;
        this.codi_ine = codi_ine;

    }
    @Override
    public String toString() {
        return "\nComunitat autonoma: " +
                "\n\tID: " + comAutonoma_id +
                "\n\tNom: " + nom +
                "\n\tCodi INE:" + codi_ine;
    }
}
