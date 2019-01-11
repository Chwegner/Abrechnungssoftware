package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.Gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class OffeneRechnungenController
{
    @FXML
    private AnchorPane offeneRechnungen;

    private MainController mainController;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }


    public AnchorPane getOffeneRechnungen()
    {
        return offeneRechnungen;
    }
}
