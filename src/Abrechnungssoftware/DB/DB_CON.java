package Abrechnungssoftware.DB;


import Abrechnungssoftware.Verarbeitung.Auftrag;
import Abrechnungssoftware.Verarbeitung.Kunde;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


public class DB_CON {

    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    protected String host;
    protected String dbName;
    protected String user;
    protected String pass;
    protected Reader reader = null;
    protected Stammdaten stamm;
    //protected Kunde kunde;
    protected Auftrag auftrag;
    protected ArrayList<Kunde> kundenliste = new ArrayList<Kunde>();
    protected ArrayList<Auftrag> auftragliste = new ArrayList<Auftrag>();
    protected ArrayList rechnungliste;



    public DB_CON(){
        this.stamm = stamm;
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
            }
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("Fehler bei MySQL-JDBC-Bridge" + e);
            return;
        }

    }

    /** Datenbankverbindung Öffnen
     *
     */
    public void db_open(){
        try {
            String url = "jdbc:mysql://" + host + "/" + dbName;
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {

        }
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
                    "firma = '"+daten.getFirma()+"'," +
                    "vorname = '"+daten.getVorname()+"'," +
                    "nachname = '"+daten.getNachname()+"'," +
                    "strasse = '"+daten.getStr()+"'," +
                    "hsnr = '"+daten.getHsnr()+"'," +
                    "plz = '"+daten.getPlz()+"'," +
                    "ort = '"+daten.getOrt()+"'," +
                    "telefon = '"+daten.getTelefon()+"'," +
                    "telefax = '"+daten.getTelefax()+"'," +
                    "web = '"+daten.getWeb()+"'," +
                    "email = '"+daten.getEmail()+"'," +
                    "bankname = '"+daten.getBankname()+"'," +
                    "kontoinhaber = '" + daten.getKontoinhaber() +"'," +
                    "bic = '"+daten.getBic()+"'," +
                    "iban = '"+daten.getIban()+"'," +
                    "steuernummer = '"+daten.getSteuernummer()+"' where id = '1'";
            statement.executeUpdate(sqlQuery);
            statement.close();
        } catch (Exception e) {

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

        } catch (Exception e){
            System.out.println("Fehler bei Abfrage: " + e);
        }
        return kundenliste;
    }

    public void EditKunde(int ID, Kunde kunde) {
        try {
            statement = connection.createStatement();
            String sqlQuery = "UPDATE kunden SET " +
                    "firma = '"+kunde.getFirma()+"'," +
                    "strasse = '"+kunde.getStrasse()+"'," +
                    "hsnr = '"+kunde.getHausnummer()+"'," +
                    "plz = '"+kunde.getPlz()+"'," +
                    "ort = '"+kunde.getOrt()+"'," +
                    "telefon = '"+kunde.getTelefon()+"'," +
                    "telefax = '"+kunde.getFax()+"'," +
                    "web = '"+kunde.getWeb()+"'," +
                    "email = '"+kunde.getEmail()+"'," +
                    "ap_anrede = '"+kunde.getAnrede()+"'," +
                    "ap_vorname = '"+kunde.getApVorname()+"'," +
                    "ap_nachname = '"+kunde.getApName()+"'," +
                    "ap_telefon = '"+kunde.getApTelefon()+"'," +
                    "ap_email = '"+kunde.getApEmail()+"'," +
                    "stdsatz = '"+kunde.getStundenSatz()+"' " +
                    "WHERE id = '"+ID+"'";

            statement.executeUpdate(sqlQuery);
            statement.close();

        }catch (Exception e){

        }

    }

    public void NewKunde(Kunde kunde) {
        try {
            statement = connection.createStatement();
            String sqlQuery = "INSERT INTO kunden (" +
                    "firma, strasse, hsnr, plz, ort, telefon, telefax, web, email, " +
                    "ap_anrede, ap_vorname, ap_nachname, ap_telefon, ap_email, stdsatz) VALUES(" +
                    "'"+kunde.getFirma()+"','"+kunde.getStrasse()+"','"+kunde.getHausnummer()+"'," +
                    "'"+kunde.getPlz()+"','"+kunde.getOrt()+"','"+kunde.getTelefon()+"','"+kunde.getFax()+"'," +
                    "'"+kunde.getWeb()+"','"+kunde.getEmail()+"','"+kunde.getAnrede()+"','"+kunde.getApVorname()+"'," +
                    "'"+kunde.getApName()+"','"+kunde.getApTelefon()+"','"+kunde.getApEmail()+"'," +
                    "'"+kunde.getStundenSatz()+"') ";

            statement.executeUpdate(sqlQuery);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void DeleteKunde(int ID) {
        try{
            statement = connection.createStatement();
            String sqlQuery = "DELETE FROM kunden WHERE id = '"+ID+"'";
            statement.executeUpdate(sqlQuery);
            statement.close();

        }catch(Exception e){

        }
    }

    public Kunde LoadKunde(int ID) {
        Kunde kunde = new Kunde();
        try{
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM kunden WHERE id = '"+ID+"'";
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                kunde.setId(resultSet.getInt("id"));
                kunde.setFirma(resultSet.getString("firma"));
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
                kunde.setApEmail(resultSet.getString("ap_email"));
                kunde.setStundenSatz(resultSet.getDouble("stdsatz"));
            }
            statement.close();

        }catch(Exception e){
            System.out.println("Fehler bei Abfrage: " + e);
        }

        return kunde;
    }

    public void NewAuftrag(int KD_ID,String von,String bis,String grund) {
        try{
            statement = connection.createStatement();
            String sqlQuery = "INSERT INTO auftrag () VALUES ()";
        }catch (Exception e){

        }
    }

    public ArrayList LoadAuftragList() {
        try{
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM auftrag";

            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Auftrag auftrag = new Auftrag();

                auftragliste.add(auftrag);
            }
            statement.close();


        }catch (Exception e){

        }

        return auftragliste;
    }

    public Auftrag LoadAuftrag(int ID) {
        try{
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM auftrag WHERE id = '"+ID+"'";

            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {

            }

            statement.close();
        }catch (Exception e){

        }
        return auftrag;
    }

    public void DeleteAuftrag(int ID) {
        try{
            statement = connection.createStatement();
            String sqlQuery = "DELETE FROM auftrag WHERE id = '"+ID+"'";
            statement.executeUpdate(sqlQuery);
            statement.close();

        }catch (Exception e){

        }
    }

    public void EditAuftrag(int ID, Auftrag auftrag) {

    }

    public ArrayList LoadRechnungList() {

        return rechnungliste;
    }

    public void LoadRechnung(int ID) {

    }

    public void NewRechnung() {

    }


    public void db_close(){
        try{
            connection.close();
        }catch (Exception e){

        }
    }

    /* Installationsroutine*/
    public void db_install(){
        String query0 = "CREATE DATABASE IF NOT EXISTS `" + dbName + "`";
        String query1 = "USE `" + dbName + "`";
        String query2 = "CREATE TABLE IF NOT EXISTS`stammdaten` (" +
                "  `id` tinyint(1) NOT NULL," +
                "  `firma` varchar(100) NOT NULL," +
                "  `vorname` varchar(50) NOT NULL," +
                "  `nachname` varchar(50) NOT NULL," +
                "  `strasse` varchar(100) NOT NULL," +
                "  `hsnr` varchar(20) NOT NULL," +
                "  `plz` varchar(10) NOT NULL," +
                "  `ort` varchar(50) NOT NULL," +
                "  `telefon` varchar(50) NOT NULL," +
                "  `telefax` varchar(50) NOT NULL," +
                "  `web` varchar(50) NOT NULL," +
                "  `email` varchar(50) NOT NULL," +
                "  `bankname` varchar(50) NOT NULL," +
                "  `kontoinhaber` varchar(50) NOT NULL," +
                "  `bic` varchar(50) NOT NULL," +
                "  `iban` varchar(50) NOT NULL," +
                "  `steuernummer` varchar(50) NOT NULL" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8";
        String query3 = "ALTER TABLE `stammdaten`" +
                "  ADD PRIMARY KEY (`id`)";
        String query4 = "ALTER TABLE `stammdaten`" +
                "  MODIFY `id` tinyint(1) NOT NULL AUTO_INCREMENT";
        String query5 = "CREATE TABLE IF NOT EXISTS `kunden` (" +
                "  `id` int(11) NOT NULL," +
                "  `firma` varchar(100) NOT NULL," +
                "  `strasse` varchar(50) NOT NULL," +
                "  `hsnr` varchar(20) NOT NULL," +
                "  `plz` varchar(20) NOT NULL," +
                "  `ort` varchar(50) NOT NULL," +
                "  `telefon` varchar(50) NOT NULL," +
                "  `telefax` varchar(50) NOT NULL," +
                "  `web` varchar(50) NOT NULL," +
                "  `email` varchar(50) NOT NULL," +
                "  `ap_anrede` enum('Herr','Frau') NOT NULL DEFAULT 'Herr'," +
                "  `ap_vorname` varchar(50) NOT NULL," +
                "  `ap_nachname` varchar(50) NOT NULL," +
                "  `ap_telefon` varchar(50) NOT NULL," +
                "  `ap_email` varchar(50) NOT NULL," +
                "  `stdsatz` decimal(10,2) NOT NULL" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8";
        String query6 = "ALTER TABLE `kunden`" +
                "  ADD PRIMARY KEY (`id`)";
        String query7 = "ALTER TABLE `kunden`" +
                "  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT";
        String query8 = "CREATE TABLE `auftrag` (" +
                "  `id` int(11) NOT NULL," +
                "  `kunden_id` int(11) NOT NULL," +
                "  `von` varchar(20) NOT NULL," +
                "  `bis` varchar(20) NOT NULL," +
                "  `grund` varchar(250) NOT NULL" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8";
        String query9 = "ALTER TABLE `auftrag`" +
                "  ADD PRIMARY KEY (`id`);";
        String query10 = "ALTER TABLE `rechnung`" +
                "  ADD PRIMARY KEY (`id`);";
        String query11 = "CREATE TABLE `rechnung` (" +
                "  `id` int(11) NOT NULL," +
                "  `auftrag_id` int(11) NOT NULL," +
                "  `re_nr` varchar(10) NOT NULL," +
                "  `bezahlt` enum('ja','nein') NOT NULL DEFAULT 'nein'" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8";
        String query12 = "ALTER TABLE `auftrag`" +
                "  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT";
        String query13 = "ALTER TABLE `rechnung`" +
                "  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT";

        try{
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.addBatch(query0);
            statement.addBatch(query1);
            statement.addBatch(query2);
            statement.addBatch(query3);
            statement.addBatch(query4);
            statement.addBatch(query5);
            statement.addBatch(query6);
            statement.addBatch(query7);
            statement.addBatch(query8);
            statement.addBatch(query9);
            statement.addBatch(query10);
            statement.addBatch(query11);
            statement.addBatch(query12);
            statement.addBatch(query13);
            statement.executeBatch();
            connection.commit();
            statement.close();
        }catch (Exception e){

        }
    }
}
