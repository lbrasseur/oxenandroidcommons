package ar.com.oxen.android.common.license;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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

	public boolean isLicenseOk() {
		try {
			return this.licenseValidator.validate(this.readLicense());
		} catch (Exception e) {
			return false;
		}
	}

	public License<I> readLicense() {
		String licenseString = this.readLicenseString();

		if (licenseString != null && !licenseString.trim().equals("")) {
			return this.licenseSerializer.deserializeLicence(licenseString);
		} else {
			return null;
		}
	}

	public String readLicenseString() {
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

		return licenseString;
	}

	public void saveLicense(License<I> license) {
		this.saveLicenseString(this.licenseSerializer.serializeLicence(license));
	}

	public void saveLicenseString(String licenseString) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					this.context.openFileOutput(LICENSE_FILE,
							Context.MODE_WORLD_WRITEABLE))));
			writer.println(licenseString);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
