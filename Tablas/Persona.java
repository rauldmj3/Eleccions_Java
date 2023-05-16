package Tablas;

import DAO.PersonaDAODB;

import java.io.IOException;
import java.sql.*;

public class Persona extends PersonaDAODB {
    int persona_id;
    String nom;
    String cog1;
    String cog2;
    String sexe;
    Date data_naix;
    String dni;

    public Persona(int persona_id, String nom, String cog1, String cog2, String sexe, Date data_naix, String dni) {
        this.persona_id = persona_id;
        this.nom = nom;
        this.cog1 = cog1;
        this.cog2 = cog2;
        this.sexe = sexe;
        this.data_naix = data_naix;
        this.dni = dni;
    }
    public void set(String nom, String cog1, String cog2, String sexe, Date data_naix, String dni){
        this.nom = nom;
        this.cog1 = cog1;
        this.cog2 = cog2;
        this.sexe = sexe;
        this.data_naix = data_naix;
        this.dni = dni;
    }
    public int getPersona_id() {
        return persona_id;
    }

    public String getNom() {
        return nom;
    }

    public String getCog1() {
        return cog1;
    }

    public String getCog2() {
        return cog2;
    }

    public String getSexe() {
        return sexe;
    }

    public Date getData_naix() {
        return data_naix;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "\nPersona:" +
                "\n\tID: " + persona_id +
                "\n\tNom: " + nom +
                "\n\tCognoms: " + cog1 + " " + cog2 +
                "\n\tSexe: " + sexe +
                "\n\tData naixement: " + data_naix +
                "\n\tDNI: " + dni;
    }
}
