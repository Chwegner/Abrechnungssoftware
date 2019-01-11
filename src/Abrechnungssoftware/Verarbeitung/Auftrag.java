package Verarbeitung;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Auftrag {
	String bezeichnung, von, bis;
	int arbeitstage, korrekturtage;
	Kunde kunde;
//	Stammdaten stammdaten;

	// Methoden
	// getter / setter

	// generieren von Auftraegen
	public void auftragErstellen() {
		//TODO

	}

	// generieren von Teilauftraegen
	public void teilAuftrag(Auftrag auftrag, String von, String bis) {
		//TODO
	}

	public int getArbeitstage(String von, String bis) {

		// Lokale Variable zur Ausgabe der Arbeitstage
		int arbeitstage = 0;
		Date anfang = null;
		Date ende = null;

		// Datumsformat festlegen
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

		// Eingaben parsen
		try {
			anfang = df.parse(von);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ende = df.parse(bis);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Calender-Objekt erstellen
		Calendar calAnfang = new GregorianCalendar();
		calAnfang.setTimeZone(TimeZone.getTimeZone("CET"));
		calAnfang.setFirstDayOfWeek(1);
		calAnfang.setTime(anfang);

		Calendar calEnde = new GregorianCalendar();
		calEnde.setTimeZone(TimeZone.getTimeZone("CET"));
		calEnde.setTime(ende);

		// Berechnung der Arbeitstage
		if (calEnde.getTimeInMillis() <= calAnfang.getTimeInMillis()) {
			throw new IllegalArgumentException();
		} else {
			while (calEnde.getTimeInMillis() >= calAnfang.getTimeInMillis()) {
				int dow = calAnfang.get(Calendar.DAY_OF_WEEK);
				if ((dow != 7) && (dow != 6)) {
					arbeitstage = arbeitstage + 1;
				}
				calAnfang.add(Calendar.DAY_OF_WEEK, 1);
			}
		}

		return arbeitstage;
	}

}
