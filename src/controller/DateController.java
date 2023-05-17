package controller;

import java.time.LocalDate;
import java.time.Month;

import javax.swing.JOptionPane;

public class DateController {
	
    private static DateController instance;
	
	private DateController() {
		
	}
	
	public static DateController getInstance() {
        if (instance == null) {
            instance = new DateController();
        }
        return instance;
    }
	
	public LocalDate getLocalDate(int[] intDate) {
		LocalDate date = null;
		
		switch(intDate[1]) {
			case 1:  date = LocalDate.of(intDate[0], Month.JANUARY, intDate[2]);
            		 break;
	        case 2:  date = LocalDate.of(intDate[0], Month.FEBRUARY, intDate[2]);
	                 break;
	        case 3:  date = LocalDate.of(intDate[0], Month.MARCH, intDate[2]);
	                 break;
	        case 4:  date = LocalDate.of(intDate[0], Month.APRIL, intDate[2]);
	                 break;
	        case 5:  date = LocalDate.of(intDate[0], Month.MAY, intDate[2]);
	                 break;
	        case 6:  date = LocalDate.of(intDate[0], Month.JUNE, intDate[2]);
	                 break;
	        case 7:  date = LocalDate.of(intDate[0], Month.JULY, intDate[2]);
	                 break;
	        case 8:  date = LocalDate.of(intDate[0], Month.AUGUST, intDate[2]);
	                 break;
	        case 9:  date = LocalDate.of(intDate[0], Month.SEPTEMBER, intDate[2]);
	                 break;
	        case 10: date = LocalDate.of(intDate[0], Month.OCTOBER, intDate[2]);
	                 break;
	        case 11: date = LocalDate.of(intDate[0], Month.NOVEMBER, intDate[2]);
	                 break;
	        case 12: date = LocalDate.of(intDate[0], Month.DECEMBER, intDate[2]);
	                 break;
	        default: //Do nothing
	                 break;
		}
		
		return date;
	}
}
