package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.Gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class OffeneAuftraegeController
{
    @FXML
    private AnchorPane offeneAuftraege;

    private MainController mainController;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getOffeneAuftraege()
    {
        return offeneAuftraege;
    }
}
