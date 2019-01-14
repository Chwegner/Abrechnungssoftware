package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.Gui.MainController;
import Abrechnungssoftware.Verarbeitung.Kunde;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class KundenUebersichtController
{
    @FXML
    private AnchorPane kundenUebersicht;

    private MainController mainController;
    private DB_CON db;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getKundenUebersicht()
    {
        return kundenUebersicht;
    }


}
