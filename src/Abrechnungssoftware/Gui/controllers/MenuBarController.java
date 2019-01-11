package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.Gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MenuBarController
{


    /// MainController //////////////////

    private MainController mainController;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    /////////////////////////////////////

    //// Button-Events ////

    public void Exit()
    {
        System.exit(0);
    }


    public void DruckFensterAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getMainPane().getChildren().add(mainController.getDruckenController().getDrucken());

    }

    public void UebersichtFensterAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getMainPane().getChildren().add(mainController.getUebersichtController().getUebersicht());

    }

    public void RechnungErstellenAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getMainPane().getChildren().add(mainController.getRechnungErstellenController().getRechnungErstellen());

    }

    public void OffeneRechnungenAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getMainPane().getChildren().add(mainController.getOffeneRechnungenController().getOffeneRechnungen());

    }

    public void BezahlteRechnungenAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getMainPane().getChildren().add(mainController.getBezahlteRechnungenController().getBezahlteRechnungen());

    }

    public void AuftragErstellenAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getMainPane().getChildren().add(mainController.getAuftragErstellenController().getAuftragErstellen());

    }

    public void OffeneAuftraegeAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getMainPane().getChildren().add(mainController.getOffeneAuftraegeController().getOffeneAuftraege());

    }

    public void KundenAnlegenAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getMainPane().getChildren().add(mainController.getKundenAnlegenController().getKundenAnlegen());

    }

    public void KundenUebersichtAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getMainPane().getChildren().add(mainController.getKundenUebersichtController().getKundenUebersicht());
    }

    public void StammdatenAendernAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getMainPane().getChildren().add(mainController.getStammdatenAendernController().getStammdatenAendern());
    }


}
