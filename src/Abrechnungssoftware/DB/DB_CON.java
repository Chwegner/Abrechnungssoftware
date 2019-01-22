package Abrechnungssoftware.DB;


import Abrechnungssoftware.Verarbeitung.Auftrag;
import Abrechnungssoftware.Verarbeitung.Kunde;
import Abrechnungssoftware.DB.Stammdaten;
import Abrechnungssoftware.Verarbeitung.Rechnung;
import Abrechnungssoftware.DB.helperClass;
import Abrechnungssoftware.DB.helperClass2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


public class DB_CON {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private String host;
    private String dbName;
    private String user;
    private String pass;
    private Stammdaten stamm;
    private Kunde kunde;
    private Auftrag auftrag;
    private Rechnung rechnung;
    private helperClass2 helpi;
    private ArrayList<helperClass> helper = new ArrayList<>();
    private ArrayList<Kunde> kundenliste = new ArrayList<>();
    private ArrayList<Auftrag> auftragliste = new ArrayList<>();
    private ArrayList<Rechnung> rechnungliste = new ArrayList<>();


    public DB_CON() {
        Reader reader = null;
        try {
            reader = new FileReader("system.ini");
            Properties prop = new Properties();
            prop.load(reader);
            this.host = prop.getProperty("HOST");
            this.user = prop.getProperty("USER");
            this.pass = prop.getProperty("PWD");
            this.dbName = prop.getProperty("DATENBANK");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("Fehler bei MySQL-JDBC-Bridge" + e);
        }

    }

    /**
     * Datenbankverbindung Öffnen
     */
    public boolean db_open() {
        boolean con = false;
        try {
            String url = "jdbc:mysql://" + host + "/" + dbName;
            connection = DriverManager.getConnection(url, user, pass);
            if(connection == null){
                con = false;
            }else con = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }


    public Stammdaten LoadStammdaten(Stammdaten stamm) {
        try {
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM stammdaten WHERE id = '1'";
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                stamm.setId(resultSet.getInt("id"));
                stamm.setFirma(resultSet.getString("firma"));
                stamm.setVorname(resultSet.getString("vorname"));
                stamm.setNachname(resultSet.getString("nachname"));
                stamm.setStr(resultSet.getString("strasse"));
                stamm.setHsnr(resultSet.getString("hsnr"));
                stamm.setPlz(resultSet.getString("plz"));
                stamm.setOrt(resultSet.getString("ort"));
                stamm.setWeb(resultSet.getString("web"));
                stamm.setTelefon(resultSet.getString("telefon"));
                stamm.setTelefax(resultSet.getString("telefax"));
                stamm.setEmail(resultSet.getString("email"));
                stamm.setSteuernummer(resultSet.getString("steuernummer"));
                stamm.setBankname(resultSet.getString("bankname"));
                stamm.setKontoinhaber(resultSet.getString("kontoinhaber"));
                stamm.setIban(resultSet.getString("iban"));
                stamm.setBic(resultSet.getString("bic"));

            }
            statement.close();

        } catch (SQLException e) {
            System.out.println("Fehler bei Abfrage: " + e);
        }
        return stamm;
    }

    public void SaveStammdaten(Stammdaten daten) {
        try {
            statement = connection.createStatement();
            String sqlQuery = "UPDATE stammdaten SET " +
                    "firma = '" + daten.getFirma() + "'," +
                    "vorname = '" + daten.getVorname() + "'," +
                    "nachname = '" + daten.getNachname() + "'," +
                    "strasse = '" + daten.getStr() + "'," +
                    "hsnr = '" + daten.getHsnr() + "'," +
                    "plz = '" + daten.getPlz() + "'," +
                    "ort = '" + daten.getOrt() + "'," +
                    "telefon = '" + daten.getTelefon() + "'," +
                    "telefax = '" + daten.getTelefax() + "'," +
                    "web = '" + daten.getWeb() + "'," +
                    "email = '" + daten.getEmail() + "'," +
                    "bankname = '" + daten.getBankname() + "'," +
                    "kontoinhaber = '" + daten.getKontoinhaber() + "'," +
                    "bic = '" + daten.getBic() + "'," +
                    "iban = '" + daten.getIban() + "'," +
                    "steuernummer = '" + daten.getSteuernummer() + "' where id = '1'";
            statement.executeUpdate(sqlQuery);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList LoadKundenList() {
        try {
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM kunden";

            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Kunde kunde = new Kunde();
                kunde.setId(resultSet.getInt("id"));
                kunde.setFirma(resultSet.getString("firma"));
                kunde.setAnrede(resultSet.getString("anrede"));
                kunde.setVorname(resultSet.getString("vorname"));
                kunde.setName(resultSet.getString("nachname"));
                kunde.setStrasse(resultSet.getString("strasse"));
                kunde.setHausnummer(resultSet.getString("hsnr"));
                kunde.setPlz(resultSet.getString("plz"));
                kunde.setOrt(resultSet.getString("ort"));
                kunde.setTelefon(resultSet.getString("telefon"));
                kunde.setFax(resultSet.getString("telefax"));
                kunde.setWeb(resultSet.getString("web"));
                kunde.setEmail(resultSet.getString("email"));
                kunde.setAnrede(resultSet.getString("ap_anrede"));
                kunde.setApVorname(resultSet.getString("ap_vorname"));
                kunde.setApName(resultSet.getString("ap_nachname"));
                kunde.setApTelefon(resultSet.getString("ap_telefon"));
                kunde.setApEmail(resultSet.getString("ap_email"));
                kunde.setStundenSatz(resultSet.getDouble("stdsatz"));
                kundenliste.add(kunde);
            }
            statement.close();

        } catch (Exception e) {
            System.out.println("Fehler bei Abfrage: " + e);
        }
        return kundenliste;
    }

    public void EditKunde(int ID, Kunde kunde) {
        try {
            statement = connection.createStatement();
            String sqlQuery = "UPDATE kunden SET " +
                    "firma = '" + kunde.getFirma() + "'," +
                    "anrede = '" + kunde.getAnrede() + "'," +
                    "vorname = '" + kunde.getVorname() + "'," +
                    "nachname = '" + kunde.getName() + "'," +
                    "strasse = '" + kunde.getStrasse() + "'," +
                    "hsnr = '" + kunde.getHausnummer() + "'," +
                    "plz = '" + kunde.getPlz() + "'," +
                    "ort = '" + kunde.getOrt() + "'," +
                    "telefon = '" + kunde.getTelefon() + "'," +
                    "telefax = '" + kunde.getFax() + "'," +
                    "web = '" + kunde.getWeb() + "'," +
                    "email = '" + kunde.getEmail() + "'," +
                    "ap_anrede = '" + kunde.getApAnrede() + "'," +
                    "ap_vorname = '" + kunde.getApVorname() + "'," +
                    "ap_nachname = '" + kunde.getApName() + "'," +
                    "ap_telefon = '" + kunde.getApTelefon() + "'," +
                    "ap_email = '" + kunde.getApEmail() + "'," +
                    "stdsatz = '" + kunde.getStundenSatz() + "' " +
                    "WHERE id = '" + ID + "'";

            statement.executeUpdate(sqlQuery);
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void NewKunde(Kunde kunde) {
        try {
            statement = connection.createStatement();
            String sqlQuery = "INSERT INTO kunden (" +
                    "firma,anrede,vorname,nachname, strasse, hsnr, plz, ort, telefon, telefax, web, email, " +
                    "ap_anrede, ap_vorname, ap_nachname, ap_telefon, ap_email, stdsatz) VALUES(" +
                    "'" + kunde.getFirma() + "','" + kunde.getAnrede() + "','" + kunde.getVorname() + "','" + kunde.getName() + "'," +
                    "'" + kunde.getStrasse() + "','" + kunde.getHausnummer() + "'," +
                    "'" + kunde.getPlz() + "','" + kunde.getOrt() + "','" + kunde.getTelefon() + "','" + kunde.getFax() + "'," +
                    "'" + kunde.getWeb() + "','" + kunde.getEmail() + "','" + kunde.getAnrede() + "','" + kunde.getApVorname() + "'," +
                    "'" + kunde.getApName() + "','" + kunde.getApTelefon() + "','" + kunde.getApEmail() + "'," +
                    "'" + kunde.getStundenSatz() + "') ";

            statement.executeUpdate(sqlQuery);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteKunde(int ID) {
        try {
            statement = connection.createStatement();
            String sqlQuery = "DELETE FROM kunden WHERE id = '" + ID + "'";
            statement.executeUpdate(sqlQuery);
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Kunde LoadKunde(int ID) {
        Kunde kunde = new Kunde();
        try {
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM kunden WHERE id = '" + ID + "'";
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                kunde.setId(resultSet.getInt("id"));
                kunde.setFirma(resultSet.getString("firma"));
                kunde.setAnrede(resultSet.getString("anrede"));
                kunde.setVorname(resultSet.getString("vorname"));
                kunde.setName(resultSet.getString("nachname"));
                kunde.setStrasse(resultSet.getString("strasse"));
                kunde.setHausnummer(resultSet.getString("hsnr"));
                kunde.setPlz(resultSet.getString("plz"));
                kunde.setOrt(resultSet.getString("ort"));
                kunde.setTelefon(resultSet.getString("telefon"));
                kunde.setFax(resultSet.getString("telefax"));
                kunde.setWeb(resultSet.getString("web"));
                kunde.setEmail(resultSet.getString("email"));
                kunde.setApAnrede(resultSet.getString("ap_anrede"));
                kunde.setApVorname(resultSet.getString("ap_vorname"));
                kunde.setApName(resultSet.getString("ap_nachname"));
                kunde.setApEmail(resultSet.getString("ap_email"));
                kunde.setStundenSatz(resultSet.getDouble("stdsatz"));
            }
            statement.close();

        } catch (Exception e) {
            System.out.println("Fehler bei Abfrage: " + e);
        }

        return kunde;
    }

    public void NewAuftrag(Auftrag auftrag) {
        int id = 0;
        try {
            PreparedStatement ps;
            String sqlQuery = "INSERT INTO auftrag (kunden_id,von,bis,grund) VALUES (" +
                    "'" + auftrag.getKundenid() + "','" + auftrag.getVon() + "','" + auftrag.getBis() + "','" + auftrag.getBezeichnung() + "')";

            ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();

            auftrag.setId(id);
            String[][] auswertung = auftrag.getAuswertung();

            for (int i = 0; i < auftrag.getAbrechnungsintervall(); i++) {

                //Auftrag_intervall eintragen
                String sqlQuery1 = "INSERT INTO auftrag_intervall  " +
                        "(auftrag_id,von,bis,tage,korrektur_tage) VALUES (" +
                        "'" + auftrag.getId() + "','" + auswertung[i][0] + "','" + auswertung[i][1] + "'," +
                        "'"+auftrag.getTage()+"','0')";
                ps = connection.prepareStatement(sqlQuery1, Statement.RETURN_GENERATED_KEYS);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                int ids = 0;
                if (rs.next()) {
                    ids = rs.getInt(1);
                }
                rs.close();
                ps.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList LoadAuftragList() {
        try {
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM auftrag";

            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Auftrag auftrag = new Auftrag();
                auftrag.setId(resultSet.getInt("id"));
                auftrag.setKundenid(resultSet.getInt("kunden_id"));
                auftrag.setVon(resultSet.getString("von"));
                auftrag.setBis(resultSet.getString("bis"));
                auftrag.setBezeichnung(resultSet.getString("grund"));
                auftragliste.add(auftrag);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return auftragliste;
    }

    public Auftrag LoadAuftrag(int ID) {
        try {
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM auftrag WHERE id = '" + ID + "'";

            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                auftrag.setId(resultSet.getInt("id"));
                auftrag.setKundenid(resultSet.getInt("kunden_id"));
                auftrag.setVon(resultSet.getString("von"));
                auftrag.setBis(resultSet.getString("bis"));
                auftrag.setBezeichnung(resultSet.getString("grund"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auftrag;
    }

    public void DeleteAuftrag(int ID) {
        try {
            statement = connection.createStatement();
            String sqlQuery = "DELETE FROM auftrag WHERE id = '" + ID + "'";
            statement.executeUpdate(sqlQuery);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void EditAuftrag(Auftrag auftrag) {
        try {
            statement = connection.createStatement();
            String sqlQuery = "Update auftrag SET " +
                    "kunden_id = '" + auftrag.getKundenid() + "'," +
                    "von = '" + auftrag.getVon() + "'," +
                    "bis = '" + auftrag.getBis() + "'," +
                    "grund = '" + auftrag.getBezeichnung() + "' " +
                    " WHERE id = '" + auftrag.getId() + "'";
            statement.executeUpdate(sqlQuery);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SaveKorrekturtage(int id,int tage){
        try{
            statement = connection.createStatement();
            String sqlQuery = "UPDATE auftrag_intervall SET" +
                    " korrektur_tage = '"+tage+"' WHERE" +
                    " id = '"+id+"'";
            statement.executeUpdate(sqlQuery);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int LoadKorrekturtage(int id){
        int korrekturtage = 0;
        try{
            statement = connection.createStatement();
            String sqlQuery = "SELECT korrektur_tage FROM auftrag_intervall WHERE id = '"+id+"'";
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                korrekturtage = resultSet.getInt("korrektur_tage");
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return korrekturtage;
    }

    public ArrayList LoadRechnungList() {
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT " +
                    "t1.id AS intervall_id,t1.von AS intervall_von,t1.bis AS intervall_bis," +
                    "t1.tage,t1.korrektur_tage,t1.re_erstellt,t1.re_bezahlt," +
                    "t2.id AS auftrag_id,t2.von,t2.bis,t2.grund," +
                    "t3.* FROM auftrag_intervall AS t1,auftrag AS t2, kunden AS t3 WHERE " +
                    "t2.id = t1.auftrag_id AND t3.id = t2.kunden_id AND t1.re_erstellt= 'Nein'");
            while(resultSet.next()){
                helperClass help = new helperClass();
                help.setAuftrag_intervall_id(resultSet.getInt("intervall_id"));
                help.setAuftrag_id(resultSet.getInt("auftrag_id"));
                help.setKunden_id(resultSet.getInt("id"));
                help.setBis(resultSet.getString("bis"));
                help.setVon(resultSet.getString("von"));
                help.setIntervall_bis(resultSet.getString("intervall_bis"));
                help.setIntervall_von(resultSet.getString("intervall_von"));
                help.setTage(resultSet.getInt("tage"));
                help.setKorrektur_tage(resultSet.getInt("korrektur_tage"));
                help.setFirma(resultSet.getString("firma"));
                help.setAnrede(resultSet.getString("anrede"));
                help.setVorname(resultSet.getString("vorname"));
                help.setName(resultSet.getString("nachname"));
                help.setStrasse(resultSet.getString("strasse"));
                help.setHausnummer(resultSet.getString("hsnr"));
                help.setPlz(resultSet.getString("plz"));
                help.setOrt(resultSet.getString("ort"));
                help.setTelefon(resultSet.getString("telefon"));
                help.setFax(resultSet.getString("telefax"));
                help.setEmail(resultSet.getString("email"));
                help.setGrund(resultSet.getString("grund"));
                helper.add(help);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return helper;
    }

    public ArrayList LoadRechnungList_ende() {
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT " +
                    "t1.id AS intervall_id,t1.von AS intervall_von,t1.bis AS intervall_bis," +
                    "t1.tage,t1.korrektur_tage,t1.re_erstellt,t1.re_bezahlt," +
                    "t2.id AS auftrag_id,t2.von,t2.bis,t2.grund," +
                    "t3.* FROM auftrag_intervall AS t1,auftrag AS t2, kunden AS t3 WHERE " +
                    "t2.id = t1.auftrag_id AND t3.id = t2.kunden_id AND t1.re_erstellt= 'Ja' AND re_bezahlt= 'Ja'");
            while(resultSet.next()){
                helperClass help = new helperClass();
                help.setAuftrag_intervall_id(resultSet.getInt("intervall_id"));
                help.setAuftrag_id(resultSet.getInt("auftrag_id"));
                help.setKunden_id(resultSet.getInt("id"));
                help.setBis(resultSet.getString("bis"));
                help.setVon(resultSet.getString("von"));
                help.setIntervall_bis(resultSet.getString("intervall_bis"));
                help.setIntervall_von(resultSet.getString("intervall_von"));
                help.setTage(resultSet.getInt("tage"));
                help.setKorrektur_tage(resultSet.getInt("korrektur_tage"));
                help.setFirma(resultSet.getString("firma"));
                help.setAnrede(resultSet.getString("anrede"));
                help.setVorname(resultSet.getString("vorname"));
                help.setName(resultSet.getString("nachname"));
                help.setStrasse(resultSet.getString("strasse"));
                help.setHausnummer(resultSet.getString("hsnr"));
                help.setPlz(resultSet.getString("plz"));
                help.setOrt(resultSet.getString("ort"));
                help.setTelefon(resultSet.getString("telefon"));
                help.setFax(resultSet.getString("telefax"));
                help.setEmail(resultSet.getString("email"));
                help.setGrund(resultSet.getString("grund"));
                helper.add(help);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return helper;
    }

    public ArrayList LoadRechnungList_offen() {
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT " +
                    "t1.id AS intervall_id,t1.von AS intervall_von,t1.bis AS intervall_bis," +
                    "t1.tage,t1.korrektur_tage,t1.re_erstellt,t1.re_bezahlt," +
                    "t2.id AS auftrag_id,t2.von,t2.bis,t2.grund," +
                    "t3.* FROM auftrag_intervall AS t1,auftrag AS t2, kunden AS t3 WHERE " +
                    "t2.id = t1.auftrag_id AND t3.id = t2.kunden_id AND t1.re_erstellt= 'Nein' AND re_bezahlt= 'Nein'");
            while(resultSet.next()){
                helperClass help = new helperClass();
                help.setAuftrag_intervall_id(resultSet.getInt("intervall_id"));
                help.setAuftrag_id(resultSet.getInt("auftrag_id"));
                help.setKunden_id(resultSet.getInt("id"));
                help.setBis(resultSet.getString("bis"));
                help.setVon(resultSet.getString("von"));
                help.setIntervall_bis(resultSet.getString("intervall_bis"));
                help.setIntervall_von(resultSet.getString("intervall_von"));
                help.setTage(resultSet.getInt("tage"));
                help.setKorrektur_tage(resultSet.getInt("korrektur_tage"));
                help.setFirma(resultSet.getString("firma"));
                help.setAnrede(resultSet.getString("anrede"));
                help.setVorname(resultSet.getString("vorname"));
                help.setName(resultSet.getString("nachname"));
                help.setStrasse(resultSet.getString("strasse"));
                help.setHausnummer(resultSet.getString("hsnr"));
                help.setPlz(resultSet.getString("plz"));
                help.setOrt(resultSet.getString("ort"));
                help.setTelefon(resultSet.getString("telefon"));
                help.setFax(resultSet.getString("telefax"));
                help.setEmail(resultSet.getString("email"));
                help.setGrund(resultSet.getString("grund"));
                helper.add(help);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return helper;
    }

    public Rechnung LoadRechnung(int ID) {
        try{
           /* statement = connection.createStatement();
            String sqlQuery = "Select * FROM auftrag_intervall WHERE id = '"+ID+"'";
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                rechnung.setId(resultSet.getInt("id"));
                rechnung.setAuftrag_id(resultSet.getString("auftrag_id"));
                rechnung.setVon(resultSet.getString("von"));
                rechnung.setBis(resultSet.getString("bis"));
                rechnung.setErstellt(resultSet.getString("re_erstellt"));
                rechnung.setBezahlt(resultSet.getString("re_bezahlt"));
            }
            statement.close();*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return rechnung;
    }

    public void NewRechnung(int ID) {
        try {
            statement = connection.createStatement();
            String sqlQuery = "Update auftrag_intervall SET re_erstellt = 'Ja'" +
                    " WHERE id = '"+ID+"'";
            statement.executeUpdate(sqlQuery);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void NewRechnung(int ID,int auftrag_id,String re_nr){
        try {
            statement = connection.createStatement();
            String sqlQuery = "Update auftrag_intervall SET re_erstellt = 'Ja'" +
                    " WHERE id = '"+ID+"'";
            statement.executeUpdate(sqlQuery);
            statement.close();

            statement = connection.createStatement();
            String sqlQuery1 = "INSERT INTO rechnung (auftrag_id,re_nr) VALUES ('"+auftrag_id+"','"+re_nr+"')";

            statement.executeUpdate(sqlQuery1);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Rechnungbezahlt(int ID){
        try{
            statement = connection.createStatement();
            String sqlQuery = "UPDATE auftrag_intervall SET re_bezahlt = 'Ja'" +
                    " WHERE id = '"+ID+"'";
            statement.executeUpdate(sqlQuery);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int nextIdRechnung(){
        int id = 0;
        try {
            statement = connection.createStatement();
            String sqlQuery = "SELECT id FROM rechnung ORDER BY id LIMIT 1";
            resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                id = resultSet.getInt("id")+1;
            }
            if(id==0){
                id = 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return id;
    }
    public helperClass2 loadHelper(int ID){
        helpi = new helperClass2();
        try {
            statement = connection.createStatement();
            String sqlQuery = "SELECT " +
                    "t1.id AS intervall_id,t1.von AS intervall_von,t1.bis AS intervall_bis," +
                    "t1.tage,t1.korrektur_tage,t1.re_erstellt,t1.re_bezahlt," +
                    "t2.id AS auftrag_id,t2.von,t2.bis,t2.grund," +
                    "t3.* FROM auftrag_intervall AS t1,auftrag AS t2, kunden AS t3 WHERE " +
                    "t2.id = t1.auftrag_id AND t3.id = t2.kunden_id AND t1.re_erstellt = 'Nein' AND t1.id = '"+ID+"'";
            resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                helpi.setAuftrag_intervall_id(resultSet.getInt("intervall_id"));
                System.out.println(resultSet.getInt("intervall_id"));
                helpi.setKunden_id(resultSet.getInt("id"));
                helpi.setAuftrag_id(resultSet.getInt("auftrag_id"));
                helpi.setKorrekturtage(resultSet.getInt("korrektur_tage"));
                helpi.setTage(resultSet.getInt("tage"));
                helpi.setFirma(resultSet.getString("firma"));
                helpi.setAnrede(resultSet.getString("anrede"));
                helpi.setVorname(resultSet.getString("vorname"));
                helpi.setName(resultSet.getString("nachname"));
                helpi.setStrasse(resultSet.getString("strasse"));
                helpi.setHausnummer(resultSet.getString("hsnr"));
                helpi.setPlz(resultSet.getString("plz"));
                helpi.setOrt(resultSet.getString("ort"));
                helpi.setTelefon(resultSet.getString("telefon"));
                helpi.setFax(resultSet.getString("telefax"));
                helpi.setWeb(resultSet.getString("web"));
                helpi.setEmail(resultSet.getString("email"));
                helpi.setApAnrede(resultSet.getString("ap_anrede"));
                helpi.setApVorname(resultSet.getString("ap_vorname"));
                helpi.setApName(resultSet.getString("ap_nachname"));
                helpi.setApTelefon(resultSet.getString("ap_telefon"));
                helpi.setApEmail(resultSet.getString("ap_email"));
                helpi.setBezeichnung(resultSet.getString("grund"));
                helpi.setVon(resultSet.getString("von"));
                helpi.setBis(resultSet.getString("bis"));
                helpi.setIntervall_von(resultSet.getString("intervall_von"));
                helpi.setIntervall_bis(resultSet.getString("intervall_bis"));
                helpi.setStundenSatz(resultSet.getDouble("stdsatz"));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return helpi;
    }

    public void db_close() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Installationsroutine*/
    public void db_install() {
        // Tabellen löschen für eine saubere installation
        String query01 = "drop table auftrag";
        String query02 = "drop table auftrag_intervall";
        String query03 = "drop table kunden";
        String query04 = "drop table rechnung";
        String query05 = "drop table rechnungen";
        String query06 = "drop table stammdaten";
        // Tabellen anlegen und leeren Stammdatensatz erzeugen
        String query0 = "create table if not exists auftrag" +
                "(" +
                "  id        int(11) auto_increment" +
                "    primary key," +
                "  kunden_id int(11)      not null," +
                "  von       varchar(20)  not null," +
                "  bis       varchar(20)  not null," +
                "  grund     varchar(250) not null" +
                ")" +
                "  engine = MyISAM " +
                "  charset = utf8";
        String query1 = "create table if not exists auftrag_intervall" +
                "(" +
                "  id             int(11) auto_increment" +
                "    primary key," +
                "  auftrag_id     int(11)                            not null," +
                "  von            varchar(20)                        not null," +
                "  bis            varchar(20)                        not null," +
                "  tage           int(2)                             null," +
                "  re_erstellt    enum ('ja', 'nein') default 'nein' not null," +
                "  re_bezahlt     enum ('ja', 'nein') default 'nein' not null," +
                "  korrektur_tage int(2)                             null" +
                ")" +
                "  engine = MyISAM " +
                "  charset = utf8;";
        String query2 = "create table if not exists kunden" +
                "(" +
                "  id          int(11) auto_increment" +
                "    primary key," +
                "  firma       varchar(100)                         not null," +
                "  anrede      enum ('Herr', 'Frau') default 'Herr' not null," +
                "  vorname     varchar(100)                         not null," +
                "  nachname    varchar(100)                         not null," +
                "  strasse     varchar(50)                          not null," +
                "  hsnr        varchar(20)                          not null," +
                "  plz         varchar(20)                          not null," +
                "  ort         varchar(50)                          not null," +
                "  telefon     varchar(50)                          not null," +
                "  telefax     varchar(50)                          not null," +
                "  web         varchar(50)                          not null," +
                "  email       varchar(50)                          not null," +
                "  ap_anrede   enum ('Herr', 'Frau') default 'Herr' not null," +
                "  ap_vorname  varchar(50)                          not null," +
                "  ap_nachname varchar(50)                          not null," +
                "  ap_telefon  varchar(50)                          not null," +
                "  ap_email    varchar(50)                          not null," +
                "  stdsatz     decimal(10, 2)                       not null" +
                ")" +
                "  engine = MyISAM " +
                "  charset = utf8";
        String query3 = "create table if not exists rechnung" +
                "(" +
                "  id         int(11)    auto_increment primary key ," +
                "  auftrag_id int(11)     not null," +
                "  re_nr      varchar(10) not null" +
                ")" +
                "  engine = MyISAM " +
                "  charset = utf8";
        String query4  = "create table if not exists rechnungen" +
                "(" +
                "  id                   int(11) auto_increment" +
                "    primary key," +
                "  grund                varchar(100) charset latin1 null," +
                "  kunden_id            int(11)                     null," +
                "  auftrag_id           int(11)                     null," +
                "  von                  varchar(20) charset latin1  null," +
                "  bis                  varchar(20) charset latin1  null," +
                "  auftrag_intervall_id int(11)                     null," +
                "  intervall_von        varchar(20) charset latin1  null," +
                "  intervall_bis        varchar(20) charset latin1  null," +
                "  tage                 int(3)                      null," +
                "  korrektur_tage       int(2)                      null" +
                ")" +
                "  engine = MyISAM " +
                "  charset = utf8";
        String query5 = "create table if not exists stammdaten" +
                "(" +
                "  id           tinyint(1) auto_increment" +
                "    primary key," +
                "  firma        varchar(100) not null," +
                "  vorname      varchar(50)  not null," +
                "  nachname     varchar(50)  not null," +
                "  strasse      varchar(100) not null," +
                "  hsnr         varchar(20)  not null," +
                "  plz          varchar(10)  not null," +
                "  ort          varchar(50)  not null," +
                "  telefon      varchar(50)  not null," +
                "  telefax      varchar(50)  not null," +
                "  web          varchar(50)  not null," +
                "  email        varchar(50)  not null," +
                "  bankname     varchar(50)  not null," +
                "  kontoinhaber varchar(50)  not null," +
                "  bic          varchar(50)  not null," +
                "  iban         varchar(50)  not null," +
                "  steuernummer varchar(50)  not null" +
                ")" +
                "  engine = MyISAM " +
                "  charset = utf8";
        String query6 = "INSERT INTO stammdaten (" +
                "firma, vorname, nachname, strasse, hsnr, plz, ort, telefon, telefax, web, email, bankname," +
                " kontoinhaber, bic, iban, steuernummer) VALUES (" +
                "'','','','','','','','','','','','','','','','')";

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.addBatch(query01);
            statement.addBatch(query02);
            statement.addBatch(query03);
            statement.addBatch(query04);
            statement.addBatch(query05);
            statement.addBatch(query06);
            statement.addBatch(query0);
            statement.addBatch(query1);
            statement.addBatch(query2);
            statement.addBatch(query3);
            statement.addBatch(query4);
            statement.addBatch(query5);
            statement.addBatch(query6);
            statement.executeBatch();
            connection.commit();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
