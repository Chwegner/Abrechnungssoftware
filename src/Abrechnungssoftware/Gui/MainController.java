package Abrechnungssoftware.Gui;

import Abrechnungssoftware.Gui.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController extends Application
{
    @FXML
    private VBox mainPane;

    /// Controllerklassen verbinden ///

    @FXML
    private MenuBarController menuBarController;
    @FXML
    private UebersichtController uebersichtController;
    @FXML
    private DruckenController druckenController;
    @FXML
    private RechnungErstellenController rechnungErstellenController;
    @FXML
    private OffeneRechnungenController offeneRechnungenController;
    @FXML
    private BezahlteRechnungenController bezahlteRechnungenController;
    @FXML
    private AuftragErstellenController auftragErstellenController;
    @FXML
    private OffeneAuftraegeController offeneAuftraegeController;
    @FXML
    private KundenAnlegenController kundenAnlegenController;
    @FXML
    private KundenUebersichtController kundenUebersichtController;
    @FXML
    private StammdatenAendernController stammdatenAendernController;


    @FXML
    private void initialize()
    {
        menuBarController.injectMainController(this);
        uebersichtController.injectMainController(this);
        druckenController.injectMainController(this);
        rechnungErstellenController.injectMainController(this);
        offeneRechnungenController.injectMainController(this);
        bezahlteRechnungenController.injectMainController(this);
        auftragErstellenController.injectMainController(this);
        offeneAuftraegeController.injectMainController(this);
        kundenAnlegenController.injectMainController(this);
        kundenUebersichtController.injectMainController(this);
        stammdatenAendernController.injectMainController(this);

        getMenuBarController().UebersichtFensterAufrufen();
    }

    //// Getter und Setter ////

    public VBox getMainPane()
    {
        return mainPane;
    }

    public DruckenController getDruckenController()
    {
        return druckenController;
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

    public BezahlteRechnungenController getBezahlteRechnungenController()
    {
        return bezahlteRechnungenController;
    }

    public KundenAnlegenController getKundenAnlegenController()
    {
        return kundenAnlegenController;
    }

    public KundenUebersichtController getKundenUebersichtController()
    {
        return kundenUebersichtController;
    }

    public OffeneAuftraegeController getOffeneAuftraegeController()
    {
        return offeneAuftraegeController;
    }

    public OffeneRechnungenController getOffeneRechnungenController()
    {
        return offeneRechnungenController;
    }

    public RechnungErstellenController getRechnungErstellenController()
    {
        return rechnungErstellenController;
    }

    public StammdatenAendernController getStammdatenAendernController()
    {
        return stammdatenAendernController;
    }


    /// App starten ///

    @Override
    public void start(Stage stage) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Abrechnungssoftware");
        stage.setScene(scene);
        stage.show();
    }

}


