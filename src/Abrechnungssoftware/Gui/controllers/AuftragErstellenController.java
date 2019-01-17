package Abrechnungssoftware.Gui.controllers;



import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.Gui.MainController;
import Abrechnungssoftware.Verarbeitung.Auftrag;
import Abrechnungssoftware.Verarbeitung.Kunde;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@SuppressWarnings("Duplicates")

public class AuftragErstellenController
{
    @FXML
    private AnchorPane auftragErstellen;
    @FXML
    private TableView<Kunde> kundenTable;
    @FXML
    private TableColumn<Kunde, String> idColumn, firmaColumn;
    @FXML
    private TextField grund;
    @FXML
    private DatePicker starttermin, endtermin;
    @FXML
    private Label AuftragInfoLabel;

    private MainController mainController;

    private ObservableList<Kunde> ob_liste;
    private ArrayList<Kunde> liste;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getAuftragErstellen()
    {
        return auftragErstellen;
    }

    public void KundenlisteEinfuegen()
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

    public void InfoLabelSchreiben()
    {
        int index = kundenTable.getSelectionModel().getSelectedItem().getId();
        String firma = kundenTable.getSelectionModel().getSelectedItem().getFirma();

        AuftragInfoLabel.setText("Auftrag erstellen für:\r\n" + firma + " - ID: " + index);

    }


    public void AuftragSpeichern()
    {
        DB_CON db = mainController.getDb();

        int index = kundenTable.getSelectionModel().getSelectedItem().getId();

        LocalDate startDate = starttermin.getValue();
        LocalDate endDate = endtermin.getValue();
        String von = startDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String bis = endDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        String bezeichnung = grund.getText();

        Auftrag auftrag = new Auftrag(von, bis, index, bezeichnung);
        db.NewAuftrag(auftrag);


        grund.setText("");
        starttermin.setValue(null);
        endtermin.setValue(null);


    }


}
