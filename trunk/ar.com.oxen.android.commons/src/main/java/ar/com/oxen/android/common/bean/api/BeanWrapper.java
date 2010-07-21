package ar.com.oxen.android.common.bean.api;

/**
 * Interface used to abstract bean manipulation.
 * 
 * @author lautaro.brasseur
 */
public interface BeanWrapper<T> extends ClassWrapper<T> {
	/**
	 * Gets a property from a bean.
	 * 
	 * @param name
	 *            The property
	 * @return The property value
	 */
	Object getProperty(String name);

	/**
	 * Sets a property to a bean.
	 * 
	 * @param name
	 *            The property
	 * @param value
	 *            The property value
	 */
	void setProperty(String name, Object value);

	/**
	 * Gets the wrapped object.
	 * @return The wrapped object
	 */
	T getBean();
}
