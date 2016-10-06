/**
 * 
 */
package br.com.currency.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.currency.exception.BusinessException;

/**
 * @author Josmar Soares de Souza Junior
 *
 */
public class DateUtil {

	
	public static Calendar convertStringToCalendar(String date){
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(date));
			return calendar;
		} catch (ParseException e) {
			throw new BusinessException("Invalid date.", e);
		}
	}
	
	public static Boolean isWeekend(final Calendar date){
		return date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public static Calendar findPrecedingBusinessDay(final Calendar date){
		Calendar precedingBusinessDay = Calendar.getInstance();
		precedingBusinessDay.setTime(date.getTime());
		while(isWeekend(precedingBusinessDay)){
			precedingBusinessDay.add(Calendar.DAY_OF_MONTH, -1);
		}
		return precedingBusinessDay;
	}
}
