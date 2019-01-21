package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.DB.Stammdaten;
import Abrechnungssoftware.Gui.MainController;
import Abrechnungssoftware.Verarbeitung.Kunde;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class KundenAnlegenController
{
    @FXML
    private AnchorPane kundenAnlegen;

    @FXML
    private TextField firma, name, vorname, strasse, nummer, ort, plz;
    @FXML
    private TextField telefon, telefax, website, mail;
    @FXML
    private TextField apVorname, apNachname, apTelefon, apMail, stundensatz;
    @FXML
    private ChoiceBox anrede, apAnrede;
    @FXML
    private Label statusLabel;

    private MainController mainController;


    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getKundenAnlegen()
    {
        return kundenAnlegen;
    }

    @SuppressWarnings("Duplicates")

    public void KundeNeu()
    {
        try
        {
            DB_CON db = mainController.getDb();
            Kunde kunde = mainController.getKunde();

            kunde.setFirma(firma.getText());
            kunde.setAnrede((String) anrede.getValue());
            kunde.setName(name.getText());
            kunde.setVorname(vorname.getText());
            kunde.setStrasse(strasse.getText());
            kunde.setHausnummer(nummer.getText());
            kunde.setOrt(ort.getText());
            kunde.setPlz(plz.getText());
            kunde.setTelefon(telefon.getText());
            kunde.setFax(telefax.getText());
            kunde.setWeb(website.getText());
            kunde.setEmail(mail.getText());
            kunde.setApAnrede((String) apAnrede.getValue());
            kunde.setApVorname(apVorname.getText());
            kunde.setApName(apNachname.getText());
            kunde.setApTelefon(apTelefon.getText());
            kunde.setApEmail(apMail.getText());
            kunde.setStundenSatz(Double.parseDouble(stundensatz.getText()));

            mainController.getDb().NewKunde(kunde);

            KundenFelderLeeren();

            statusLabel.setStyle("-fx-text-fill: green");
            statusLabel.setText("Kunde erstellt!");

        } catch (NullPointerException e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Felder bitte ausfüllen!");
            e.printStackTrace();

        } catch (Exception e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Fehler beim Anlegen des Kunden!");
            e.printStackTrace();

        }


    }

    @SuppressWarnings("Duplicates")

    public void KundenFelderLeeren()
    {
        firma.setText("");
        anrede.setValue("Herr");
        name.setText("");
        vorname.setText("");
        strasse.setText("");
        nummer.setText("");
        ort.setText("");
        plz.setText("");
        telefon.setText("");
        telefax.setText("");
        website.setText("");
        mail.setText("");
        apAnrede.setValue("Herr");
        apVorname.setText("");
        apNachname.setText("");
        apTelefon.setText("");
        apMail.setText("");
        stundensatz.setText("");

    }

}
