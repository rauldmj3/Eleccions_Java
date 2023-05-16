package Tablas;

import DAO.MunicipiDAODB;

public class Municipi extends MunicipiDAODB {
    int municipi_id;
    String nom;
    String codi_ine;
    Provincia provincia;
    String districte;

    public Municipi(int municipi_id, String nom, String codi_ine, Provincia provincia, String districte) {
        this.municipi_id = municipi_id;
        this.nom = nom;
        this.codi_ine = codi_ine;
        this.provincia = provincia;
        this.districte = districte;
    }

    public int getMunicipi_id() {
        return municipi_id;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi_ine() {
        return codi_ine;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public String getDistricte() {
        return districte;
    }
    public void set(String nom, String codi_ine, Provincia provincia, String districte){
        this.nom = nom;
        this.codi_ine = codi_ine;
        this.provincia = provincia;
        this.districte = districte;
    }
    @Override
    public String toString() {
        return "\nMunicipi: " +
                "\n\tID: " + municipi_id +
                "\n\tNom: " + nom +
                "\n\tCodi INE: " + codi_ine  +
                "\n\tProvincia: " + provincia.getNom() +
                "\n\tDistricte: " + districte ;
    }
}
