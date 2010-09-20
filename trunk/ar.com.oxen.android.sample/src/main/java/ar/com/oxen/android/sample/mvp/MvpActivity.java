package ar.com.oxen.android.sample.mvp;


import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import ar.com.oxen.android.common.property.impl.ButonClickObservable;
import ar.com.oxen.android.common.property.impl.CheckBoxCheckedProperty;
import ar.com.oxen.android.common.property.impl.EditTextTextProperty;
import ar.com.oxen.android.common.property.impl.TextViewTextProperty;
import ar.com.oxen.android.common.property.impl.ToastProperty;
import ar.com.oxen.android.common.property.impl.ViewEnabledProperty;
import ar.com.oxen.android.sample.R;
import ar.com.oxen.commons.property.impl.Binder;
import roboguice.activity.GuiceActivity;
import roboguice.inject.InjectView;

public class MvpActivity extends GuiceActivity {
	@InjectView(R.id.nameEdit)
	private EditText name;
	
	@InjectView(R.id.emailEdit)
	private EditText email;
	
	@InjectView(R.id.acceptTermsCheckBox)
	private CheckBox acceptTerms;
	
	@InjectView(R.id.errorMessageText)
	private TextView errorMessage;
	
	@InjectView(R.id.signupButton)
	private Button signup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mvp_sample);

		Person model = new Person();
		PresentationModel pModel = new PresentationModel();
		
		new Binder()
				.bindProperty(pModel.name, new EditTextTextProperty(name))
				.bindProperty(pModel.email, new EditTextTextProperty(email))
				.bindProperty(pModel.acceptTerms, new CheckBoxCheckedProperty(acceptTerms))
				.bindProperty(pModel.signupAllowed, new ViewEnabledProperty(signup))
				.bindProperty(pModel.errorMessage, new TextViewTextProperty(errorMessage))
				.bindProperty(pModel.notification, new ToastProperty(this))
				.bindObservable(new ButonClickObservable(signup), pModel.signupClicked);

		new ViewPresenter(pModel, model);
	}
}
