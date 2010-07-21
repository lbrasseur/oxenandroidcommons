package ar.com.oxen.android.common.bean.api;

/**
 * Class used to describe a property. Replaces java.beans.PropertyDescriptor
 * since it is not available in Android.
 * 
 * @author lbrasseur
 * 
 */
public class PropertyDescriptor {
	private String name;
	private Class<?> type;

	public PropertyDescriptor(String name, Class<?> type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}
}
