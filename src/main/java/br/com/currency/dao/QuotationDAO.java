/**
 * 
 */
package br.com.currency.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import br.com.currency.exception.BusinessException;
import br.com.currency.util.DateUtil;

/**
 * @author Josmar Soares de Souza Junior
 * 
 *
 */
public class QuotationDAO {
	
	private final String DATA_SOURCE_PATH = "src/main/java/br/com/currency/resources/dataSource.csv";
	private final String CSV_SEPARATOR = ";";
	
	private File dataSource;
	private Set<String> currencies;
	
	public QuotationDAO(){
		dataSource = new File(DATA_SOURCE_PATH);
		loadCurrencies();
	}
	
	public QuotationDAO(String customDataSourcePath){
		dataSource = new File(customDataSourcePath);
		loadCurrencies();
	}
	
	
	public QuotationDAO(File customDataSource){
		dataSource = customDataSource;
		loadCurrencies();
	}

	public BigDecimal getQuotation(String currency, Calendar quotationDate){
		try {
			Scanner scanner = new Scanner(dataSource);
			while(scanner.hasNextLine()){
				String[] line = scanner.nextLine().split(CSV_SEPARATOR);
				if(line[3].equalsIgnoreCase(currency) && quotationDate.equals(DateUtil.convertStringToCalendar(line[0]))){
					scanner.close();
					return new BigDecimal(line[4].replace(",", "."));
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			throw new BusinessException("DataSource not found.", e);
		}
		throw new BusinessException("Quotation not available.");
	}
	
	private void loadCurrencies(){
		this.currencies = new HashSet<String>();
		try {
			Scanner scanner = new Scanner(dataSource);
			while(scanner.hasNextLine()){
				this.currencies.add(scanner.nextLine().split(CSV_SEPARATOR)[3]);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			throw new BusinessException("DataSource not found.", e);
		}
	}

	/**
	 * @return the currencies
	 */
	public Set<String> getCurrencies() {
		return currencies;
	}
	
}
