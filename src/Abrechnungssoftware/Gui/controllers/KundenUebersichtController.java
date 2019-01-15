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
import java.util.List;

public class KundenUebersichtController
{
    @FXML
    private AnchorPane kundenUebersicht;
    @FXML
    private ChoiceBox anrede;
    @FXML
    private TextField firma, name, vorname, strasse, nummer, ort, plz, telefon, telefax, website, mail;
    @FXML
    private TableView<Kunde> kundenTable;

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

        TableColumn<Kunde, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Kunde, String> firmaColumn = new TableColumn<>("Firma");
        firmaColumn.setCellValueFactory(new PropertyValueFactory<>("firma"));

        kundenTable.getColumns().addAll(idColumn, firmaColumn);

        ObservableList<Kunde> ob_liste = FXCollections.observableArrayList();
        kundenTable.setItems(ob_liste);
        ArrayList<Kunde> liste = db.LoadKundenList();


        int i = 0;
        for (Object element : liste)
        {
            Kunde temp = liste.get(i);
            kundenTable.getItems().add(temp);


            i++;
        }
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

    }


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

            int index = kundenTable.getSelectionModel().getSelectedItem().getId();
            db.EditKunde(index, kunde);


        } catch (Exception e)
        {
            e.printStackTrace();

        }

    }


}
