package ar.com.oxen.android.sample.mvp;

import java.util.regex.Pattern;

import ar.com.oxen.commons.bean.api.BeanWrapper;
import ar.com.oxen.commons.bean.impl.simple.SimpleWrapperFactory;
import ar.com.oxen.commons.property.api.Observer;
import ar.com.oxen.commons.property.impl.Binder;
import ar.com.oxen.commons.property.impl.bean.BeanProperty;

public class ViewPresenter {
	private Pattern emailPattern = Pattern
			.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
	private PresentationModel pModel;
	private Person model;

	public ViewPresenter(PresentationModel pModel, Person model) {
		super();
		this.pModel = pModel;
		this.model = model;
		this.configure();
	}

	private void configure() {
		BeanWrapper<Person> modelWrapper = new SimpleWrapperFactory()
				.wrapBean(this.model);

		this.pModel.signupClicked.addObserver(new Observer() {
			@Override
			public void update() {
				pModel.notification.set("Registered user: " + model);
			}
		});

		this.pModel.name.addObserver(new ValidationObserver());
		this.pModel.email.addObserver(new ValidationObserver());
		this.pModel.acceptTerms.addObserver(new ValidationObserver());

		new Binder()
			.bindProperty(this.pModel.name,	new BeanProperty<String>(modelWrapper, "name"))
			.bindProperty(this.pModel.email, new BeanProperty<String>(modelWrapper, "email"));

		// TODO: How can this be done automatically?
		this.pModel.name.set(model.getName());
		this.pModel.email.set(model.getEmail());
	}

	/**
	 * Validation observer -just for sample purposes. I should build a
	 * validation API.
	 * 
	 * @author Lautaro
	 * 
	 */
	private class ValidationObserver implements Observer {
		@Override
		public void update() {
			if (pModel.name.get() == null
					|| pModel.name.get().trim().equals("")) {
				pModel.errorMessage.set("The name is required");
				pModel.signupAllowed.set(false);
				return;
			}

			if (pModel.email.get() == null
					|| !emailPattern.matcher(pModel.email.get()).matches()) {
				pModel.errorMessage.set("The email is invalid");
				pModel.signupAllowed.set(false);
				return;
			}

			if (pModel.acceptTerms.get() == null || !pModel.acceptTerms.get()) {
				pModel.errorMessage.set("You must accept terms and conditions");
				pModel.signupAllowed.set(false);
				return;
			}

			pModel.errorMessage.set("");
			pModel.signupAllowed.set(true);
		}
	}
}
