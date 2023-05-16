package Tablas;

import DAO.ProvinciaDAODB;

public class Provincia extends ProvinciaDAODB {
    int provincia_id;
    ComAutonoma comAutonoma;
    String nom;
    String codi_ine;
    int num_escons;

    public Provincia(int provincia_id, ComAutonoma comAutonoma, String nom, String codi_ine, int num_escons) {
        this.provincia_id = provincia_id;
        set(comAutonoma,nom,codi_ine,num_escons);
    }

    public Provincia(int provincia_id){
        this.provincia_id = provincia_id;
        set(null,null,null,0);
    }

    public Provincia(Provincia v) {
        this.provincia_id = v.provincia_id;
        set(v.getComAutonoma(),v.getNom(),v.getCodi_ine(),v.getNum_escons());
    }

    public void set(ComAutonoma comAutonoma, String nom, String codi_ine, int num_escons){
        this.comAutonoma = comAutonoma;
        this.nom = nom;
        this.codi_ine = codi_ine;
        this.num_escons = num_escons;
    }

    public int getProvincia_id() {
        return provincia_id;
    }

    public ComAutonoma getComAutonoma() {
        return comAutonoma;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi_ine() {
        return codi_ine;
    }

    public int getNum_escons() {
        return num_escons;
    }

    @Override
    public String toString() {
        return "\nProvincia:" +
                "\n\tID: " + provincia_id +
                "\n\tComunitat Autonoma: " + comAutonoma.getNom() +
                "\n\tNom: " + nom +
                "\n\tCodi INE: " + codi_ine +
                "\n\tNumero d'escons: " + num_escons;
    }
}
