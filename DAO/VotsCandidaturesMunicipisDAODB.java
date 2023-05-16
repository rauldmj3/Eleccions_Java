package DAO;

import Tablas.*;
import java.sql.*;

public class VotsCandidaturesMunicipisDAODB implements DAODB<VotsCandidaturesMunicipis> {
    String taula= "vots_candidatures_municipis";

    @Override
    public void create(VotsCandidaturesMunicipis c, Connection conn) throws SQLException {
        String query = "INSERT INTO " + taula + " (eleccio_id,municipi_id,candidatura_id,vots) VALUES (?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,c.getEleccio_id());
        statement.setInt(2,c.getMunicipi_().getMunicipi_id());
        statement.setInt(3,c.getCandidatura().getCandidatura_id());
        statement.setInt(4,c.getVots());
        statement.execute();
        statement.close();
    }

    @Override
    public boolean read(VotsCandidaturesMunicipis c, Connection conn) throws SQLException {
        VotsCandidaturesMunicipis er = read(c.getMunicipi_().getMunicipi_id(),c.getCandidatura().getCandidatura_id(),conn);
        if (er == null) return false;
        c.set(er.getVots(),er.getEleccio_id());
        return false;
    }
    public VotsCandidaturesMunicipis read(int municipi_id,int candidatura_id, Connection conn) throws SQLException {
        String query = "SELECT eleccio_id,municipi_id,candidatura_id,vots FROM " + taula + " WHERE municipi_id=? AND candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,municipi_id);
        statement.setInt(2,candidatura_id);
        statement.executeUpdate();
        ResultSet resultat= statement.executeQuery(query);
        if (resultat == null) {
            statement.close();
            return null;
        }
        resultat.next();
        Municipi municipi=null;
        Candidatures candidatures=null;
        int vots,eleccio_id;
        try {
            municipi = municipi.read(resultat.getInt("provincia_id"),conn);
            candidatures = candidatures.read(resultat.getInt("candidatura_id"),conn);
            vots=  resultat.getInt("vots");
            eleccio_id = resultat.getInt("candidats_obtinguts");
        }catch (Exception e){
            return null;
        }
        resultat.close();
        statement.close();
        return new VotsCandidaturesMunicipis(municipi,candidatures,vots,eleccio_id);
    }
    @Override
    public void update(VotsCandidaturesMunicipis c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET vots=?,eleccio_id=? WHERE municipi_id=? AND candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,c.getVots());
        statement.setInt(2,c.getEleccio_id());
        statement.setInt(3,c.getMunicipi_().getMunicipi_id());
        statement.setInt(4,c.getCandidatura().getCandidatura_id());
        statement.executeUpdate();
        statement.executeQuery(query);
        statement.close();
    }

    @Override
    public void delete(VotsCandidaturesMunicipis c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE municipi_id=? AND candidatura_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,c.getMunicipi_().getMunicipi_id());
        statement.setInt(2,c.getCandidatura().getCandidatura_id());
        statement.executeUpdate();
        statement.executeQuery(query);
        statement.close();
    }

    @Override
    public boolean exists(VotsCandidaturesMunicipis c, Connection conn) throws SQLException {
        return read(c,conn);
    }
}
