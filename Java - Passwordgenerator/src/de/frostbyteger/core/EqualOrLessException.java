/**
 * 
 */
package de.frostbyteger.core;

/**
 * @author Kevin
 * @version 1.00
 */
public class EqualOrLessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8826997846955110048L;

	/**
	 * 
	 */
	public EqualOrLessException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public EqualOrLessException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public EqualOrLessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EqualOrLessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public EqualOrLessException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
