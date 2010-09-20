package ar.com.oxen.android.sample.mvp;

import ar.com.oxen.commons.property.api.Observable;
import ar.com.oxen.commons.property.api.ObservableProperty;
import ar.com.oxen.commons.property.impl.SimpleObservable;
import ar.com.oxen.commons.property.impl.SimpleObservableProperty;

public class PresentationModel {
	public final ObservableProperty<String> name = new SimpleObservableProperty<String>();
	public final ObservableProperty<String> email = new SimpleObservableProperty<String>();
	public final ObservableProperty<Boolean> acceptTerms = new SimpleObservableProperty<Boolean>(false);
	public final ObservableProperty<Boolean> signupAllowed = new SimpleObservableProperty<Boolean>(false);
	public final Observable signupClicked = new SimpleObservable();
	public final ObservableProperty<String> errorMessage = new SimpleObservableProperty<String>();
	public final ObservableProperty<String> notification = new SimpleObservableProperty<String>();
}
