package ar.com.oxen.android.common.property.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ar.com.oxen.commons.property.impl.SimpleObservable;

public class ButonClickObservable extends SimpleObservable {

	public ButonClickObservable(Button button) {
		super();
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				ButonClickObservable.this.notifyObservers();
			}
		});
	}
}
