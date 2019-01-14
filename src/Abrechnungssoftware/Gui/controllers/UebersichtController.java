package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.DB.Stammdaten;
import Abrechnungssoftware.Gui.MainController;
import Abrechnungssoftware.Verarbeitung.Kunde;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UebersichtController
{
    @FXML
    private AnchorPane uebersicht;
    @FXML
    private TableView kundenTable;


    private MainController mainController;
    private DB_CON db;


    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getUebersicht()
    {
        return uebersicht;
    }

    public void KundenListeEinfuegen()
    {
        db = mainController.getDb();


    }
}
