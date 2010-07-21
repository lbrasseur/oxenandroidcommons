package ar.com.oxen.android.common.bean.impl.simple;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import ar.com.oxen.android.common.bean.api.ClassWrapper;
import ar.com.oxen.android.common.bean.api.NoSuchPropertyException;
import ar.com.oxen.android.common.bean.api.PropertyDescriptor;
import ar.com.oxen.android.common.bean.api.WrapperException;

/**
 * Simple implementation of BeanWrapper
 * 
 * @author lbrasseur
 * @param <T>
 * 
 */
public class SimpleClassWrapper<T> implements ClassWrapper<T> {
	/**
	 * The class to be wraped.
	 */
	private Class<T> clazz;

	/**
	 * Constructor.
	 * 
	 * @param bean
	 *            The bean to be wrapped
	 */
	public SimpleClassWrapper(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		List<PropertyDescriptor> descriptors = new LinkedList<PropertyDescriptor>();

		for (Method method : clazz.getMethods()) {
			String methodName = method.getName();
			if (methodName.startsWith("get") && !methodName.equals("getClass")) {
				descriptors.add(new PropertyDescriptor(methodName.substring(3,
						4).toLowerCase()
						+ methodName.substring(4), method.getReturnType()));

			}
		}

		return descriptors.toArray(new PropertyDescriptor[descriptors.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropertyDescriptor getPropertyDescriptor(String name) {
		return new PropertyDescriptor(name, getMethod("get" + capitalize(name))
				.getReturnType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getShortClassName() {
		String fullName = clazz.getName();
		return fullName.substring(fullName.lastIndexOf('.') + 1);
	}

	/**
	 * Looks for a method on the bean
	 * 
	 * @param name
	 *            The name of the method
	 * @param args
	 *            The arguments
	 * @return The Method object
	 */
	protected Method getMethod(String name, Class<?>... args) {
		try {
			return clazz.getMethod(name, args);
		} catch (NoSuchMethodException e) {
			throw new NoSuchPropertyException(name, e);
		} catch (SecurityException e) {
			throw new WrapperException(e);
		}
	}

	/**
	 * Capitalizes an String
	 * 
	 * @param text
	 *            The string to be capitalized
	 * @return The capitalized string
	 */
	protected String capitalize(String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}
}
