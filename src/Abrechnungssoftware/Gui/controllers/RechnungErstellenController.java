package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.DB.helperClass;
import Abrechnungssoftware.Gui.MainController;
import Abrechnungssoftware.Verarbeitung.Auftrag;
import Abrechnungssoftware.Verarbeitung.Kunde;
import Abrechnungssoftware.Verarbeitung.Rechnung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class RechnungErstellenController
{
    @FXML
    private AnchorPane rechnungErstellen;
    @FXML
    private TableView<helperClass> auftragTable;
    @FXML
    private TableColumn<helperClass, String> grund, von, bis, firma;
    @FXML
    private TableColumn<helperClass, Integer> auftragNr, kundeNr, tage, id;
    @FXML
    private Label rechnungLabel, statusLabel;


    private MainController mainController;

    private ArrayList<String> idListe;


    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }


    public AnchorPane getRechnungErstellen()
    {
        return rechnungErstellen;
    }

    public void initialize()
    {
        auftragTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        statusLabel.setStyle("-fx-font-size: 1em;");


    }

    public void AuftraegeEinfuegen()
    {


        DB_CON db = mainController.getDb();


        id.setCellValueFactory(new PropertyValueFactory<>("auftrag_intervall_id"));
        firma.setCellValueFactory(new PropertyValueFactory<>("firma"));
        grund.setCellValueFactory(new PropertyValueFactory<>("grund"));
        kundeNr.setCellValueFactory(new PropertyValueFactory<>("kunden_id"));
        auftragNr.setCellValueFactory(new PropertyValueFactory<>("auftrag_id"));
        von.setCellValueFactory(new PropertyValueFactory<>("intervall_von"));
        bis.setCellValueFactory(new PropertyValueFactory<>("intervall_bis"));
        tage.setCellValueFactory(new PropertyValueFactory<>("tage"));


        ObservableList<helperClass> ob_liste = FXCollections.observableArrayList();
        auftragTable.setItems(ob_liste);
        ArrayList<helperClass> liste = db.LoadRechnungList();


        int i = 0;
        for (Object element : liste)
        {
            helperClass temp = liste.get(i);
            System.out.println(temp.getAuftrag_id());
            auftragTable.getItems().add(temp);


            i++;
        }


        liste.clear();


    }

    public void RechnungenAuswahl()
    {

    }

    public void RechnungAbbrechen()
    {
        getRechnungErstellen();
        AuftraegeEinfuegen();

        //erstellenListe.clear();
        rechnungLabel.setText("Erstelle Rechnung für: ");

    }


    public void RechnungErstellen()
    {


        DB_CON db = mainController.getDb();

        int index = auftragTable.getSelectionModel().getSelectedItem().getId();

        try
        {

        } catch (Exception e)
        {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Fehler beim Erstellen der Rechnung!");
            e.printStackTrace();
        }


    }


}
