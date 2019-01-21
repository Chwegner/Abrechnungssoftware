package Abrechnungssoftware.Gui.controllers;

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

    public void FehltageFenster(int ID, int fehltage)
    {

        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Korrekturtage");
        window.setMinWidth(250);
        window.setMinHeight(250);

        label = new Label();
        label.setText("Korrekturtage:");

        tageEingabe = new TextField(Integer.toString(fehltage));
        tageEingabe.setMinWidth(50);
        tageEingabe.setMaxWidth(50);

        okButton = new Button("Speichern");
        tage = tageEingabe.getText();
        fehltage = Integer.parseInt(tage);
        int finalFehltage = fehltage;
        okButton.setOnAction(e -> mainController.getDb().SaveKorrekturtage(ID, finalFehltage));

        layout = new VBox(10);
        layout.getChildren().addAll(label, tageEingabe, okButton, cancelButton);
        layout.setAlignment(Pos.CENTER);

        scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

}
