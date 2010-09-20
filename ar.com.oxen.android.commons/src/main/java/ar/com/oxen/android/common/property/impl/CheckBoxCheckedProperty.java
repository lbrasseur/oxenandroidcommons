package ar.com.oxen.android.common.property.impl;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import ar.com.oxen.commons.property.api.ObservableProperty;
import ar.com.oxen.commons.property.impl.SimpleObservable;

public class CheckBoxCheckedProperty extends SimpleObservable implements
		ObservableProperty<Boolean> {
	private CheckBox checkBox;

	public CheckBoxCheckedProperty(CheckBox checkBox) {
		super();
		this.checkBox = checkBox;
		this.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton,
					boolean selected) {
				CheckBoxCheckedProperty.this.notifyObservers();
			}
		});
	}

	@Override
	public Boolean get() {
		return checkBox.isChecked();
	}

	@Override
	public void set(Boolean newValue) {
		checkBox.setChecked(newValue);
	}
}
