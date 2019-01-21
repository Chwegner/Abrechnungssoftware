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
    MainController mainController;

    ImageView vorschau;

    public void injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void Vorschau()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("PDF Vorschau");
        window.setMaximized(true);

        ScrollPane pane = new ScrollPane();

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
        vorschau.setFitHeight(image.getHeight()/2);
        vorschau.setFitWidth(image.getWidth()/2);
        vorschau.setPreserveRatio(true);
        vorschau.setSmooth(true);
        CenterImage();

        pane.setContent(vorschau);
        pane.setPrefViewportHeight(vorschau.getFitHeight());
        pane.setPrefViewportWidth(vorschau.getFitWidth());
        pane.setStyle("-fx-padding: 20 20 20 20");
        pane.getContent().setStyle("-fx-alignment: Center");

        Button okButton = new Button("Bestätigen");

        Button cancelButton = new Button("Abbrechen");

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(okButton, cancelButton);
        buttonLayout.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(pane, buttonLayout);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(100);


        Scene scene = new Scene(layout);
        String css = this.getClass().getResource("../css/MainStyle.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.showAndWait();

    }

    public void pdfToImage()
    {
        String OUTPUT_DIR = "./";

        try (final PDDocument document = PDDocument.load(new File("rg_out.pdf"))){
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page)
            {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String fileName = OUTPUT_DIR + "vorschau-" + page + ".png";
                ImageIOUtil.writeImage(bim, fileName, 300);
            }
        } catch (IOException e){
            System.err.println("Exception while trying to create pdf document - " + e);
        }

    }
    public void CenterImage() {
        Image img = vorschau.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = vorschau.getFitWidth() / img.getWidth();
            double ratioY = vorschau.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            vorschau.setX((vorschau.getFitWidth() - w) / 2);
            vorschau.setY((vorschau.getFitHeight() - h) / 2);

        }
    }
}
