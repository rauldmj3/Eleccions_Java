package DAO;

import java.sql.*;

import Tablas.*;


public class ProvinciaDAODB implements DAODB<Provincia> {
    String taula = "provincies";

    // CRUD
    public void create(Provincia c, Connection conn) throws SQLException {

        String query = "INSERT INTO " + taula + " (comunitat_aut_id,nom,codi_ine,num_escons) VALUES (?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getComAutonoma().getComAutonoma_id());
        statement.setString(2, c.getNom());
        statement.setString(3, c.getCodi_ine());
        statement.setInt(4, c.getNum_escons());
        statement.execute();
        statement.close();
    }

    public boolean read(Provincia c, Connection conn) throws SQLException {
        Provincia er = read(c.getProvincia_id(), conn);
        if (er == null) return false;
        c.set( er.getComAutonoma(),er.getNom(),er.getCodi_ine(),er.getNum_escons());
        return true;
    }

    public Provincia read(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + taula + " WHERE provincia_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultat = statement.executeQuery();
        if (resultat == null) {
            statement.close();
            return null;
        }
        resultat.next();
        ComAutonoma comAutonoma=null;
        String nom;
        String codi_ine;
        int num_escons;
        try {
            comAutonoma= comAutonoma.read(resultat.getInt("comunitat_aut_id"),conn);
            nom = resultat.getString("nom");
            codi_ine= resultat.getString("codi_ine");
            num_escons = resultat.getInt("num_escons");
        }catch (Exception e){
            return null;
        }
        resultat.close();
        statement.close();
        return new Provincia(id,comAutonoma,nom,codi_ine,num_escons);
    }

    public void update(Provincia c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET comunitat_aut_id= ? ,nom= ?,codi_ine= ? ,num_escons= ? WHERE provincia_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getComAutonoma().getComAutonoma_id());
        statement.setString(2, c.getNom());
        statement.setString(3, c.getCodi_ine());
        statement.setInt(4, c.getNum_escons());
        statement.setInt(5, c.getProvincia_id());
        statement.execute();
        statement.close();
    }

    public void delete(Provincia c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE provincia_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getProvincia_id());
        statement.execute();
        statement.close();
    }

    // ALTRES DAO
    public boolean exists(Provincia c, Connection conn) throws SQLException {
        return read(c, conn);
    }

}


