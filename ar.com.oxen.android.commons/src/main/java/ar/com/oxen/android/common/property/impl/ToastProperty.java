package ar.com.oxen.android.common.property.impl;

import android.content.Context;
import android.widget.Toast;
import ar.com.oxen.commons.property.api.Property;

public class ToastProperty implements Property<String> {
	private Context context;

	public ToastProperty(Context context) {
		super();
		this.context = context;
	}

	@Override
	public String get() {
		return null;
	}

	@Override
	public void set(String newValue) {
		if (newValue != null) {
			Toast.makeText(this.context, newValue, 5000).show();
		}
	}
}
