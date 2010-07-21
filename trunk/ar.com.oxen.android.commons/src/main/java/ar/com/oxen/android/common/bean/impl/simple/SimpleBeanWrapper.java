package ar.com.oxen.android.common.bean.impl.simple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ar.com.oxen.android.common.bean.api.BeanWrapper;
import ar.com.oxen.android.common.bean.api.PropertyDescriptor;
import ar.com.oxen.android.common.bean.api.WrapperException;

/**
 * Simple implementation of BeanWrapper
 * 
 * @author lbrasseur
 * @param <T>
 * 
 */
public class SimpleBeanWrapper<T> extends SimpleClassWrapper<T> implements
		BeanWrapper<T> {
	/**
	 * The wraped bean.
	 */
	private T bean;

	/**
	 * Constructor.
	 * 
	 * @param bean
	 *            The bean to be wrapped
	 */
	@SuppressWarnings("unchecked")
	public SimpleBeanWrapper(T bean) {
		super((Class<T>) bean.getClass());
		this.bean = bean;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getProperty(String name) {
		try {
			Method method = getMethod("get" + capitalize(name));
			return method.invoke(bean);
		} catch (InvocationTargetException e) {
			throw new WrapperException(e);
		} catch (IllegalAccessException e) {
			throw new WrapperException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setProperty(String name, Object value) {
		try {
			final PropertyDescriptor propDescriptor = 
				getPropertyDescriptor(name);
			final Method method = getMethod("set" + capitalize(name), 
					propDescriptor.getType());
			method.invoke(bean, value);
		} catch (InvocationTargetException e) {
			throw new WrapperException(e);
		} catch (IllegalAccessException e) {
			throw new WrapperException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getBean() {
		return bean;
	}
}
