/**
 * 
 */
package br.com.currency;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.currency.dao.QuotationDAO;
import br.com.currency.exception.BusinessException;
import br.com.currency.util.DateUtil;

/**
 * @author Josmar Soares de Souza Junior
 *
 *Class to convert different currencies
 */
public class Quotation {

	private QuotationDAO quotationDAO = new QuotationDAO();
	
	/**
	 * @param from String with the currency name (example "USD") you want to convert
	 * @param to String with the currency name (example "EUR") you want to see the result
	 * @param value The value that should be converted. The currency of this value will be expressed in the “from” parameter
	 * @param quotation A date as String in the format “dd/MM/yyyy”
	 * @return Value rounded to two decimal places in the “from” currency parameter
	 */
	public BigDecimal currencyQuotation(String from, String to, Number value, String quotation){
		
		if(!quotationDAO.getCurrencies().contains(from) || 
				!quotationDAO.getCurrencies().contains(to)){
			throw new BusinessException("Invalid currency name.");
		}
		if(value.doubleValue() < 0){
			throw new BusinessException("Value smaller than zero.");
		}
		
		BigDecimal fromQuotation = quotationDAO.getQuotation(from, DateUtil.convertStringToCalendar(quotation));
		BigDecimal toQuotation = quotationDAO.getQuotation(to, DateUtil.convertStringToCalendar(quotation));
		
		
		return fromQuotation.multiply(new BigDecimal(value.toString())).divide(toQuotation, 2, RoundingMode.HALF_UP);
	}
}
