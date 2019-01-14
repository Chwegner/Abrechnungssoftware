package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.Gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

public class KundenUebersichtController
{
    @FXML
    private AnchorPane kundenUebersicht;
    @FXML
    private ChoiceBox anrede;

    private MainController mainController;
    private DB_CON db;
    private String herr;


    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getKundenUebersicht()
    {
        return kundenUebersicht;
    }

}
