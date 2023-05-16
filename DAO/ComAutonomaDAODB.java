package DAO;

import java.sql.*;

import Tablas.*;


public class ComAutonomaDAODB implements DAODB<ComAutonoma> {
    String taula = "comunitats_autonomes";

    // CRUD
    public void create(ComAutonoma c, Connection conn) throws SQLException {

        String query = "INSERT INTO " + taula + " (nom,codi_ine) VALUES (?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, c.getNom());
        statement.setString(2, c.getCodi_ine());
        statement.execute();
        statement.close();
    }

    public boolean read(ComAutonoma c, Connection conn) throws SQLException {
        ComAutonoma er = read(c.getComAutonoma_id(), conn);
        if (er == null) return false;
        c.set(er.getNom(), er.getCodi_ine());
        return true;
    }

    public ComAutonoma read(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + taula + " WHERE comunitat_aut_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultat = statement.executeQuery();
        if (resultat == null) {
            statement.close();
            return null;
        }
        resultat.next();
        String codi_ine,nom;
        try {
             nom= resultat.getString("nom");
             codi_ine= resultat.getString("codi_ine");
        }catch (Exception e){
            return null;
        }
        resultat.close();
        statement.close();
        return new ComAutonoma(id,nom,codi_ine);
    }

    public void update(ComAutonoma c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET codi_ine= ? ,nom= ? WHERE comunitat_aut_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, c.getCodi_ine());
        statement.setString(2, c.getNom());
        statement.setInt(3, c.getComAutonoma_id());
        statement.execute();
        statement.close();
    }

    public void delete(ComAutonoma c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE comunitat_aut_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getComAutonoma_id());
        statement.execute();
        statement.close();
    }

    // ALTRES DAO
    public boolean exists(ComAutonoma c, Connection conn) throws SQLException {
        return read(c, conn);
    }

}

