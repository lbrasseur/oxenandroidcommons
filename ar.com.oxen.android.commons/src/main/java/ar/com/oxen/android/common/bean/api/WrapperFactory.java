package ar.com.oxen.android.common.bean.api;

/**
 * Interface used to abstract bean wrapping.
 * 
 * @author lautaro.brasseur
 */
public interface WrapperFactory {
	/**
	 * Wraps a class
	 * @param <T>
	 * 
	 * @param clazz
	 *            The bean to be wrapped
	 * @return The ClassWrapper which wraps the class
	 */
	<T> ClassWrapper<T> wrapClass(Class<T> clazz);

	/**
	 * Wraps a bean
	 * @param <T>
	 * 
	 * @param bean
	 *            The bean to be wrapped
	 * @return The BeanWrapper which wraps the bean
	 */
	<T> BeanWrapper<T> wrapBean(T bean);

	/**
	 * Instantiates and wraps a bean
	 * @param <T>
	 * 
	 * @param bean
	 *            The bean to be wrapped
	 * @return The BeanWrapper which wraps the bean
	 */
	<T> BeanWrapper<T> wrapNewBean(Class<T> clazz);
}
