package DAO;

import java.sql.*;

import Tablas.*;


public class MunicipiDAODB implements DAODB<Municipi> {
    String taula = "municipis";

    // CRUD
    public void create(Municipi c, Connection conn) throws SQLException {

        String query = "INSERT INTO " + taula + " (nom,codi_ine,provincia_id,districte) VALUES (?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, c.getNom());
        statement.setString(2, c.getCodi_ine());
        statement.setInt(3,c.getProvincia().getProvincia_id());
        statement.setString(4,c.getDistricte());
        statement.execute();
        statement.close();
    }

    public boolean read(Municipi c, Connection conn) throws SQLException {
        Municipi er = read(c.getMunicipi_id(), conn);
        if (er == null) return false;
        c.set(er.getNom(), er.getCodi_ine(),er.getProvincia(),er.getDistricte());
        return true;
    }

    public Municipi read(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + taula + " WHERE municipi_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultat = statement.executeQuery();
        if (resultat == null) {
            statement.close();
            return null;
        }
        resultat.next();
        String nom;
        String codi_ine;
        int provincia_id;
        String districte;
        try {
            nom= resultat.getString("nom");
            codi_ine= resultat.getString("codi_ine");
            provincia_id= resultat.getInt("provincia_id");
            districte=resultat.getString("districte");
        }catch (Exception e){
            return null;
        }
        resultat.close();
        statement.close();
        return new Municipi(id,nom,codi_ine,new Provincia(provincia_id),districte);
    }

    public void update(Municipi c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET codi_ine= ? ,nom= ? ,provincia_id= ?,districte= ? WHERE municipi_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, c.getCodi_ine());
        statement.setString(2, c.getNom());
        statement.setInt(3, c.getProvincia().getProvincia_id());
        statement.setString(4,c.getDistricte());
        statement.setInt(5,c.getMunicipi_id());
        statement.execute();
        statement.close();
    }

    public void delete(Municipi c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE municipi_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getMunicipi_id());
        statement.execute();
        statement.close();
    }

    // ALTRES DAO
    public boolean exists(Municipi c, Connection conn) throws SQLException {
        return read(c, conn);
    }

}


