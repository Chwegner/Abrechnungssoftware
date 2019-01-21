package Abrechnungssoftware.Gui;

import Abrechnungssoftware.DB.Stammdaten;
import Abrechnungssoftware.Gui.controllers.*;
import Abrechnungssoftware.Verarbeitung.Kunde;
import Abrechnungssoftware.DB.DB_CON;

import Abrechnungssoftware.Verarbeitung.Rechnung;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.ConnectException;


public class MainController extends Application

{
    @FXML
    private VBox mainPane;
    DB_CON db = new DB_CON();
    Stammdaten stammdaten = new Stammdaten();
    Kunde kunde = new Kunde();
    Rechnung rechnung = new Rechnung();

    /// Controllerklassen verbinden ///

    @FXML
    private MenuBarController menuBarController;
    @FXML
    private UebersichtController uebersichtController;
    @FXML
    private RechnungErstellenController rechnungErstellenController;
    @FXML
    private AuftragErstellenController auftragErstellenController;
    @FXML
    private KundenAnlegenController kundenAnlegenController;
    @FXML
    private KundenUebersichtController kundenUebersichtController;
    @FXML
    private StammdatenAendernController stammdatenAendernController;
    private FehltageController fehltageController = new FehltageController();
    private VorschauController vorschauController = new VorschauController();

    @FXML
    private void initialize()
    {
        try
        {
            boolean dbopen;
            dbopen = db.db_open();

            if (dbopen == false)
            {
                AlertBox.display("Fehler", "Datenbank konnte nicht geöffnet werden!");
                System.exit(0);
            }

            menuBarController.injectMainController(this);
            uebersichtController.injectMainController(this);
            rechnungErstellenController.injectMainController(this);
            auftragErstellenController.injectMainController(this);
            kundenAnlegenController.injectMainController(this);
            kundenUebersichtController.injectMainController(this);
            stammdatenAendernController.injectMainController(this);
            fehltageController.injectMainController(this);
            vorschauController.injectMainController(this);

            getMenuBarController().UebersichtFensterAufrufen();

        } catch (Exception e)
        {
            AlertBox.display("Fehler!", "Programm konnte nicht initialisiert werden!");
            e.printStackTrace();
            System.exit(0);

        }
    }

    //// Getter und Setter ////

    public VBox getMainPane()
    {
        return mainPane;
    }

    public UebersichtController getUebersichtController()
    {
        return uebersichtController;
    }

    public MenuBarController getMenuBarController()
    {
        return menuBarController;
    }

    public AuftragErstellenController getAuftragErstellenController()
    {
        return auftragErstellenController;
    }

    public KundenAnlegenController getKundenAnlegenController()
    {
        return kundenAnlegenController;
    }

    public KundenUebersichtController getKundenUebersichtController()
    {
        return kundenUebersichtController;
    }

    public RechnungErstellenController getRechnungErstellenController()
    {
        return rechnungErstellenController;
    }

    public StammdatenAendernController getStammdatenAendernController()
    {
        return stammdatenAendernController;
    }

    public FehltageController getFehltageController()
    {
        return fehltageController;
    }

    public VorschauController getVorschauController()
    {
        return vorschauController;
    }

    public DB_CON getDb()
    {
        return db;
    }

    public Stammdaten getStammdaten()
    {
        return stammdaten;
    }

    public Kunde getKunde()
    {
        return kunde;
    }

    public Rechnung getRechnung()
    {
        return rechnung;
    }

    /// App starten ///

    @Override
    public void start(Stage stage)
    {

        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Abrechnungssoftware");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (ConnectException e)
        {
            AlertBox.display("Fehler", "Datenbankverbindung gescheitert!");
            e.printStackTrace();
            System.exit(0);
        } catch (LoadException e)
        {
            AlertBox.display("Fehler", "Fehler beim Laden der FXML files!");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e)
        {
            AlertBox.display("Fehler!", "Programmstart gescheitert!");
            e.printStackTrace();
            System.exit(0);
        }
    }

}


