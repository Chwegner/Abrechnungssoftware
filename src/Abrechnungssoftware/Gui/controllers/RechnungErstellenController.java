package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.Gui.MainController;
import Abrechnungssoftware.Verarbeitung.Auftrag;
import Abrechnungssoftware.Verarbeitung.Kunde;
import Abrechnungssoftware.Verarbeitung.Rechnung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<Auftrag> auftragTable;
    @FXML
    private TableColumn<Auftrag, String> firma, von, bis, bezahlt;
    @FXML
    private TableColumn<Auftrag, Integer> auftragNr;
    @FXML
    private Label rechnungLabel, statusLabel;


    private MainController mainController;
    private DB_CON db;
    private Auftrag auftrag;

    private ObservableList<Auftrag> auftragObservableList;
    private ArrayList<Auftrag> liste;

    private ArrayList<Integer> erstellenListe;


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


        db = mainController.getDb();
        Auftrag auftrag;

        try
        {
            //firma.setCellValueFactory(new PropertyValueFactory<>("firma"));
            auftragNr.setCellValueFactory(new PropertyValueFactory<>("auftrags-nr."));
            von.setCellValueFactory(new PropertyValueFactory<>("von"));
            bis.setCellValueFactory(new PropertyValueFactory<>("bis"));
            bezahlt.setCellValueFactory(new PropertyValueFactory<>("bezahlt?"));

            auftragObservableList = FXCollections.observableArrayList();
            auftragTable.setItems(auftragObservableList);
            liste = db.LoadRechnungList();

            int i = 0;
            for (Object element : liste)
            {
                Auftrag temp = liste.get(i);


                i++;
            }
            liste.clear();


        } catch (Exception e)
        {

            statusLabel.setText("Fehler beim Laden der Auträge!");
            statusLabel.setTextFill(Color.RED);
            e.printStackTrace();
        }


    }

    public void RechnungenAuswahl()
    {
        int id = auftragTable.getSelectionModel().getSelectedItem().getId();

        String text = rechnungLabel.getText() + " '" + id + "' ";
        rechnungLabel.setText(text);

        erstellenListe.add(id);

    }

    public void RechnungAbbrechen()
    {
        getRechnungErstellen();
        AuftraegeEinfuegen();

        erstellenListe.clear();
        rechnungLabel.setText("Erstelle Rechnung für: ");

    }


    public void RechnungErstellen()
    {


        db = mainController.getDb();

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
