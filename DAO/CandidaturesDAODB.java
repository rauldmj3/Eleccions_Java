package DAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import Tablas.*;


public class CandidaturesDAODB implements DAODB<Candidatures> {
    String taula = "candidatures";

    // CRUD
    public void create(Candidatures c, Connection conn) throws SQLException {

        String query = "INSERT INTO " + taula + " (eleccio_id,codi_candidatura,nom_curt,nom_llarg,codi_acumulacio_provincia,codi_acumulacio_ca,codi_acumulacio_nacional) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getEleccio_id());
        statement.setString(2, c.getCodi_candidatura());
        statement.setString(3, c.getNom_curt());
        statement.setString(4, c.getNom_llarg());
        statement.setString(5, c.getCodi_acumulacio_provincia());
        statement.setString(6, c.getCodi_acumulacio_ca());
        statement.setString(7, c.getCodi_acumulacio_nacional());
        statement.execute();
        statement.close();
    }

    public boolean read(Candidatures c, Connection conn) throws SQLException {
        Candidatures er = read(c.getCandidatura_id(), conn);
        if (er == null) return false;
        c.set(er.getEleccio_id(), er.getCodi_candidatura(), er.getNom_curt(), c.getNom_llarg(), c.getCodi_acumulacio_provincia(), c.getCodi_acumulacio_ca(), c.getCodi_acumulacio_nacional());
        return true;
    }

    public Candidatures read(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + taula + " WHERE candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultat = statement.executeQuery();
        if (resultat == null) {
            statement.close();
            return null;
        }
        resultat.next();
        int eleccio_id;
        String codi_candidatura, nom_curt, nom_llarg, codi_acumulacio_provincia, codi_acumulacio_ca, codi_acumulacio_nacional;

        try {
            eleccio_id = resultat.getInt("eleccio_id");
            codi_candidatura=resultat.getString("codi_candidatura");
            nom_curt= resultat.getString("nom_curt");
            nom_llarg= resultat.getString("nom_llarg");
            codi_acumulacio_provincia =resultat.getString("codi_acumulacio_provincia"); ;
            codi_acumulacio_ca= resultat.getString("codi_acumulacio_ca");
            codi_acumulacio_nacional= resultat.getString("codi_acumulacio_nacional");
        }catch (Exception e){
            return null;
        }
        resultat.close();
        statement.close();
        return new Candidatures(id,eleccio_id, codi_candidatura, nom_curt, nom_llarg, codi_acumulacio_provincia, codi_acumulacio_ca, codi_acumulacio_nacional);
    }

    public void update(Candidatures c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET eleccio_id=?,codi_candidatura=?,nom_curt=?,nom_llarg=?,codi_acumulacio_provincia=?,codi_acumulacio_ca=?,codi_acumulacio_nacional=? WHERE candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getEleccio_id());
        statement.setString(2, c.getCodi_candidatura());
        statement.setString(3, c.getNom_curt());
        statement.setString(4, c.getNom_llarg());
        statement.setString(5, c.getCodi_acumulacio_provincia());
        statement.setString(6, c.getCodi_acumulacio_ca());
        statement.setString(7, c.getCodi_acumulacio_nacional());
        statement.setInt(8, c.getCandidatura_id());
        statement.execute();
        statement.close();
    }

    public void delete(Candidatures c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getCandidatura_id());
        statement.execute();
        statement.close();
    }

    // ALTRES DAO
    public boolean exists(Candidatures c, Connection conn) throws SQLException {
        return read(c, conn);
    }
}
