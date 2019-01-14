package Abrechnungssoftware.DB;


import Abrechnungssoftware.Verarbeitung.Kunde;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
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
import java.util.List;
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
    protected ArrayList<Kunde> kundenliste = new ArrayList<Kunde>();
    protected ArrayList auftragliste;
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
            String sqlQuery = "";

            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
//                firma, name, vorname, strasse, hausnummer, ort, plz, telefon, fax, email,
//                        apName, apVorname, apTelefon, apEmail;
//                double
//                        stundenSatz ;
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
                kunde.setAnrede(resultSet.getString("anrede"));
                kunde.setApVorname(resultSet.getString("ap_vorname"));
                kunde.setApName(resultSet.getString("ap_name"));
                kunde.setApTelefon(resultSet.getString("ap_telefon"));
                kunde.setApEmail(resultSet.getString("ap_email"));
                kunde.setStundenSatz(resultSet.getDouble("stdsatz"));
                kundenliste.add(kunde);
            }
            statement.close();

        } catch (Exception e){

        }
        return kundenliste;
    }

    public void EditKunde(int ID) {

    }

    public void NewKunde() {

    }

    public void DeleteKunde(int ID) {

    }

    public void LoadKunde(int ID) {

    }

    public void NewAuftrag(int KD_ID,String von,String bis,String grund) {

    }

    public ArrayList LoadAuftragList() {

        return auftragliste;
    }

    public void LoadAuftrag(int ID) {

    }

    public void DeleteAuftrag(int ID) {

    }

    public void EditAuftrag(int ID) {

    }

    public ArrayList LoadRechnungList() {

        return rechnungliste;
    }

    public void LoadRechnung(int ID) {

    }

    public void NewRechnung() {

    }
}
