package DAO;

import java.sql.*;
import Tablas.*;

public class VotsCandidaturesCaDAODB implements DAODB<VotsCandidaturesCa>{
    String taula= "vots_candidatures_ca";
    // CRUD
    public void create(VotsCandidaturesCa c, Connection conn) throws SQLException {
        String query = "INSERT INTO " + taula + " (comunitat_aut_id,candidatura_id,vots) VALUES (?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,c.getComunitat_aut().getComAutonoma_id());
        statement.setInt(2,c.getCandidatura().getCandidatura_id());
        statement.setInt(3,c.getVots());
        statement.execute();
        statement.close();
    }


    public boolean read(VotsCandidaturesCa c,Connection conn) throws SQLException {
        VotsCandidaturesCa er = read(c.getComunitat_aut().getComAutonoma_id(),c.getCandidatura().getCandidatura_id(),conn);
        if (er == null) return false;
        c.set(er.getVots());
        return true;
    }

    public VotsCandidaturesCa read(int comunitat_id,int candidatura_id, Connection conn) throws SQLException {
        String query = "SELECT comunitat_aut_id,candidatura_id,vots FROM " + taula + " WHERE comunitat_aut_id=? AND candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,comunitat_id);
        statement.setInt(2,candidatura_id);
        statement.executeUpdate();
        ResultSet resultat= statement.executeQuery(query);
        if (resultat == null) {
            statement.close();
            return null;
        }
        resultat.next();
        ComAutonoma comAutonoma=null;
        Candidatures candidatures=null;
        int vots;
        try {
            comAutonoma = comAutonoma.read(resultat.getInt("provincia_id"),conn);
            candidatures = candidatures.read(resultat.getInt("candidatura_id"),conn);
            vots=  resultat.getInt("vots");
        }catch (Exception e){
            return null;
        }
        resultat.close();
        statement.close();
        return new VotsCandidaturesCa(comAutonoma,candidatures,vots);
    }

    public void update(VotsCandidaturesCa c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET vots=? WHERE comunitat_aut_id=? AND candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,c.getVots());
        statement.setInt(2,c.getComunitat_aut().getComAutonoma_id());
        statement.setInt(3,c.getCandidatura().getCandidatura_id());
        statement.executeUpdate();
        statement.executeQuery(query);
        statement.close();
    }

    public void delete(VotsCandidaturesCa c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE comunitat_aut_id=? AND candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,c.getComunitat_aut().getComAutonoma_id());
        statement.setInt(2,c.getCandidatura().getCandidatura_id());
        statement.executeUpdate();
        statement.executeQuery(query);
        statement.close();
    }

    // ALTRES DAO
    public boolean exists(VotsCandidaturesCa c,Connection conn) throws SQLException {
        return read(c,conn);
    }
}
