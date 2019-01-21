package Abrechnungssoftware.Verarbeitung;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class main {




	public static void main(String[] args){

//		Auftrag auftr = new Auftrag();
//		int tage = auftr.getArbeitstage("01.02.2019","01.03.2019");
//        System.out.println(tage);

//		int tage = auftr.getArbeitstage("01.01.2019", "31.01.2019");
//		System.out.println(tage);
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
	    Rechnung rg = new Rechnung();
		rg.rechnungErstellen(test,false);

	}

}

