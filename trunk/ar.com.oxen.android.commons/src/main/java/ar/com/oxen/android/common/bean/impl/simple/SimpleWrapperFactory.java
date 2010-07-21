package ar.com.oxen.android.common.bean.impl.simple;

import ar.com.oxen.android.common.bean.api.BeanWrapper;
import ar.com.oxen.android.common.bean.api.ClassWrapper;
import ar.com.oxen.android.common.bean.api.WrapperException;
import ar.com.oxen.android.common.bean.api.WrapperFactory;

/**
 * Simple implementation of BeanWrapperFactory
 * 
 * @author lbrasseur
 * 
 */
public class SimpleWrapperFactory implements WrapperFactory {
	/**
	 * {@inheritDoc}
	 * 
	 * @param <T>
	 */
	@Override
	public <T> ClassWrapper<T> wrapClass(Class<T> clazz) {
		return new SimpleClassWrapper<T>(clazz);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param <T>
	 */
	@Override
	public <T> BeanWrapper<T> wrapBean(T bean) {
		return new SimpleBeanWrapper<T>(bean);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> BeanWrapper<T> wrapNewBean(Class<T> clazz) {
		try {
			return this.wrapBean(clazz.newInstance());
		} catch (InstantiationException e) {
			throw new WrapperException(e);
		} catch (IllegalAccessException e) {
			throw new WrapperException(e);
		}
	}
}
