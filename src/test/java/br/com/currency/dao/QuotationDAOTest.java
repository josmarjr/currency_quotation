/**
 * 
 */
package br.com.currency.dao;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.com.currency.exception.BusinessException;
import br.com.currency.util.DateUtil;

/**
 * @author Josmar Soares de Souza Junior
 *
 */
public class QuotationDAOTest {
	
	private QuotationDAO quotationDAO;
	
	
	@Before
	public void init() throws IOException{
		File file = File.createTempFile("temp", ".csv");
		
		file.deleteOnExit();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("05/10/2016;540;B;GBP;4,12210000;4,12450000;1,27410000;1,27460000\n");
   	    bw.write("05/10/2016;220;A;USD;3,23530000;3,23590000;1,00000000;1,00000000\n");
   	    bw.write("05/10/2016;978;B;EUR;3,62160000;3,62260000;1,11940000;1,11950000\n");
   	    bw.write("05/10/2016;950;A;KES;0,03192000;0,03199000;101,15000000;101,35000000\n");
   	    bw.write("05/10/2016;998;A;XAU;131,73050000;131,75490000;0,02456000;0,02456000\n");
   	    bw.write("06/10/2016;998;A;XAU;151,73050000;131,75490000;0,02456000;0,02456000\n");
   	    bw.close();
   	    
   	 quotationDAO = new QuotationDAO(file);
	}

	
	@Test
	public void getCurrenciesTest(){
			
		Set<String> currencies = quotationDAO.getCurrencies();
		
		assertEquals(5, currencies.size());
	}
	
	@Test
	public void getQuotationTestSuccess(){
		
		BigDecimal quotation = quotationDAO.getQuotation("GBP", DateUtil.convertStringToCalendar("05/10/2016"));
		
		assertEquals(new BigDecimal("4.12210000"), quotation);
		
	}
	
	@Test(expected=BusinessException.class)
	public void getQuotationTestWithDateNotAvailable(){
		
		BigDecimal quotation = quotationDAO.getQuotation("GBP", DateUtil.convertStringToCalendar("06/10/2016"));
		
		assertEquals(new BigDecimal("4.12210000"), quotation);
		
	}
}
