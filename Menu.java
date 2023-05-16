import Tablas.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    static HashMap<Integer, Persona> persones = Importacio.importPersona();
    static HashMap<Integer, Candidatures> candidatures = Importacio.importCandidatures();
    static HashMap<Integer, ComAutonoma> comAutonomes = Importacio.importComAutonomes();
    static HashMap<Integer, Provincia> provincies = Importacio.importProvincies(comAutonomes);
    static HashMap<Integer, Municipi> municipis = Importacio.importMunicipis(provincies);
    static HashMap<Integer, VotsCandidaturesMunicipis> votsCandidaturesMunicipis = Importacio.importVotsCandMunicipis(candidatures, municipis);
    static HashMap<Integer, VotsCandidaturesProvincies> votsCandidaturesProvincies = Importacio.importVotsCandProvincies(candidatures, provincies);
    static HashMap<Integer, VotsCandidaturesCa> votsCandidaturesCA = Importacio.importVotsCandCA(candidatures, comAutonomes);
    static Scanner scan = new Scanner(System.in);

    public static void menu() throws SQLException {
        int opcio;
        do {
            System.out.println("1.Mostrar taula completa");
            System.out.println("2.Insertar un registre");
            System.out.println("3.Eliminar un registre");
            System.out.println("4.Modificar un registre");
            System.out.println("5.Mostra un registre");
            System.out.println("0.Acabar");
            System.out.print("Selecciona l'opcio:");
            opcio = scan.nextInt();
            scan.nextLine();
            switch (opcio) {
                case 1:
                    mostrarTaula();
                    break;
                case 2:
                    insertarTaula();
                    break;
                case 3:
                    deteleTaula();
                    break;
                case 4:
                    updateTaula();
                    break;
                case 5:
                    mostrarUnRegistre();
                    break;
            }
        } while (opcio != 0);
    }

    public static void mostrarCodiTaules() {
        System.out.println("-----------------------------------------------");
        System.out.println("COM --> Comunitats Autonomes");
        System.out.println("PRO --> Provincies");
        System.out.println("MUN --> Municipis");
        System.out.println("PER --> Persones");
        System.out.println("CAN --> Candidatures");
        System.out.println("VCC --> Vots Candidatures Comunitat Autonoma");
        System.out.println("VCP --> Vots Candidatures Provincies");
        System.out.println("VCM --> Vots Candidatures Municipis");
        System.out.println("-----------------------------------------------");
    }

    public static void mostrarTaula() {
        String taula;
        do {
            mostrarCodiTaules();
            System.out.print("Quina taula vols mostrar(CAP per sortir):");
            taula = scan.nextLine();
            taula = taula.toUpperCase().substring(0, 3);
            switch (taula) {
                case "PRO":
                    System.out.println(provincies.values());
                    break;
                case "COM":
                    System.out.println(comAutonomes.values());
                    break;
                case "MUN":
                    System.out.println(municipis.values());
                    break;
                case "PER":
                    System.out.println(persones.values());
                    break;
                case "CAN":
                    System.out.println(candidatures.values());
                    break;
                case "VCP":
                    System.out.println(votsCandidaturesProvincies.values());
                    break;
                case "VCC":
                    System.out.println(votsCandidaturesCA.values());
                    break;
                case "VCM":
                    System.out.println(votsCandidaturesMunicipis.values());
                    break;
            }
        } while (!taula.equals("CAP"));
    }

    public static void insertarTaula() throws SQLException {
        String taula;
        String camps;
        String[] dades;
        int seguentRegistre;
        do {
            Connection conn = Conexio.conectar();
            mostrarCodiTaules();
            System.out.print("A quina taula vols insertar registres(CAP per sortir):");
            taula = scan.nextLine();
            taula = taula.toUpperCase().substring(0, 3);
            switch (taula) {
                case "PRO":
                    System.out.println("Introdueix per ordre els camps de la taula(provincia_id,comunitat_aut_id,nom,codi_ine,num_escons): ");
                    camps = scan.nextLine();
                    dades = camps.split(",");
                    ComAutonoma comAutonoma2 = comAutonomes.get(Integer.valueOf(dades[1]));
                    Provincia provincia2 = new Provincia(Integer.valueOf(dades[0]), comAutonoma2, dades[1], dades[2], Integer.valueOf(dades[4]));
                    seguentRegistre = count("provincies", conn) + 1;
                    provincies.put(seguentRegistre, provincia2);
                    provincies.get(seguentRegistre).create(provincia2, conn);
                    break;
                case "COM":
                    System.out.println("Introdueix per ordre els camps de la taula(comunitat_aut_id,nom,codi_ine): ");
                    camps = scan.nextLine();
                    dades = camps.split(",");
                    ComAutonoma comAutonoma1 = new ComAutonoma(Integer.valueOf(dades[0]), dades[1], dades[2]);
                    seguentRegistre = count("comunitats_autonomes", conn) + 1;
                    comAutonomes.put(seguentRegistre, comAutonoma1);
                    comAutonomes.get(seguentRegistre).create(comAutonoma1, conn);
                    break;
                case "MUN":
                    System.out.println("Introdueix per ordre els camps de la taula(municipi_id,nom,codi_ine,provincia_id,districte): ");
                    camps = scan.nextLine();
                    dades = camps.split(",");
                    Provincia provincia1 = provincies.get(Integer.valueOf(dades[3]));
                    Municipi municipi = new Municipi(Integer.valueOf(dades[0]), dades[1], dades[2], provincia1, dades[4]);
                    seguentRegistre = count("municipis", conn) + 1;
                    municipis.put(seguentRegistre, municipi);
                    municipis.get(seguentRegistre).create(municipi,conn);
                    break;
                case "PER":
                    System.out.println("Introdueix per ordre els camps de la taula(persona_id,nom,cog1,cog2,sexe,data_naixement,dni): ");
                    camps = scan.nextLine();
                    dades = camps.split(",");
                    Persona persona = new Persona(Integer.valueOf(dades[0]), dades[1], dades[2], dades[3], dades[4], Date.valueOf(dades[5]), dades[6]);
                    seguentRegistre = count("persones", conn) + 1;
                    persones.put(seguentRegistre, persona);
                    persones.get(seguentRegistre).create(persona,conn);
                    break;
                case "CAN":
                    System.out.println("Introdueix per ordre els camps de la taula(candidatura_id,eleccio_id,codi_candidatura,nom_curt,nom_llarg,codi_acumulacio_provincia,codi_acumulacio_ca,codi_acumulacio_nacional): ");
                    camps = scan.nextLine();
                    dades = camps.split(",");
                    Candidatures candidatura = new Candidatures(Integer.valueOf(dades[0]), Integer.valueOf(dades[1]), dades[2], dades[3], dades[4], dades[5], dades[6], dades[7]);
                    seguentRegistre = count("candidatures", conn) + 1;
                    candidatures.put(seguentRegistre, candidatura);
                    candidatures.get(seguentRegistre).create(candidatura, conn);
                    break;
                case "VCP":
                    System.out.println("Introdueix per ordre els camps de la taula(provincia_id,candidatura_id,vots,candidats_obtinguts): ");
                    camps = scan.nextLine();
                    dades = camps.split(",");
                    Provincia provincia = provincies.get(Integer.valueOf(dades[0]));
                    Candidatures candidatura3 = candidatures.get(Integer.valueOf(dades[1]));
                    VotsCandidaturesProvincies votsCandidaturesProvincia = new VotsCandidaturesProvincies(provincia, candidatura3, Integer.valueOf(dades[2]), Integer.valueOf(dades[3]));
                    seguentRegistre = count("vots_candidatures_provincies", conn) + 1;
                    votsCandidaturesProvincies.put(seguentRegistre, votsCandidaturesProvincia);
                    votsCandidaturesProvincies.get(seguentRegistre).create(votsCandidaturesProvincia, conn);
                    break;
                case "VCC":
                    System.out.println("Introdueix per ordre els camps de la taula(comunitat_id,candidatura_id,vots): ");
                    camps = scan.nextLine();
                    dades = camps.split(",");
                    ComAutonoma comAutonoma = comAutonomes.get(Integer.valueOf(dades[0]));
                    Candidatures candidatura1 = candidatures.get(Integer.valueOf(dades[1]));
                    VotsCandidaturesCa votsCandidaturesComunitat = new VotsCandidaturesCa(comAutonoma, candidatura1, Integer.valueOf(dades[2]));
                    seguentRegistre = count("vots_candidatures_ca", conn) + 1;
                    votsCandidaturesCA.put(seguentRegistre, votsCandidaturesComunitat);
                    votsCandidaturesCA.get(seguentRegistre).create(votsCandidaturesComunitat, conn);
                    break;
                case "VCM":
                    System.out.println("Introdueix per ordre els camps de la taula(municipi_id,candidatura_id,vots,eleccio_id): ");
                    camps = scan.nextLine();
                    dades = camps.split(",");
                    Municipi municipi1 = municipis.get(Integer.valueOf(dades[0]));
                    Candidatures candidatura2 = candidatures.get(Integer.valueOf(dades[1]));
                    VotsCandidaturesMunicipis votsCandidaturesMunicipi = new VotsCandidaturesMunicipis(municipi1, candidatura2, Integer.valueOf(dades[2]), Integer.valueOf(dades[3]));
                    seguentRegistre = count("vots_candidatures_municipis", conn) + 1;
                    votsCandidaturesMunicipis.put(seguentRegistre, votsCandidaturesMunicipi);
                    votsCandidaturesMunicipis.get(seguentRegistre).create(votsCandidaturesMunicipi,conn);
                    break;
            }
        } while (!taula.equals("CAP"));
    }

    public static void deteleTaula() throws SQLException {
        String taula;
        String[] dades;
        int id;
        do {
            Connection conn = Conexio.conectar();
            mostrarCodiTaules();
            System.out.print("De quina taula vols eliminar registres(CAP per sortir):");
            taula = scan.nextLine();
            taula = taula.toUpperCase().substring(0, 3);
            switch (taula) {
                case "PRO":
                    System.out.println("Introdueix la provincia_id, per eliminar una provincia: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > provincies.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        provincies.get(id).delete(provincies.get(id), conn);
                        provincies.remove(id);
                    }
                    break;
                case "COM":
                    System.out.println("Introdueix la comunitat_aut_id, per eliminar una comunitat autonoma: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > comAutonomes.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        comAutonomes.get(id).delete(comAutonomes.get(id), conn);
                        comAutonomes.remove(id);
                    }
                    break;
                case "MUN":
                    System.out.println("Introdueix el municipi_id, per eliminar un muncipi: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > municipis.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        municipis.get(id).delete(municipis.get(id),conn);
                        municipis.remove(id);
                    }
                    break;
                case "PER":
                    System.out.println("Introdueix la persona_id, per eliminar una persona: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > persones.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        persones.get(id).delete(persones.get(id),conn);
                        persones.remove(id);
                    }
                    break;
                case "CAN":
                    System.out.println("Introdueix la candidatura_id, per eliminar una candidatura: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > candidatures.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        candidatures.get(id).delete(candidatures.get(id),conn);
                        candidatures.remove(id);
                    }
                    break;
                case "VCP":
                    System.out.println("Introdueix la provincia_id i la candidatura_id, per eliminar una candidatura d'una provincia(provincia_id,candidatura_id): ");
                    dades = scan.nextLine().split(",");
                    id = idCandidaturaProvincia(dades);
                    if (id < 1 || id > votsCandidaturesProvincies.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        votsCandidaturesProvincies.get(id).delete(votsCandidaturesProvincies.get(id), conn);
                        votsCandidaturesProvincies.remove(id);
                    }
                    break;
                case "VCC":
                    System.out.println("Introdueix la comunitat_aut_id i la candidatura_id, per eliminar una candidatura d'una comunitat autonoma(comunitat_aut_id,candidatura_id): ");
                    dades = scan.nextLine().split(",");
                    id = idCandidaturaComAutonoma(dades);
                    if (id < 1 || id > votsCandidaturesCA.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        votsCandidaturesCA.get(id).delete(votsCandidaturesCA.get(id), conn);
                        votsCandidaturesCA.remove(id);
                    }
                    break;
                case "VCM":
                    System.out.println("Introdueix el municipi_id i la candidatura_id, per eliminar una candidatura d'un municipi(municipi_id,candidatura_id): ");
                    dades = scan.nextLine().split(",");
                    id = idCandidaturaMunicipi(dades);
                    if (id < 1 || id > votsCandidaturesMunicipis.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        votsCandidaturesMunicipis.get(id).delete(votsCandidaturesMunicipis.get(id),conn);
                        votsCandidaturesMunicipis.remove(id);
                    }
                    break;
            }
        } while (!taula.equals("CAP"));
    }
    public static void updateTaula() throws SQLException {
        String taula;
        String camps;
        String[] dades;
        int id;
        do {
            Connection conn = Conexio.conectar();
            mostrarCodiTaules();
            System.out.print("De quina taula vols modificar registres(CAP per sortir):");
            taula = scan.nextLine();
            taula = taula.toUpperCase().substring(0, 3);
            switch (taula) {
                case "PRO":
                    System.out.println("Introdueix la provincia_id, per modificar una provincia: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > provincies.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        System.out.println("Introdueix les noves dades(comunitat_aut_id,nom,codi_ine,num_escons):");
                        camps = scan.nextLine();
                        dades = camps.split(",");
                        ComAutonoma comAutonoma=null;
                        Provincia p=new Provincia(id,comAutonoma.read(Integer.parseInt(dades[0]),conn),dades[1],dades[2],Integer.parseInt(dades[3]));
                        provincies.get(id).update(p,conn);
                        provincies.replace(id,p);
                    }
                    break;
                case "COM":
                    System.out.println("Introdueix la comunitat_aut_id, per modificar una comunitat autonoma: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > comAutonomes.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        System.out.println("Introdueix les noves dades(nom,codi_ine):");
                        camps = scan.nextLine();
                        dades = camps.split(",");
                        ComAutonoma c=new ComAutonoma(id,dades[0],dades[1]);
                        comAutonomes.get(id).update(c,conn);
                        comAutonomes.replace(id,c);
                    }
                    break;
                case "MUN":
                    System.out.println("Introdueix el municipi_id, per modificar un muncipi: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > municipis.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        System.out.println("Introdueix les noves dades(nom,codi_ine,provincia_id,districte):");
                        camps = scan.nextLine();
                        dades = camps.split(",");
                        Provincia provincia1 = provincies.get(Integer.valueOf(dades[2]));
                        Municipi m=new Municipi(id,dades[0],dades[1],provincia1,dades[3]);
                        municipis.get(id).update(m,conn);
                        municipis.replace(id,m);
                    }
                    break;
                case "PER":
                    System.out.println("Introdueix la persona_id, per modificar una persona: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > persones.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        System.out.println("Introdueix les noves dades(nom,cog1,cog2,sexe,data_naixement,dni):");
                        camps = scan.nextLine();
                        dades = camps.split(",");
                        Persona p=new Persona(id,dades[0],dades[1],dades[2],dades[3],Date.valueOf(dades[4]),dades[5]);
                        persones.get(id).update(p,conn);
                        persones.replace(id,p);
                    }
                    break;
                case "CAN":
                    System.out.println("Introdueix la candidatura_id, per modificar una candidatura: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > candidatures.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        candidatures.get(id).update(candidatures.get(id),conn);
                        candidatures.remove(id);
                    }
                    break;
                case "VCP":
                    System.out.println("Introdueix la provincia_id i la candidatura_id, per modificar una candidatura d'una provincia(provincia_id,candidatura_id): ");
                    dades = scan.nextLine().split(",");
                    id = idCandidaturaProvincia(dades);
                    if (id < 1 || id > votsCandidaturesProvincies.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        System.out.println("Introdueix les noves dades(vots,candidats_obtinguts:");
                        camps = scan.nextLine();
                        dades = camps.split(",");
                        VotsCandidaturesProvincies vCP=new VotsCandidaturesProvincies(votsCandidaturesProvincies.get(id).getProvincia(),votsCandidaturesProvincies.get(id).getCandidatura(),Integer.valueOf(dades[0]),Integer.valueOf(dades[1]));
                        votsCandidaturesProvincies.get(id).update(vCP,conn);
                        votsCandidaturesProvincies.replace(id,vCP);
                    }
                    break;
                case "VCC":
                    System.out.println("Introdueix la comunitat_aut_id i la candidatura_id, per modificar una candidatura d'una comunitat autonoma(comunitat_aut_id,candidatura_id): ");
                    dades = scan.nextLine().split(",");
                    id = idCandidaturaComAutonoma(dades);
                    if (id < 1 || id > votsCandidaturesCA.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        System.out.println("Introdueix les noves dades(vots):");
                        camps = scan.nextLine();
                        dades = camps.split(",");
                        VotsCandidaturesCa vCC=new VotsCandidaturesCa(votsCandidaturesCA.get(id).getComunitat_aut(),votsCandidaturesCA.get(id).getCandidatura(),Integer.valueOf(dades[0]));
                        votsCandidaturesCA.get(id).update(vCC,conn);
                        votsCandidaturesCA.replace(id,vCC);
                    }
                    break;
                case "VCM":
                    System.out.println("Introdueix el municipi_id i la candidatura_id, per modificar una candidatura d'un municipi(municipi_id,candidatura_id): ");
                    dades = scan.nextLine().split(",");
                    id = idCandidaturaMunicipi(dades);
                    if (id < 1 || id > votsCandidaturesMunicipis.size()) {
                        System.out.println("No existeix aquest registre");
                    } else {
                        System.out.println("Introdueix les noves dades(eleccio_id,vots):");
                        camps = scan.nextLine();
                        dades = camps.split(",");
                        VotsCandidaturesMunicipis vCM=new VotsCandidaturesMunicipis(votsCandidaturesMunicipis.get(id).getMunicipi_(),votsCandidaturesMunicipis.get(id).getCandidatura(),Integer.valueOf(dades[0]),Integer.valueOf(dades[1]));
                        votsCandidaturesMunicipis.get(id).update(vCM,conn);
                        votsCandidaturesMunicipis.replace(id,vCM);
                    }
                    break;
            }
        } while (!taula.equals("CAP"));
    }
    public static void mostrarUnRegistre() throws SQLException {
        String taula;
        String[] dades;
        Connection conn=Conexio.conectar();
        int id;
        do {
            mostrarCodiTaules();
            System.out.print("De quina taula vols mostrar nomes un registre(CAP per sortir):");
            taula = scan.nextLine();
            taula = taula.toUpperCase().substring(0, 3);
            switch (taula) {
                case "PRO":
                    System.out.println("Introdueix la provincia_id: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > provincies.size()) {
                        System.out.println("No existeix aquest registre");
                    } else System.out.println(provincies.get(id).toString());
                    break;
                case "COM":
                    System.out.println("Introdueix la comunitat_aut_id: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > comAutonomes.size()) {
                        System.out.println("No existeix aquest registre");
                    } else System.out.println(comAutonomes.get(id).toString());
                    break;
                case "MUN":
                    System.out.println("Introdueix el muncipi_id: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1) {
                        System.out.println("No existeix aquest registre");
                    } else System.out.println(municipis.get(id).toString());
                    break;
                case "PER":
                    System.out.println("Introdueix la persona_id: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > persones.size()) {
                        System.out.println("No existeix aquest registre");
                    } else System.out.println(persones.get(id).toString());
                    break;
                case "CAN":
                    System.out.println("Introdueix la candidatura_id: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    if (id < 1 || id > candidatures.size()) {
                        System.out.println("No existeix aquest registre");
                    } else System.out.println(candidatures.get(id).toString());
                    break;
                case "VCP":
                    System.out.println("Introdueix la provincia_id i la candidatura_id(provincia_id,candidatura_id): ");
                    dades = scan.nextLine().split(",");
                    id = idCandidaturaProvincia(dades);
                    if (id < 1 || id > votsCandidaturesProvincies.size()) {
                        System.out.println("No existeix aquest registre");
                    } else System.out.println(votsCandidaturesProvincies.get(id).toString());
                    break;
                case "VCC":
                    System.out.println("Introdueix la comunitat_aut_id i la candidatura_id(comunitat_aut_id,candidatura_id): ");
                    dades = scan.nextLine().split(",");
                    id = idCandidaturaProvincia(dades);
                    if (id < 1 || id > votsCandidaturesCA.size()) {
                        System.out.println("No existeix aquest registre");
                    } else System.out.println(votsCandidaturesCA.get(id).toString());
                    break;
                case "VCM":
                    System.out.println("Introdueix el municipi_id i la candidatura_id(municipi_id,candidatura_id): ");
                    dades = scan.nextLine().split(",");
                    id = idCandidaturaProvincia(dades);
                    if (id < 1 || id > votsCandidaturesMunicipis.size()) {
                        System.out.println("No existeix aquest registre");
                    } else System.out.println(votsCandidaturesMunicipis.get(id).toString());
                    break;
            }
        } while (!taula.equals("CAP"));
    }
    public static int count(String taula, Connection conn) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + taula;
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultat = statement.executeQuery(query);
        resultat.next();
        int countComanda = resultat.getInt("COUNT(*)");
        resultat.close();
        statement.close();
        return countComanda;
    }
    public static int idCandidaturaProvincia(String[] dades) {
        for (int i = 1; i < votsCandidaturesProvincies.size(); i++) {
            if (votsCandidaturesProvincies.get(i).getProvincia().getProvincia_id() == Integer.parseInt(dades[0]) && votsCandidaturesProvincies.get(i).getCandidatura().getCandidatura_id() == Integer.parseInt(dades[1])) {
                return i;
            }
        }
        return 0;
    }

    public static int idCandidaturaComAutonoma(String[] dades) {
        for (int i = 1; i < votsCandidaturesCA.size(); i++) {
            if (votsCandidaturesCA.get(i).getComunitat_aut().getComAutonoma_id() == Integer.parseInt(dades[0]) && votsCandidaturesCA.get(i).getCandidatura().getCandidatura_id() == Integer.parseInt(dades[1])) {
                return i;
            }
        }
        return 0;
    }

    public static int idCandidaturaMunicipi(String[] dades) {
        for (int i = 1; i < votsCandidaturesMunicipis.size(); i++) {
            if (votsCandidaturesMunicipis.get(i).getMunicipi_().getMunicipi_id() == Integer.parseInt(dades[0]) && votsCandidaturesMunicipis.get(i).getCandidatura().getCandidatura_id() == Integer.parseInt(dades[1])) {
                return i;
            }
        }
        return 0;
    }
}
