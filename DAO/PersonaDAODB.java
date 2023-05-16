package DAO;

import java.sql.*;

import Tablas.*;


public class PersonaDAODB implements DAODB<Persona> {
    String taula = "persones";

    // CRUD
    public void create(Persona c, Connection conn) throws SQLException {

        String query = "INSERT INTO " + taula + " (nom,cog1,cog2,sexe,data_naixement,dni) VALUES (?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, c.getNom());
        statement.setString(2, c.getCog1());
        statement.setString(3, c.getCog2());
        statement.setString(4, c.getSexe());
        statement.setDate(5, c.getData_naix());
        statement.setString(6, c.getDni());
        statement.execute();
        statement.close();
    }

    public boolean read(Persona c, Connection conn) throws SQLException {
        Persona er = read(c.getPersona_id(), conn);
        if (er == null) return false;
        c.set(er.getNom(), er.getCog1(), er.getCog2(), er.getSexe(),er.getData_naix(),er.getDni());
        return true;
    }

    public Persona read(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + taula + " WHERE persona_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultat = statement.executeQuery();
        if (resultat == null) {
            statement.close();
            return null;
        }
        resultat.next();
        String nom;
        String cog1;
        String cog2;
        String sexe;
        Date data_naix;
        String dni;
        try {
            nom= resultat.getString("nom");
            cog1= resultat.getString("cog1");
            cog2= resultat.getString("cog2");
            sexe=resultat.getString("sexe");
            data_naix=resultat.getDate("data_naixement");
            dni=resultat.getString("dni");

        }catch (Exception e){
            return null;
        }
        resultat.close();
        statement.close();
        return new Persona(id,nom,cog1,cog2,sexe,data_naix,dni);
    }

    public void update(Persona c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET nom= ? ,cog1= ? ,cog2= ? ,sexe= ? ,data_naixement= ? ,dni= ? WHERE persona_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, c.getNom());
        statement.setString(2, c.getCog1());
        statement.setString(3, c.getCog2());
        statement.setString(4, c.getSexe());
        statement.setDate(5, c.getData_naix());
        statement.setString(6, c.getDni());
        statement.setInt(7,c.getPersona_id());
        statement.execute();
        statement.close();
    }

    public void delete(Persona c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE persona_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getPersona_id());
        statement.execute();
        statement.close();
    }

    // ALTRES DAO
    public boolean exists(Persona c, Connection conn) throws SQLException {
        return read(c, conn);
    }

}


