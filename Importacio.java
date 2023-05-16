import Tablas.*;

import java.sql.*;
import java.util.HashMap;

public class Importacio {
    public static HashMap<Integer, Persona> importPersona() {
        HashMap<Integer, Persona> persones = new HashMap<>();
        try {
            int i = 1;
            Connection conn = Conexio.conectar();
            String sql = "SELECT * FROM persones ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery(sql);

            while (resultat.next()) {
                int id = resultat.getInt("persona_id");
                String nom = resultat.getString("nom");
                String cog1 = resultat.getString("cog1");
                String cog2 = resultat.getString("cog2");
                String sexe = resultat.getString("sexe");
                Date data_naix = resultat.getDate("data_naixement");
                String dni = resultat.getString("dni");
                Persona persona = new Persona(id, nom, cog1, cog2, sexe, data_naix, dni);
                persones.put(i, persona);
                i++;
            }
            resultat.close();
            stmt.close();
            Conexio.desconectar(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return persones;
    }
    public static HashMap<Integer, Municipi> importMunicipis(HashMap<Integer, Provincia> provincies) {
        HashMap<Integer, Municipi> municipis = new HashMap<>();
        try {
            int i = 1;
            Connection conn = Conexio.conectar();
            String sql = "SELECT * FROM municipis ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery(sql);

            while (resultat.next()) {
                int id = resultat.getInt("municipi_id");
                String nom = resultat.getString("nom");
                String codi_ine = resultat.getString("codi_ine");
                int provincia_id = resultat.getInt("provincia_id");
                Provincia provincia = provincies.get(provincia_id);
                String districte = resultat.getString("districte");

                Municipi municipi = new Municipi(id, nom, codi_ine, provincia, districte);
                municipis.put(i, municipi);
                i++;
            }
            resultat.close();
            stmt.close();
            Conexio.desconectar(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return municipis;
    }
    public static HashMap<Integer, Provincia> importProvincies(HashMap<Integer, ComAutonoma> comAutonomes) {
        HashMap<Integer, Provincia> provincies = new HashMap<>();
        try {
            int i = 1;
            Connection conn = Conexio.conectar();
            String sql = "SELECT * FROM provincies";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery(sql);

            while (resultat.next()) {
                int id = resultat.getInt("provincia_id");
                int comAutonoma_id = resultat.getInt("comunitat_aut_id");
                ComAutonoma comAutonoma = comAutonomes.get(comAutonoma_id);
                String nom = resultat.getString("nom");
                String codi_ine = resultat.getString("codi_ine");
                int num_escons = resultat.getInt("num_escons");

                Provincia provincia = new Provincia(id, comAutonoma, nom, codi_ine, num_escons);
                provincies.put(i, provincia);
                i++;
            }
            resultat.close();
            stmt.close();
            Conexio.desconectar(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return provincies;
    }
    public static HashMap<Integer, ComAutonoma> importComAutonomes() {
        HashMap<Integer, ComAutonoma> comAutonomes = new HashMap<>();
        try {
            int i = 1;
            Connection conn = Conexio.conectar();
            String sql = "SELECT * FROM comunitats_autonomes ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery(sql);

            while (resultat.next()) {
                int id = resultat.getInt("comunitat_aut_id");
                String nom = resultat.getString("nom");
                String codi_ine = resultat.getString("codi_ine");

                ComAutonoma comAutonoma = new ComAutonoma(id, nom, codi_ine);
                comAutonomes.put(i, comAutonoma);
                i++;
            }
            resultat.close();
            stmt.close();
            Conexio.desconectar(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return comAutonomes;
    }
    public static HashMap<Integer, Candidatures> importCandidatures() {
        HashMap<Integer, Candidatures> candidatures = new HashMap<>();
        try {
            int i = 1;
            Connection conn = Conexio.conectar();
            String sql = "SELECT * FROM candidatures ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery(sql);

            while (resultat.next()) {
                int id = resultat.getInt("candidatura_id");
                int eleccio_id = resultat.getInt("eleccio_id");
                String codi_candidatura = resultat.getString("codi_candidatura");
                String nom_curt = resultat.getString("nom_curt");
                String nom_llarg = resultat.getString("nom_llarg");
                String codi_acumulacio_provincia = resultat.getString("codi_acumulacio_provincia");
                String codi_acumulacio_ca = resultat.getString("codi_acumulacio_ca");
                String codi_acumulacio_nacional = resultat.getString("codi_acumulacio_nacional");

                Candidatures candidatura = new Candidatures(id, eleccio_id, codi_candidatura, nom_curt, nom_llarg, codi_acumulacio_provincia, codi_acumulacio_ca, codi_acumulacio_nacional);
                candidatures.put(i, candidatura);
                i++;
            }
            resultat.close();
            stmt.close();
            Conexio.desconectar(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidatures;
    }
    public static HashMap<Integer, VotsCandidaturesCa> importVotsCandCA(HashMap<Integer, Candidatures> candidatures, HashMap<Integer, ComAutonoma> comAutonomes) {
        HashMap<Integer, VotsCandidaturesCa> votsCandidaturesCA = new HashMap<>();
        try {
            int i = 1;
            Connection conn = Conexio.conectar();
            String sql = "SELECT * FROM vots_candidatures_ca ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery(sql);

            while (resultat.next()) {
                int comunitat_aut_id = resultat.getInt("comunitat_aut_id");
                ComAutonoma comAutonoma = comAutonomes.get(comunitat_aut_id);
                int candidatura_id = resultat.getInt("candidatura_id");
                Candidatures candidatura = candidatures.get(candidatura_id);
                int vots = resultat.getInt("vots");

                VotsCandidaturesCa votsCandidaturesCa = new VotsCandidaturesCa(comAutonoma, candidatura, vots);
                votsCandidaturesCA.put(i, votsCandidaturesCa);
                i++;
            }
            resultat.close();
            stmt.close();
            Conexio.desconectar(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return votsCandidaturesCA;
    }
    public static HashMap<Integer, VotsCandidaturesMunicipis> importVotsCandMunicipis(HashMap<Integer, Candidatures> candidatures, HashMap<Integer, Municipi> municipis) {
        HashMap<Integer, VotsCandidaturesMunicipis> votsCandidaturesMunicipis = new HashMap<>();
        try {
            int i = 1;
            Connection conn = Conexio.conectar();
            String sql = "SELECT * FROM vots_candidatures_municipis ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery(sql);

            while (resultat.next()) {
                int eleccio_id = resultat.getInt("eleccio_id");
                int municipi_id = resultat.getInt("municipi_id");
                Municipi municipi = municipis.get(municipi_id);
                int candidatura_id = resultat.getInt("candidatura_id");
                Candidatures candidatura = candidatures.get(candidatura_id);
                int vots = resultat.getInt("vots");
                if (municipi_id <1||municipi_id >municipis.size() || candidatura_id <1||candidatura_id >candidatures.size()) {
                    resultat.next();
                }else {
                    VotsCandidaturesMunicipis votsCandidaturaMunicipi = new VotsCandidaturesMunicipis(municipi, candidatura, vots, eleccio_id);
                    votsCandidaturesMunicipis.put(i, votsCandidaturaMunicipi);
                    i++;
                }
            }
            resultat.close();
            stmt.close();
            Conexio.desconectar(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return votsCandidaturesMunicipis;
    }
    public static HashMap<Integer, VotsCandidaturesProvincies> importVotsCandProvincies(HashMap<Integer, Candidatures> candidatures, HashMap<Integer, Provincia> provincies) {
        HashMap<Integer, VotsCandidaturesProvincies> votsCandidaturesProvincies = new HashMap<>();
        try {
            int i = 1;
            Connection conn = Conexio.conectar();
            String sql = "SELECT * FROM vots_candidatures_provincies ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery(sql);

            while (resultat.next()) {

                int provincia_id = resultat.getInt("provincia_id");
                Provincia provincia = provincies.get(provincia_id);
                int candidatura_id = resultat.getInt("candidatura_id");
                Candidatures candidatura = candidatures.get(candidatura_id);
                int vots = resultat.getInt("vots");
                int candidats_obtinguts = resultat.getInt("candidats_obtinguts");
                if (provincia_id ==0 || candidatura_id ==0) {
                    resultat.next();
                }else {
                    VotsCandidaturesProvincies votsCandidaturaProvincia = new VotsCandidaturesProvincies(provincia, candidatura, vots, candidats_obtinguts);
                    votsCandidaturesProvincies.put(i, votsCandidaturaProvincia);
                    i++;
                }
            }
            resultat.close();
            stmt.close();
            Conexio.desconectar(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return votsCandidaturesProvincies;
    }
}
