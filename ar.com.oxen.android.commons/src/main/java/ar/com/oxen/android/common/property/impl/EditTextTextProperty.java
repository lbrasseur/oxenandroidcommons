package ar.com.oxen.android.common.property.impl;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import ar.com.oxen.commons.property.api.ObservableProperty;
import ar.com.oxen.commons.property.impl.SimpleObservable;

public class EditTextTextProperty extends SimpleObservable implements
		ObservableProperty<String> {
	private EditText textField;

	public EditTextTextProperty(EditText textField) {
		super();
		this.textField = textField;
		this.textField.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean hasFocus) {
				if (!hasFocus) {
					EditTextTextProperty.this.notifyObservers();
				}
			}
		});
	}

	@Override
	public String get() {
		return textField.getText().toString();
	}

	@Override
	public void set(String newValue) {
		textField.setText(newValue);
	}
}
