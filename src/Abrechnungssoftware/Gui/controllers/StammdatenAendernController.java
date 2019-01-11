package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.Gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class StammdatenAendernController
{
    @FXML
    private AnchorPane stammdatenAendern;

    private MainController mainController;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getStammdatenAendern()
    {
        return stammdatenAendern;
    }
}
