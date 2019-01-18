package Abrechnungssoftware.Verarbeitung;
import java.io.*;
import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.DB.Stammdaten;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class Rechnung {
	//Variablen
	Auftrag auftrag;
	DB_CON db = new DB_CON();
	Stammdaten std = new Stammdaten();
	//Methoden
	//Als pdf exportieren
	public void exportToPdf() {
		//Datenbank oeffnen
		db.db_open();
		db.LoadStammdaten(std);
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
		File fileLogo = new File("kopf.png");
		String pfadLogo = fileLogo.getAbsolutePath();
		pfadLogo = pfadLogo.replaceAll("\\\\", "/");
		pfadLogo = pfadLogo.substring(1);
		pfadLogo = pfadLogo.replaceAll(":/","");
		text = text.replaceAll("\\{LOGO\\}","file:///"+pfadLogo);

		//unterschrift einbinden
		File filesign = new File("unterschrift.png");
		String pfadsign = filesign.getAbsolutePath();
		pfadsign = pfadsign.replaceAll("\\\\", "/");
		pfadsign = pfadsign.substring(1);
		pfadsign = pfadsign.replaceAll(":/","");
		text = text.replaceAll("\\{SIGN\\}","file:///"+pfadsign);

		//Daten einsetzen
		String vname = std.getVorname();
		text = text.replaceAll("\\{VORNAME\\}",vname);
		String nname = std.getNachname();
		text = text.replaceAll("\\{NACHNAME\\}",nname);
		String str = std.getStr();
		text = text.replaceAll("\\{STRASSE\\}",str);
		String hsnr = std.getHsnr();
		text = text.replaceAll("\\{HNR\\}",hsnr);
		String plz = std.getPlz();
		text = text.replaceAll("\\{PLZ\\}",plz);
		String ort = std.getOrt();
		text = text.replaceAll("\\{ORT\\}",ort);
		String stnr = std.getSteuernummer();
		text = text.replaceAll("\\{STNR\\}",stnr);

		//Umlaute ersetzen
		String A = "Ä";
		text = text.replaceAll("\\{Ae\\}",A);
		String a = "ä";
		text = text.replaceAll("\\{ae\\}",a);
		String O = "Ö";
		text = text.replaceAll("\\{Oe\\}",O);
		String o = "ö";
		text = text.replaceAll("\\{oe\\}",o);
		String U = "Ü";
		text = text.replaceAll("\\{Ue\\}",U);
		String u = "ü";
		text = text.replaceAll("\\{ue\\}",u);
		String ß = "ß";
		text = text.replaceAll("\\{sz\\}",ß);

		//Sonderzeichen ersetzen
		String euro = "€";
		text = text.replaceAll("\\{EURO\\}",euro);
		String paragraph = "§";
		text = text.replaceAll("\\{PARAGRAPH\\}",paragraph);
//		String rgdatum = std;
//		text = text.replaceAll("\\{RGDATUM\\}",rgdatum);
//		String rgnr = std.;
//		text = text.replaceAll("\\{RGNR\\}",rgnr);


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
		db.db_open();
		db.LoadStammdaten(std);
	}

}
