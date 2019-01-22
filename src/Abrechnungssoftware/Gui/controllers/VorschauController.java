package Abrechnungssoftware.Gui.controllers;

import Abrechnungssoftware.Gui.MainController;
import Abrechnungssoftware.Verarbeitung.Rechnung;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class VorschauController
{
    private MainController mainController;

    private ImageView vorschau;
    private Button okButton, cancelButton;
    private Stage window;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void Vorschau()
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("PDF Vorschau");

        ScrollPane pane = new ScrollPane();
        pane.setFitToWidth(true);

        try
        {
            Rechnung rechnung = new Rechnung();
            rechnung.rechnungErstellen(mainController.getRechnungErstellenController().getIntervallListe(), false);
            pdfToImage();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        vorschau = new ImageView();
        Image image = null;

        try
        {
            image = new Image(new FileInputStream("vorschau-0.png"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        vorschau.setImage(image);
        vorschau.setFitHeight(image.getHeight() / 2);
        vorschau.setFitWidth(image.getWidth() / 2);
        vorschau.setPreserveRatio(true);
        vorschau.setSmooth(true);

        pane.setContent(vorschau);
        pane.setStyle("-fx-padding: 20 20 20 20");
        pane.getContent().setStyle("-fx-alignment: Center");
        pane.setFitToWidth(true);

        okButton = new Button("Bestätigen");
        okButton.setOnAction(event -> PDFSpeichern());

        cancelButton = new Button("Abbrechen");
        cancelButton.setOnAction(event -> window.close());

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(okButton, cancelButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(50);

        VBox layout = new VBox();
        layout.getChildren().addAll(pane, buttonLayout);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefWidth(1200);
        layout.setPrefHeight(800);

        Scene scene = new Scene(layout);
        String css = this.getClass().getResource("../css/MainStyle.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.show();

    }

    public void PDFSpeichern()
    {
        try
        {
            Rechnung rechnung = new Rechnung();
            rechnung.rechnungErstellen(mainController.getRechnungErstellenController().getIntervallListe(), true);
            window.close();

//            mainController.getRechnungErstellenController().getStatusLabel().setStyle("-fx-text-fill: green");
//            mainController.getRechnungErstellenController().getStatusLabel().setText("Rechnung erstellt!");

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    public void pdfToImage()
    {
        String OUTPUT_DIR = "./";


        try (final PDDocument document = PDDocument.load(new File("rg_out.pdf")))
        {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page)
            {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String fileName = OUTPUT_DIR + "vorschau-" + page + ".png";
                ImageIOUtil.writeImage(bim, fileName, 300);
            }
        } catch (IOException e)
        {
            System.err.println("Exception while trying to create pdf document - " + e);
        }

    }



}
