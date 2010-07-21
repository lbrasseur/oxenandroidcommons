package ar.com.oxen.android.common.bean.api;

/**
 * Exception to be thrown when an inexistent proprerty is intended to be
 * read/written.
 * 
 * @author lautaro.brasseur
 */
public class NoSuchPropertyException extends WrapperException {
	/**
	 * Serializable ID.
	 */
	private static final long serialVersionUID = 4463325859430905664L;

	/**
	 * @param property
	 *            The property
	 * @param rootCause
	 *            The cause
	 */
	public NoSuchPropertyException(final String property,
			final Throwable rootCause) {
		super("Property " + property + " doesn't exist", rootCause);
	}
}
