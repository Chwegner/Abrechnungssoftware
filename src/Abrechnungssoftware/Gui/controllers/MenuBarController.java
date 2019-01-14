package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.Gui.MainController;

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

    public void UebersichtFensterAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getUebersichtController().KundenListeEinfuegen();
        mainController.getMainPane().getChildren().add(mainController.getUebersichtController().getUebersicht());


    }

    public void RechnungErstellenAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getMainPane().getChildren().add(mainController.getRechnungErstellenController().getRechnungErstellen());

    }


    public void AuftragErstellenAufrufen()
    {
        mainController.getMainPane().getChildren().clear();
        mainController.getMainPane().getChildren().add(mainController.getAuftragErstellenController().getAuftragErstellen());

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
        mainController.getStammdatenAendernController().DatensaetzeEinsetzen();
    }


}
