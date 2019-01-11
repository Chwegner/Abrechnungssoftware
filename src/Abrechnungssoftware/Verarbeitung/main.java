package Verarbeitung;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class main {
	
	

	
	public static void main(String[] args){
		
		Auftrag auftr = new Auftrag();
		
		int tage = auftr.getArbeitstage("01.01.2019", "31.01.2019");
		System.out.println(tage);
		
	}

}

