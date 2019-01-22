package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.DB.Stammdaten;
import Abrechnungssoftware.DB.helperClass;
import Abrechnungssoftware.Gui.MainController;
import Abrechnungssoftware.Verarbeitung.Kunde;
import Abrechnungssoftware.Verarbeitung.Rechnung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    private Label statusLabel;

    @FXML
    private TableColumn<helperClass, Integer> rechnungnrRechnung, auftragnrRechnung;
    @FXML
    private TableColumn<helperClass, String> bezahltRechnung;

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

    private ObservableList<helperClass> obListeR;
    private ArrayList<helperClass> listeR;


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
        statusLabel.setStyle("-fx-font-size: 2em");

        kundenTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        rechnungTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @SuppressWarnings("Duplicates")

    public void KundenListeEinfuegen()
    {
        try
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
        } catch (Exception e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Fehler beim Laden aus der DB!");
            e.printStackTrace();
        }
    }

    public void KundenAuswahlDrucken()
    {
        try
        {
            ObservableList<helperClass> list = kundenTable.getSelectionModel().getSelectedItems();

            int i = 0;
            for (Object element : list)
            {
                helperClass temp = list.get(i);

                //// Todo PDF drucken

                i++;
            }
            statusLabel.setStyle("-fx-text-fill: green");
            statusLabel.setText("PDF erstellt!");
        } catch (NullPointerException e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Kein Kunde ausgewählt!");
            e.printStackTrace();
        } catch (Exception e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Fehler beim Drucken der PDF!");
            e.printStackTrace();
        }
    }

    public void KundenAllesDrucken()
    {
        try
        {
            ObservableList<helperClass> list = kundenTable.getItems();

            int i = 0;
            for (Object element : list)
            {
                helperClass temp = list.get(i);

                //// Todo PDF drucken

                i++;

            }
            statusLabel.setStyle("-fx-text-fill: green");
            statusLabel.setText("PDF erstellt!");

        } catch (Exception e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Fehler beim Drucken der PDF!");
            e.printStackTrace();
        }


    }


    public void RechnungsListeEinfuegen()
    {
        try
        {
            db = mainController.getDb();

            rechnungnrRechnung.setCellValueFactory(new PropertyValueFactory<>("auftrag_intervall_id"));
            auftragnrRechnung.setCellValueFactory(new PropertyValueFactory<>("auftrag_id"));
            bezahltRechnung.setCellValueFactory(new PropertyValueFactory<>("bezahlt"));

            obListeR = FXCollections.observableArrayList();
            rechnungTable.setItems(obListeR);
            listeR = db.LoadRechnungList();

            int i = 0;
            for (Object element : listeR)
            {
                helperClass temp = listeR.get(i);
                rechnungTable.getItems().add(temp);


                i++;
            }
            liste.clear();
            db.LoadRechnungList().clear();
        } catch (Exception e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Fehler beim Laden aus der DB!");
            e.printStackTrace();
        }
    }

    public void RechnungAuswahlDrucken()
    {
        try
        {
            ObservableList<helperClass> list = rechnungTable.getSelectionModel().getSelectedItems();


            int i = 0;
            for (Object element : list)
            {
                helperClass temp = list.get(i);

                //// Todo PDF drucken

                i++;
            }
            statusLabel.setStyle("-fx-text-fill: green");
            statusLabel.setText("PDF erstellt!");
        } catch (NullPointerException e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Keine Rechnung ausgewählt!");
            e.printStackTrace();
        } catch (Exception e)
        {
            statusLabel.setStyle("-fx-text-fill: red");
            statusLabel.setText("Fehler beim Drucken der PDF!");
            e.printStackTrace();
        }
    }

    public void RechnungAllesDrucken()
    {
        try
    {
        ObservableList<helperClass> list = rechnungTable.getItems();

        int i = 0;
        for (Object element : list)
        {
            helperClass temp = list.get(i);

            //// Todo PDF drucken

            i++;

        }
        statusLabel.setStyle("-fx-text-fill: green");
        statusLabel.setText("PDF erstellt!");

    } catch (Exception e)
    {
        statusLabel.setStyle("-fx-text-fill: red");
        statusLabel.setText("Fehler beim Drucken der PDF!");
        e.printStackTrace();
    }


    }


}
