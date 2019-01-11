package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.Gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class KundenAnlegenController
{
    @FXML
    private AnchorPane kundenAnlegen;

    private MainController mainController;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public AnchorPane getKundenAnlegen()
    {
        return kundenAnlegen;
    }
}
