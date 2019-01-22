package Abrechnungssoftware.Verarbeitung;
import java.io.*;
import java.util.ArrayList;

import Abrechnungssoftware.DB.DB_CON;
import Abrechnungssoftware.DB.Stammdaten;
import Abrechnungssoftware.DB.helperClass2;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class Rechnung {
	//Variablen
	Auftrag auftrag;
    ArrayList<Integer> daten;
	DB_CON db = new DB_CON();
	Stammdaten std = new Stammdaten();
	//Methoden
	//Als pdf exportieren
	public void exportToPdf(ArrayList<Integer> daten) {
		//Auftrag einbinden
		Auftrag auftr = new Auftrag();
        this.daten = daten;
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

		int i = 1;
		double ges = 0;
		String pos ="";
		//Rechnungsdaten laden
		int j = 0;
		int k = 0;
		helperClass2 help1 = new helperClass2();
		for(Integer element : daten) {
			int temp = daten.get(j);
			help1 = db.loadHelper(temp);
			String positionen =
					"<tr>"+
					"<td>"+i+"</td>"+
					"<td>"+help1.getBezeichnung()+"</td>"+
					"<td>"+((help1.getTage()-help1.getKorrekturtage())*8)+"</td>"+
					"<td>"+help1.getStundenSatz()+"</td>"+
					"<td>"+(((help1.getTage()-help1.getKorrekturtage())*8)*help1.getStundenSatz())+"</td>"+
					"</tr>";
					pos += positionen;
					ges += (((help1.getTage()-help1.getKorrekturtage())*8)*help1.getStundenSatz());
					j++ ;
					i++ ;
		}
		helperClass2 help = db.loadHelper(daten.get(0));

		//pos zu text hinzufuegen
		text = text.replaceAll("\\{RGPOSITIONEN\\}",pos);
		String summe = ges+" €";
		text = text.replaceAll("\\{SUMMENETTO\\}",summe);
		text = text.replaceAll("\\{RECHNUNGSBETRAG\\}",summe);

		//Kontodaten
		text = text.replaceAll("\\{KONTOINHABER\\}",std.getKontoinhaber());
		text = text.replaceAll("\\{BANKNAME\\}",std.getBankname());
		text = text.replaceAll("\\{IBAN\\}",std.getIban());
		text = text.replaceAll("\\{BIC\\}",std.getBic());

		//Kundendaten
		text = text.replaceAll("\\{KDFIRMA\\}",help.getFirma());
		text = text.replaceAll("\\{KDSTRASSE\\}",help.getStrasse());
		text = text.replaceAll("\\{KDHSN\\}",help.getHausnummer());
		text = text.replaceAll("\\{KDPLZ\\}",help.getPlz());
		text = text.replaceAll("\\{KDORT\\}",help.getOrt());

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
	public void rechnungErstellen(ArrayList<Integer> daten, boolean erstellen) {
		db.db_open();
		db.LoadStammdaten(std);

		if(erstellen){
			exportToPdf(daten);
			int j = 0;
			for(int element : daten) {
				int temp = daten.get(j);
				db.NewRechnung(temp);
				j++;
			}
		}else{
			exportToPdf(daten);
		}
	}
}


