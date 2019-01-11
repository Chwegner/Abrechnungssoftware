package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.Gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UebersichtController
{
    @FXML
    private AnchorPane uebersicht;

    private MainController mainController;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getUebersicht()
    {
        return uebersicht;
    }
}
