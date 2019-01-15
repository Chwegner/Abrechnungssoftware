package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.Gui.MainController;
import Abrechnungssoftware.Verarbeitung.Kunde;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class KundenUebersichtController
{
    @FXML
    private AnchorPane kundenUebersicht;
    @FXML
    private ChoiceBox anrede;
    @FXML
    private TextField firma, name, vorname, strasse, nummer, ort, plz, telefon, telefax, website, mail;
    @FXML
    private TableView kundentable;

    private MainController mainController;


    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getKundenUebersicht()
    {
        return kundenUebersicht;
    }

    public void KundenAuflisten()
    {
        DB_CON db = mainController.getDb();
        Kunde kunde = mainController.getKunde();

        ArrayList<Kunde> liste3 = db.LoadKundenList();
        ObservableList<Kunde> observableList = FXCollections.observableList(liste3);
        TableColumn<Kunde, String> spalte = new TableColumn<>("Firma");
        spalte.setMinWidth(200);
        spalte.setCellValueFactory(new PropertyValueFactory<>("firma"));

        kundentable.setItems(observableList);
        kundentable.getColumns().add(spalte);



            kundentable.getItems().add(observableList);

    }


    public void KundeBearbeiten()
    {
        try
        {
            DB_CON db = mainController.getDb();
            Kunde kunde = mainController.getKunde();

            firma.setText(kunde.getFirma());
            anrede.setValue(kunde.getAnrede());
            name.setText(kunde.getName());
            vorname.setText(kunde.getVorname());
            strasse.setText(kunde.getStrasse());
            nummer.setText(kunde.getHausnummer());
            ort.setText(kunde.getOrt());
            plz.setText(kunde.getPlz());
            telefon.setText(kunde.getTelefon());
            telefax.setText(kunde.getFax());
            website.setText(kunde.getWeb());
            mail.setText(kunde.getEmail());

            //mainController.getDb().EditKunde();

        } catch (Exception e)
        {
            e.printStackTrace();

        }

    }


}
