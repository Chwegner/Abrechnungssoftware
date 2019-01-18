package Abrechnungssoftware;

import Abrechnungssoftware.Gui.MainController;
import javafx.application.Application;
import Abrechnungssoftware.DB.DB_CON;


import javax.swing.*;
import java.io.*;
import java.util.Properties;

public class Main
{

    public static void main(String[] args) throws IOException
    {
        File file = new File("system.ini");
        boolean exists = file.exists();
        boolean install = false;
        if (file.exists() && file.isFile())
        {

        } else
        {
            String host, benutzer, password, datenbank;
            host = JOptionPane.showInputDialog("Datenbankserveradresse ?");
            benutzer = JOptionPane.showInputDialog("Benutzername ?");
            password = JOptionPane.showInputDialog("Passwort ?");
            datenbank = JOptionPane.showInputDialog("Datenbank ?");

            Writer writer = null;
            try
            {
                writer = new FileWriter("system.ini");
                Properties prop1 = new Properties(System.getProperties());
                prop1.setProperty("HOST", host);
                prop1.setProperty("USER", benutzer);
                prop1.setProperty("PWD", password);
                prop1.setProperty("DATENBANK", datenbank);
                prop1.store(writer, "Keine manuellen Aenderungen vornehmen!");
            } catch (Exception e)
            {

            } finally
            {
                try
                {
                    writer.close();
                } catch (Exception e)
                {

                }

            }
            DB_CON inst_db = new DB_CON();
            inst_db.db_open();
            inst_db.db_install();
            inst_db.db_close();
        }

        Application.launch(MainController.class, args);


//        try {
//
//            try (OutputStream os = new FileOutputStream("out.pdf")) {
//
//                PdfRendererBuilder builder = new PdfRendererBuilder();
//
//                File file = new File("newhtml.html");
//
//                String pfad = file.getAbsolutePath();
//
//                pfad = pfad.replaceAll("\\\\", "/");
//
//                pfad = pfad.substring(1);
//
//                pfad = pfad.replaceAll(":/","");
//
//                builder.withUri("file:///"+pfad);
//
//                builder.toStream(os);
//
//                builder.run();
//
//            }
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//    public static void main(String[] args) throws IOException {
//        Rechnung rg = new Rechnung();
//        rg.exportToPdf();
//    }


    }
}

