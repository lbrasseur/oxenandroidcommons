package ar.com.oxen.android.common.bean.api;

/**
 * Interface used to abstract class manipulation.
 * 
 * @author lautaro.brasseur
 */
public interface ClassWrapper<T> {
	/**
	 * Gets the property descriptors.
	 * 
	 * @return An array with the descriptors of the properties
	 */
	PropertyDescriptor[] getPropertyDescriptors();

	/**
	 * Gets the property descriptor.
	 * 
	 * @param name
	 *            The property
	 * @return A property descriptor
	 */
	PropertyDescriptor getPropertyDescriptor(String name);

	/**
	 * Gets the short name (without package).
	 * 
	 * @return The short name
	 */
	String getShortClassName();
}
