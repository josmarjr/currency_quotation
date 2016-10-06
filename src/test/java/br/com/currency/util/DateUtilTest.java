package br.com.currency.util;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import br.com.currency.exception.BusinessException;
import br.com.currency.util.DateUtil;

public class DateUtilTest {

	@Test
	public void convertStringToCalendarWithSuccess(){
		String date = "01/02/2012";
		Calendar calendar = DateUtil.convertStringToCalendar(date);
		
		assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(Calendar.FEBRUARY, calendar.get(Calendar.MONTH));
		assertEquals(2012, calendar.get(Calendar.YEAR));
	}
	
	@Test(expected=BusinessException.class)
	public void convertStringToCalendarWithException(){
		String date = "0102/2012";
		DateUtil.convertStringToCalendar(date);
	}
	
	@Test
	public void isWeekendTest(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.MONTH, Calendar.OCTOBER);
		calendar.set(Calendar.DAY_OF_MONTH, 7);
		
		assertFalse(DateUtil.isWeekend(calendar));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		assertTrue(DateUtil.isWeekend(calendar));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		assertTrue(DateUtil.isWeekend(calendar));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		assertFalse(DateUtil.isWeekend(calendar));
	}
	
	@Test
	public void findPrecedingBusinessDayTest(){
		Calendar friday = Calendar.getInstance();
		friday.set(Calendar.YEAR, 2016);
		friday.set(Calendar.MONTH, Calendar.OCTOBER);
		friday.set(Calendar.DAY_OF_MONTH, 7);
		
		Calendar saturday = Calendar.getInstance();
		saturday.set(Calendar.YEAR, 2016);
		saturday.set(Calendar.MONTH, Calendar.OCTOBER);
		saturday.set(Calendar.DAY_OF_MONTH, 8);
		
		Calendar sunday = Calendar.getInstance();
		sunday.set(Calendar.YEAR, 2016);
		sunday.set(Calendar.MONTH, Calendar.OCTOBER);
		sunday.set(Calendar.DAY_OF_MONTH, 9);
		
		assertTrue(friday.equals(DateUtil.findPrecedingBusinessDay(saturday)));
		assertTrue(friday.equals(DateUtil.findPrecedingBusinessDay(sunday)));
	}
	
}
