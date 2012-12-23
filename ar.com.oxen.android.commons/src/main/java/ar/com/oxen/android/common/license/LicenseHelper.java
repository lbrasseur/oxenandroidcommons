package ar.com.oxen.android.common.license;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import javax.inject.Inject;

import android.content.Context;
import ar.com.oxen.commons.license.api.License;
import ar.com.oxen.commons.license.api.LicenseSerializer;
import ar.com.oxen.commons.license.api.LicenseValidator;

public class LicenseHelper<I extends Serializable> {
	private final static String LICENSE_FILE = "license.txt";
	private Context context;
	private LicenseSerializer<I> licenseSerializer;
	private LicenseValidator<I> licenseValidator;

	@Inject
	public LicenseHelper(Context context,
			LicenseSerializer<I> licenseSerializer,
			LicenseValidator<I> licenseValidator) {
		super();
		this.context = context;
		this.licenseSerializer = licenseSerializer;
		this.licenseValidator = licenseValidator;
	}

	public void runLicensed(Runnable runnable,
			boolean showInvalidLicenseMessage, Object callbackEvent,
			String callbackTopic) {
		boolean valid = false;

		try {
			valid = this.licenseValidator.validate(this.getLicense());
		} catch (Exception e) {
		}

		if (valid) {
			runnable.run();
		}
	}

	private License<I> getLicense() {
		String licenseString = null;
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(
					this.context.openFileInput(LICENSE_FILE)));
			licenseString = reader.readLine();
		} catch (Exception e) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}

		if (licenseString != null && !licenseString.isEmpty()) {
			return this.licenseSerializer.deserializeLicence(licenseString);
		} else {
			return null;
		}
	}
}
