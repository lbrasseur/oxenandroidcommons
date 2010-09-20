package ar.com.oxen.android.common.property.impl;

import android.widget.TextView;
import ar.com.oxen.commons.property.api.Property;

public class TextViewTextProperty implements Property<String> {
	private TextView textView;

	public TextViewTextProperty(TextView label) {
		this.textView = label;
	}

	@Override
	public String get() {
		return this.textView.getText().toString();
	}

	@Override
	public void set(String newValue) {
		this.textView.setText(newValue);
	}
}
