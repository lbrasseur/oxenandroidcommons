/**
 * 
 */
package ar.com.oxen.android.common.bean.api;

/**
 * Busines related Exception
 * 
 * @author mana
 */
public class BusinessException extends RuntimeException {

	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = 7099184727150395093L;
	
	/**
	 * Key identifying the Exception 
	 */
	private String key;
	
	/**
	 * Format vals to use in the exception message
	 */
	Object[] formatVals;
	
	public BusinessException(final String aKey) {
		super();
		this.key = aKey;
	}

	public BusinessException(final String aKey, Object... aFormatVals) {
		this(aKey);
		this.formatVals = aFormatVals;
	}
	
	public BusinessException(final String aKey, final Throwable cause) {
		super(cause);
		this.key = aKey;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public Object[] getFormatVals() {
		return this.formatVals;
	}
	
}
