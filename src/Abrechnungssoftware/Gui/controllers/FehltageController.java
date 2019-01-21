package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.Gui.MainController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FehltageController
{
    private MainController mainController;

    private Stage window;
    private Label label;
    private TextField tageEingabe;
    private Button okButton;
    private Button cancelButton;
    private VBox layout;
    private Scene scene;

    private String tage;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }


    public void FehltageFenster(int ID, int fehltage)
    {

        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Korrekturtage");
        window.setMinWidth(250);
        window.setMinHeight(250);

        label = new Label();
        label.setText("Korrekturtage anpassen:");

        tageEingabe = new TextField(Integer.toString(fehltage));
        tageEingabe.setMinWidth(50);
        tageEingabe.setMaxWidth(50);

        okButton = new Button("Speichern");

        okButton.setOnAction(event ->
        {
            tage = tageEingabe.getText();
            int fehltage1 = Integer.parseInt(tage);

            mainController.getDb().SaveKorrekturtage(ID, fehltage1);
            mainController.getMenuBarController().RechnungErstellenAufrufen();
            window.close();
        });

        layout = new VBox(10);
        layout.getChildren().addAll(label, tageEingabe, okButton);
        layout.setAlignment(Pos.CENTER);

        scene = new Scene(layout);
        String css = this.getClass().getResource("../css/MainStyle.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.showAndWait();
    }


}
