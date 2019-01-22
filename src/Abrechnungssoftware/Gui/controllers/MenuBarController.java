package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.Gui.AlertBox;
import Abrechnungssoftware.Gui.MainController;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;

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
        try
        {
            mainController.getMainPane().getChildren().clear();
            mainController.getUebersichtController().KundenListeEinfuegen();
            mainController.getUebersichtController().RechnungsListeEinfuegen();
            mainController.getUebersichtController().AuftragOffenListe();
            mainController.getUebersichtController().AuftragEndeListe();
            mainController.getMainPane().getChildren().add(mainController.getUebersichtController().getUebersicht());
        } catch (Exception e)
        {
            AlertBox.display("Fehler", "Übersicht konnte nicht geladen werden");
            System.exit(0);
        }

    }

    public void RechnungErstellenAufrufen()
    {
        try
        {
            mainController.getMainPane().getChildren().clear();
            mainController.getRechnungErstellenController().AuftraegeEinfuegen();
            mainController.getMainPane().getChildren().add(mainController.getRechnungErstellenController().getRechnungErstellen());

        } catch (Exception e)
        {
            AlertBox.display("Fehler", "Fenster konnte nicht geladen werden");
            mainController.getMenuBarController().UebersichtFensterAufrufen();
        }
    }


    public void AuftragErstellenAufrufen()
    {
        try
        {
            mainController.getMainPane().getChildren().clear();
            mainController.getMainPane().getChildren().add(mainController.getAuftragErstellenController().getAuftragErstellen());
            mainController.getAuftragErstellenController().KundenlisteEinfuegen();
        } catch (Exception e)
        {
            AlertBox.display("Fehler", "Fenster konnte nicht geladen werden");
            mainController.getMenuBarController().UebersichtFensterAufrufen();
        }
    }

    public void KundenAnlegenAufrufen()
    {
        try
        {
            mainController.getMainPane().getChildren().clear();
            mainController.getMainPane().getChildren().add(mainController.getKundenAnlegenController().getKundenAnlegen());
        } catch (Exception e)
        {
            AlertBox.display("Fehler", "Fenster konnte nicht geladen werden");
            mainController.getMenuBarController().UebersichtFensterAufrufen();
        }
    }

    public void KundenUebersichtAufrufen()
    {
        try
        {
            mainController.getMainPane().getChildren().clear();
            mainController.getMainPane().getChildren().add(mainController.getKundenUebersichtController().getKundenUebersicht());
            mainController.getKundenUebersichtController().KundenAuflisten();
        } catch (Exception e)
        {
            AlertBox.display("Fehler", "Fenster konnte nicht geladen werden");
            mainController.getMenuBarController().UebersichtFensterAufrufen();
        }
    }

    public void StammdatenAendernAufrufen()
    {
        try
        {
            mainController.getMainPane().getChildren().clear();
            mainController.getMainPane().getChildren().add(mainController.getStammdatenAendernController().getStammdatenAendern());
            mainController.getStammdatenAendernController().DatensaetzeEinsetzen();
        } catch (Exception e)
        {
            AlertBox.display("Fehler", "Fenster konnte nicht geladen werden");
            mainController.getMenuBarController().UebersichtFensterAufrufen();
        }
    }


}
