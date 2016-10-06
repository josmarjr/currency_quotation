package br.com.currency;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.currency.exception.BusinessException;

public class QuotationTest {
	Quotation quotation = new Quotation();
	
	

	@Test
	public void currencyQuotationTest() {
		assertEquals(new BigDecimal("89.33"), quotation.currencyQuotation("USD", "EUR", 100, "05/10/2016"));
	}
	
	@Test(expected=BusinessException.class)
	public void currencyQuotationTestWithNegativeValue(){
		quotation.currencyQuotation("USD", "EUR", -100, "05/10/2016");
	}
	
	@Test(expected=BusinessException.class)
	public void currencyQuotationTestWithInvalidFromCurrency(){
		quotation.currencyQuotation("USA", "EUR", 100, "05/10/2016");
	}

	
	@Test(expected=BusinessException.class)
	public void currencyQuotationTestWithInvalidToCurrency(){
		quotation.currencyQuotation("USD", "EUA", 100, "05/10/2016");
	}
}
