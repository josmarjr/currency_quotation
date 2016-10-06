/**
 * 
 */
package br.com.currency.exception;

/**
 * @author Josmar Soares de Souza Junior
 *
 */
public class BusinessException extends RuntimeException{

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1810574432098050471L;
	
	public BusinessException(){
		super();
	}
	
	public BusinessException(String arg0){
		super(arg0);
	}
	
	public BusinessException(Throwable arg0){
		super(arg0);
	}

	public BusinessException(String arg0, Throwable arg1){
		super(arg0, arg1);
	}
}
