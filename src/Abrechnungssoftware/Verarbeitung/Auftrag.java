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

    public String[][] getAbrechnungsIntervall(String von2, String bis2) {
        String[][] intervall = null;
        String intervallStart, intervallEnde;
        Date anfangInt = null;
        Date endeInt = null;
        String dStr, mStr, deStr, meStr;

        // Datumsformat festlegen
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        // Eingaben parsen
        try {
            anfangInt = df.parse(von2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            endeInt = df.parse(bis2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Calender-Objekt erstellen
        Calendar calAnfangInt = new GregorianCalendar();
        calAnfangInt.setTimeZone(TimeZone.getTimeZone("CET"));
        calAnfangInt.setFirstDayOfWeek(1);
        calAnfangInt.setTime(anfangInt);

        //Calendar-Objekt zur Berechnung der Monate erstellen
        Calendar calBegin = new GregorianCalendar();
        calBegin.setTimeZone(TimeZone.getTimeZone("CET"));
        calBegin.setTime(anfangInt);

        //Testausgabe
        System.out.println("calBegin: "+calBegin.getTime());

        Calendar calEndeInt = new GregorianCalendar();
        calEndeInt.setTimeZone(TimeZone.getTimeZone("CET"));
        calEndeInt.setTime(endeInt);

        //Testausgabe
        System.out.println("calEndeInt: "+calEndeInt.getTime());
        System.out.println(calBegin.getTimeInMillis() < calEndeInt.getTimeInMillis());

/*
        //Berechnung fuer die Anzahl der Monate
        int monate = 0;
        int msec = 0;
        while (calBegin.getTimeInMillis() <= calEndeInt.getTimeInMillis()){
            monate++;
            int mi = (calBegin.get(Calendar.MONTH)+1);
            int yi = calBegin.get(Calendar.YEAR);
            switch (mi){
                case 1:
                    msec = 1000*60*60*24*31;
                case 2:
                    if (((GregorianCalendar) calBegin).isLeapYear(yi)){
                        msec = 1000*60*60*24*29;
                    }else{
                        msec = 1000*60*60*24*28;
                    }
                case 3:
                    msec = 1000*60*60*24*31;
                case 4:
                    msec = 1000*60*60*24*30;
                case 5:
                    msec = 1000*60*60*24*31;
                case 6:
                    msec = 1000*60*60*24*30;
                case 7:
                    msec = 1000*60*60*24*31;
                case 8:
                    msec = 1000*60*60*24*31;
                case 9:
                    msec = 1000*60*60*24*30;
                case 10:
                    msec = 1000*60*60*24*31;
                case 11:
                    msec = 1000*60*60*24*30;
                case 12:
                    msec = 1000*60*60*24*31;
            }
            calBegin.setTimeInMillis(msec);
            //Testausgabe
            System.out.println(calBegin.getTime());
        }
*/
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
                //Formatierung fuer d aendern

                if (d < 10) {
                    dStr = "0" + d;
                } else {
                    dStr = Integer.toString(d);
                }
                //Formatierung fuer m aendern
                if (m < 10) {
                    mStr = "0" + m;
                } else {
                    mStr = Integer.toString(m);
                }

                intervallStart = dStr + "." + mStr + "." + y;

                //Enddatum des Intervalls
                calAnfangInt.add(Calendar.MONTH, 1);
                calAnfangInt.add(Calendar.DAY_OF_MONTH, -1);
                //Der gesamte Zeiraum ist laenger als 1 Monat
                if (calAnfangInt.getTimeInMillis() < calEndeInt.getTimeInMillis()) {
                    int de = calAnfangInt.get(Calendar.DAY_OF_MONTH);
                    int me = calAnfangInt.get(Calendar.MONTH);
                    int ye = calAnfangInt.get(Calendar.YEAR);
                    //Formatierung fuer d aendern
                    if (de < 10) {
                        deStr = "0" + Integer.toString(de);
                    } else {
                        deStr = Integer.toString(de);
                    }
                    //Formatierung fuer m aendern
                    if (me < 10) {
                        meStr = "0" + Integer.toString(me);
                    } else {
                        meStr = Integer.toString(me);
                    }

                    intervallEnde = deStr + "." + meStr + "." + y;

                } else {
                    //Der geamte Zeitraum ist nicht laenger als 1 Monat
                    int de = calEndeInt.get(Calendar.DAY_OF_MONTH);
                    int me = calEndeInt.get(Calendar.MONTH);
                    int ye = calEndeInt.get(Calendar.YEAR);
                    //Formatierung fuer d aendern
                    if (de < 10) {
                        deStr = "0" + Integer.toString(de);
                    } else {
                        deStr = Integer.toString(de);
                    }
                    //Formatierung fuer m aendern
                    if (me < 10) {
                        meStr = "0" + Integer.toString(me);
                    } else {
                        meStr = Integer.toString(me);
                    }

                    intervallEnde = deStr + "." + meStr + "." + y;
                }
                //Anfangsdatum und Enddatum in array schreiben
                intervall[i][i] = intervallStart;
                intervall[i][i + 1] = intervallEnde;
                //Laufvariable hochzaehlen
                i++;
                //Monat hochsetzen
                calAnfangInt.add(Calendar.MONTH, 1);
            }
            return intervall;
        }
    }
}
