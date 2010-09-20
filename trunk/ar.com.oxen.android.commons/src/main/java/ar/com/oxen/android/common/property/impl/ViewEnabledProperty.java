package ar.com.oxen.android.common.property.impl;


import android.view.View;
import ar.com.oxen.commons.property.api.Property;

public class ViewEnabledProperty implements Property<Boolean> {
	private View view;

	public ViewEnabledProperty(View view) {
		super();
		this.view = view;
	}

	@Override
	public Boolean get() {
		return this.view.isEnabled();
	}

	@Override
	public void set(Boolean value) {
		this.view.setEnabled(value);
	}
}
