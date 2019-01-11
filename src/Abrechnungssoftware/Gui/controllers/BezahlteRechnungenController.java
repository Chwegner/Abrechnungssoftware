package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.Gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class BezahlteRechnungenController
{
    @FXML
    private AnchorPane bezahlteRechnungen;

    private MainController mainController;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getBezahlteRechnungen()
    {
        return bezahlteRechnungen;
    }
}
