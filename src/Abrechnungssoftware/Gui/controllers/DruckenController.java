package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.Gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class DruckenController
{
    @FXML
    private AnchorPane drucken;

    private MainController mainController;


    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getDrucken()
    {
        return drucken;
    }
}
