package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.Gui.MainController;
import Abrechnungssoftware.Verarbeitung.Kunde;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class KundenUebersichtController
{
    @FXML
    private AnchorPane kundenUebersicht;
    @FXML
    private ChoiceBox anrede, apAnrede;
    @FXML
    private TextField firma, name, vorname, strasse, nummer, ort, plz, telefon, telefax, website, mail;
    @FXML
    private TextField apVorname, apNachname, apTelefon, apMail, stundensatz;
    @FXML
    private TableView<Kunde> kundenTable;
    @FXML
    private TableColumn<Kunde, String> idColumn, firmaColumn;
    @FXML
    private SplitPane kundensplit;

    private ObservableList<Kunde> ob_liste;
    private ArrayList<Kunde> liste;

    private MainController mainController;


    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getKundenUebersicht()
    {
        return kundenUebersicht;
    }

    public void initialize()
    {

    }


    @SuppressWarnings("Duplicates")

    public void KundenAuflisten()
    {

        DB_CON db = mainController.getDb();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firmaColumn.setCellValueFactory(new PropertyValueFactory<>("firma"));


        ob_liste = FXCollections.observableArrayList();
        kundenTable.setItems(ob_liste);
        liste = db.LoadKundenList();


        int i = 0;
        for (Object element : liste)
        {
            Kunde temp = liste.get(i);
            kundenTable.getItems().add(temp);


            i++;
        }
        liste.clear();
    }


    public void KundenAuswahl()
    {


        DB_CON db = mainController.getDb();

        int index = kundenTable.getSelectionModel().getSelectedItem().getId();
        Kunde k = db.LoadKunde(index);

        firma.setText(k.getFirma());
        anrede.setValue(k.getAnrede());
        name.setText(k.getName());
        vorname.setText(k.getVorname());
        strasse.setText(k.getStrasse());
        nummer.setText(k.getHausnummer());
        ort.setText(k.getOrt());
        plz.setText(k.getPlz());
        telefon.setText(k.getTelefon());
        telefax.setText(k.getFax());
        website.setText(k.getWeb());
        mail.setText(k.getEmail());
        apAnrede.setValue(k.getApAnrede());
        apVorname.setText(k.getApVorname());
        apNachname.setText(k.getApName());
        apTelefon.setText(k.getApTelefon());
        apMail.setText(k.getApEmail());
        stundensatz.setText(Double.toString(k.getStundenSatz()));

    }

    @SuppressWarnings("Duplicates")

    public void TextfelderLeeren()
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
        apAnrede.setValue("Herr");
        apVorname.setText("");
        apNachname.setText("");
        apTelefon.setText("");
        apMail.setText("");
        stundensatz.setText("");

    }


    @SuppressWarnings("Duplicates")

    public void KundenBearbeiten()
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
            kunde.setName(apNachname.getText());
            kunde.setTelefon(apTelefon.getText());
            kunde.setApEmail(apMail.getText());
            kunde.setStundenSatz(Double.parseDouble(stundensatz.getText()));

            int index = kundenTable.getSelectionModel().getSelectedItem().getId();
            db.EditKunde(index, kunde);
            TextfelderLeeren();
            KundenAuflisten();


        } catch (Exception e)
        {
            e.printStackTrace();

        }

    }

    public void KundenLoeschen()
    {
        DB_CON db = mainController.getDb();

        int index = kundenTable.getSelectionModel().getSelectedItem().getId();
        Kunde k = db.LoadKunde(index);

        db.DeleteKunde(index);
        mainController.getMenuBarController().KundenUebersichtAufrufen();
        TextfelderLeeren();

    }

    public ObservableList<Kunde> getOb_liste()
    {
        return ob_liste;
    }

    public SplitPane getKundensplit()
    {
        return kundensplit;
    }
}
