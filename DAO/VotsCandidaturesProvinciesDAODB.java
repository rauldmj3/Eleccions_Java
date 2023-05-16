package DAO;

import java.sql.*;
import Tablas.*;

public class VotsCandidaturesProvinciesDAODB implements DAODB<VotsCandidaturesProvincies>{

    String taula= "vots_candidatures_provincies";
    // CRUD
    public void create(VotsCandidaturesProvincies c, Connection conn) throws SQLException {
        String query = "INSERT INTO " + taula + " (provincia_id,candidatura_id,vots,candidats_obtinguts) VALUES (?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,c.getProvincia().getProvincia_id());
        statement.setInt(2,c.getCandidatura().getCandidatura_id());
        statement.setInt(3,c.getVots());
        statement.setInt(4,c.getCandidats_obtinguts());
        statement.execute();
        statement.close();
    }


    public boolean read(VotsCandidaturesProvincies c,Connection conn) throws SQLException {
        VotsCandidaturesProvincies er = read(c.getProvincia().getProvincia_id(),c.getCandidatura().getCandidatura_id(),conn);
        if (er == null) return false;
        c.set(er.getVots(),er.getCandidats_obtinguts());
        return true;
    }

    public VotsCandidaturesProvincies read(int provincia_id,int candidatura_id, Connection conn) throws SQLException {
        String query = "SELECT provincia_id,candidatura_id,vots,candidats_obtinguts FROM " + taula + " WHERE provincia_id=? AND candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,provincia_id);
        statement.setInt(2,candidatura_id);
        statement.executeUpdate();
        ResultSet resultat= statement.executeQuery(query);
        if (resultat == null) {
            statement.close();
            return null;
        }
        resultat.next();
        Provincia provincia=null;
        Candidatures candidatures=null;
        int vots,candidats_obtinguts;
        try {
            provincia = provincia.read(resultat.getInt("provincia_id"),conn);
            candidatures = candidatures.read(resultat.getInt("candidatura_id"),conn);
            vots=  resultat.getInt("vots");
            candidats_obtinguts = resultat.getInt("candidats_obtinguts");
        }catch (Exception e){
            return null;
        }
        resultat.close();
        statement.close();
        return new VotsCandidaturesProvincies(provincia,candidatures,vots,candidats_obtinguts);
    }

    public void update(VotsCandidaturesProvincies c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET vots=?,candidats_obtinguts=? WHERE provincia_id=? AND candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,c.getVots());
        statement.setInt(2,c.getCandidats_obtinguts());
        statement.setInt(3,c.getProvincia().getProvincia_id());
        statement.setInt(4,c.getCandidatura().getCandidatura_id());
        statement.executeUpdate();
        statement.executeQuery(query);
        statement.close();
    }

    public void delete(VotsCandidaturesProvincies c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE provincia_id=? AND candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,c.getProvincia().getProvincia_id());
        statement.setInt(2,c.getCandidatura().getCandidatura_id());
        statement.executeUpdate();
        statement.executeQuery(query);
        statement.close();
    }

    // ALTRES DAO
    public boolean exists(VotsCandidaturesProvincies c,Connection conn) throws SQLException {
        return read(c,conn);
    }
}

