package Abrechnungssoftware.Verarbeitung;

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

	public String[][] getAbrechnungsIntervall(String von2, String bis2){
		String[][] intervall = null;
		Date anfangInt = null;
		Date endeInt = null;
		// Eingaben parsen
		try {
			anfangInt = df.parse(von2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			endeInt = df.parse(bis2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Calender-Objekt erstellen
		Calendar calAnfangInt = new GregorianCalendar();
		calAnfangInt.setTimeZone(TimeZone.getTimeZone("CET"));
		calAnfangInt.setFirstDayOfWeek(1);
		calAnfangInt.setTime(anfangInt);

		Calendar calEndeInt = new GregorianCalendar();
		calEndeInt.setTimeZone(TimeZone.getTimeZone("CET"));
		calEndeInt.setTime(endeInt);

		// Berechnung der Intervalle
		if (calEndeInt.getTimeInMillis() <= calAnfangInt.getTimeInMillis()) {
			throw new IllegalArgumentException();
		} else {
			//Laufvariable
			int i = 0;
			while (calEndeInt.getTimeInMillis() >= calAnfangInt.getTimeInMillis()) {
				//Startdatum des Intervalls
				int d = calAnfangInt.get(Calendar.DAY_OF_MONTH);
				int m = calAnfangInt.get(Calendar.MONTH);
				int y = calAnfangInt.get(Calendar.YEAR);
				String intervallStart = d+"."+m+"."+y;
				//Enddatum des Intervalls
				calAnfangInt.add(Calendar.MONTH,1);
				calAnfangInt.add(Calendar.DAY_OF_MONTH, -1);
				if (calAnfangInt.getTimeInMillis() < calEndeInt.getTimeInMillis()) {
					int de = calAnfangInt.get(Calendar.DAY_OF_MONTH);
					int me = calAnfangInt.get(Calendar.MONTH);
					int ye = calAnfangInt.get(Calendar.YEAR);

				}
				calAnfang.add(Calendar.DAY_OF_WEEK, 1);
			}

			return intervall;
		}
	}
