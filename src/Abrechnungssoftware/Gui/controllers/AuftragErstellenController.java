package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.Gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AuftragErstellenController
{
    @FXML
    private AnchorPane auftragErstellen;

    private MainController mainController;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getAuftragErstellen()
    {
        return auftragErstellen;
    }
}
