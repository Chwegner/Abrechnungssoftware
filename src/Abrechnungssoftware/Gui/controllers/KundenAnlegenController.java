package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.DB.Stammdaten;
import Abrechnungssoftware.Gui.MainController;
import Abrechnungssoftware.Verarbeitung.Kunde;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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
    private ChoiceBox anrede;

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

            mainController.getDb().NewKunde(kunde);

            KundenFelderLeeren();


        } catch (Exception e)
        {
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

    }

}
