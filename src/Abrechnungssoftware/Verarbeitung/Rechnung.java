package Abrechnungssoftware.Verarbeitung;
import java.io.*;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class Rechnung {
	//Variablen
	Auftrag auftrag;

	//Methoden
	//Als pdf exportieren
	public void exportToPdf() {
		//Daten aus Datenbank einlesen und in Variablen schreiben

		//html Template laden
		FileReader fr = null;
		//Variable zum Einlesen des Templates
		String text = "";

		//Template einlesen
		try
		{

			String fileName="rg_template.html";
			File file = new File(fileName) ;
			int len = (int)file.length() ;
			char[] buf = new char[len] ;

			fr = new FileReader(file);
			fr.read(buf) ;
			text = new String(buf);
			//System.out.println(text);
		}

/*catch(FileNotFoundException ex)
{
   System.out.println(ex);
}*/
		catch(IOException ex)
		{
			System.out.println(ex);
		}
		finally
		{
			try
			{
				if(fr!=null) fr.close();
			}
			catch(Exception ex)
			{
			}
		}
		//logo einbinden
//		File fileLogo = new File("kopf.png");
//		String pfadLogo = fileLogo.getAbsolutePath();
//		pfadLogo = pfadLogo.replaceAll("\\\\", "/");
//		pfadLogo = pfadLogo.substring(1);
//		pfadLogo = pfadLogo.replaceAll(":/","");
//		text = text.replaceAll("\\{LOGO\\}","<img src='file:///"+pfadLogo+"'></img>");

		text = text.replaceAll("\\{HEADLINE\\}","Dies ist die neue Überschrift!");


		//Export als pdf
		try {
			try (OutputStream os = new FileOutputStream("rg_out.pdf")) {
				PdfRendererBuilder builder = new PdfRendererBuilder();
				File file = new File("rg_temp.html");
				String pfad = file.getAbsolutePath();
				pfad = pfad.replaceAll("\\\\", "/");
				pfad = pfad.substring(1);
				pfad = pfad.replaceAll(":/","");
				builder.withHtmlContent(text,"");
				builder.toStream(os);
				builder.run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Rechnung erstellen
	public void rechnungErstellen() {

	}

}
