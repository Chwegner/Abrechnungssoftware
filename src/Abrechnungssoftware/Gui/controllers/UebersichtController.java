package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.DB.Stammdaten;
import Abrechnungssoftware.Gui.MainController;
import Abrechnungssoftware.Verarbeitung.Kunde;
import Abrechnungssoftware.Verarbeitung.Rechnung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class UebersichtController
{
    @FXML
    private AnchorPane uebersicht;
    @FXML
    private TableView kundenTable, rechnungTable;

    @FXML
    private TableColumn<Rechnung, Integer> auftragnr, rechnungnr;
    @FXML
    private TableColumn<Rechnung, String> bezahlt;

    @FXML
    private TableColumn<Kunde, String> idColumn, firmaColumn, anredeColumn, nameColumn, vornameColumn;
    @FXML
    private TableColumn<Kunde, String> strasseColumn, hausnrColumn, ortColumn, plzColumn, telefonColumn;
    @FXML
    private TableColumn<Kunde, String> faxColumn, webColumn, mailColumn, stundensatzColumn;


    private MainController mainController;
    private DB_CON db;

    private ObservableList<Kunde> obListe;
    private ArrayList<Kunde> liste;

    private ObservableList<Rechnung> obListeR;
    private ArrayList<Rechnung> listeR;


    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getUebersicht()
    {
        return uebersicht;
    }

    public void initialize()
    {
        kundenTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        rechnungTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    @SuppressWarnings("Duplicates")

    public void KundenListeEinfuegen()
    {
        db = mainController.getDb();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firmaColumn.setCellValueFactory(new PropertyValueFactory<>("firma"));
        anredeColumn.setCellValueFactory(new PropertyValueFactory<>("anrede"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        vornameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        strasseColumn.setCellValueFactory(new PropertyValueFactory<>("strasse"));
        hausnrColumn.setCellValueFactory(new PropertyValueFactory<>("hsnr."));
        ortColumn.setCellValueFactory(new PropertyValueFactory<>("ort"));
        plzColumn.setCellValueFactory(new PropertyValueFactory<>("plz"));
        telefonColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        faxColumn.setCellValueFactory(new PropertyValueFactory<>("fax"));
        webColumn.setCellValueFactory(new PropertyValueFactory<>("web"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        stundensatzColumn.setCellValueFactory(new PropertyValueFactory<>("st.satz"));

        obListe = FXCollections.observableArrayList();
        kundenTable.setItems(obListe);
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

    public void RechnungsListeEinfuegen()
    {
        db = mainController.getDb();

        auftragnr.setCellValueFactory(new PropertyValueFactory<>("auftragsnr."));
        rechnungnr.setCellValueFactory(new PropertyValueFactory<>("rechnungsnr."));
        bezahlt.setCellValueFactory(new PropertyValueFactory<>("bezahlt"));

        obListeR = FXCollections.observableArrayList();
        rechnungTable.setItems(obListeR);
        listeR = db.LoadRechnungList();


        int i = 0;
        for (Object element : listeR)
        {
            Rechnung temp = listeR.get(i);
            rechnungTable.getItems().add(temp);


            i++;
        }
        liste.clear();
    }


}
