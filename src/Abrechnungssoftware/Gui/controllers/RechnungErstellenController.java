package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.DB.helperClass;
import Abrechnungssoftware.Gui.MainController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Collections;

public class RechnungErstellenController
{
    @FXML
    private AnchorPane rechnungErstellen;
    @FXML
    private TableView<helperClass> auftragTable;
    @FXML
    private TableColumn<helperClass, String> grund, von, bis, firma;
    @FXML
    private TableColumn<helperClass, Integer> auftragNr, kundeNr, tage, id, fehltageRechnung;
    @FXML
    private Label rechnungLabel, statusLabel;

    private int fehltage = 0;

    private MainController mainController;

    private ArrayList<Integer> intervallListe = new ArrayList<>();


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
        statusLabel.setStyle("-fx-font-size: 2em;");
    }

    public void AuftraegeEinfuegen()
    {
        try
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
            fehltageRechnung.setCellValueFactory(new PropertyValueFactory<>("korrektur_tage"));

            ObservableList<helperClass> ob_liste = FXCollections.observableArrayList();
            auftragTable.setItems(ob_liste);
            ArrayList<helperClass> liste = db.LoadRechnungList();

            int i = 0;
            for (Object element : liste)
            {
                helperClass temp = liste.get(i);

                auftragTable.getItems().add(temp);

                i++;
            }
            liste.clear();

        } catch (Exception e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Fehler beim Laden aus der DB!");
            e.printStackTrace();

        }
    }

    public void RechnungenAuswahl()
    {
        try
        {
            int index = auftragTable.getSelectionModel().getSelectedItem().getAuftrag_intervall_id();

            if (!intervallListe.contains(index))
            {
                intervallListe.add(index);
            } else
            {
                intervallListe.removeIf(integer -> integer == index);
            }
            Collections.sort(intervallListe);

            RechnungenAnzeigen();

        } catch (Exception e)
        {
            e.printStackTrace();

        }
    }

    public void RechnungenAnzeigen()
    {
        try
        {
            String liste = "";

            for (int i = 0; i < intervallListe.size(); i++)
            {
                liste = liste + "ID: " + intervallListe.get(i) + " ";
            }

            rechnungLabel.setText("Rechnungen erstellen für: " + liste);

        } catch (Exception e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Fehler beim Anzeigen der Auswahl!");
            e.printStackTrace();
        }
    }


    public void RechnungAbbrechen()
    {
        try
        {
            getRechnungErstellen();
            AuftraegeEinfuegen();

            intervallListe.clear();
            rechnungLabel.setText("Erstelle Rechnung für: ");
        } catch (Exception e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Fehler beim Löschen der Auswahl!");
            e.printStackTrace();
        }
    }


    public void RechnungErstellen()
    {
        try
        {
            DB_CON db = mainController.getDb();

            int index = auftragTable.getSelectionModel().getSelectedItem().getId();

//            for (int i = 0; i < intervallListe.size(); i++)
//            {
//
//                db.NewRechnung(intervallListe.get(i));
//
//            }

            mainController.getVorschauController().Vorschau();

            mainController.getMenuBarController().RechnungErstellenAufrufen();
            intervallListe.clear();

        } catch (NullPointerException e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Keine Rechnung ausgewählt!");
            e.printStackTrace();
        } catch (Exception e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Fehler beim Erstellen der Rechnung!");
            e.printStackTrace();
        }
    }

    public void FehltageFenster()
    {
        int id = auftragTable.getSelectionModel().getSelectedItem().getAuftrag_intervall_id();
        fehltage = mainController.getDb().LoadKorrekturtage(id);
        mainController.getFehltageController().FehltageFenster(id, fehltage);
    }

    public ArrayList<Integer> getIntervallListe()
    {
        return intervallListe;
    }

    public TableView<helperClass> getAuftragTable()
    {
        return auftragTable;
    }

    public Label getStatusLabel()
    {
        return statusLabel;
    }
}
