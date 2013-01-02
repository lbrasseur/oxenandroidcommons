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
import ar.com.oxen.commons.license.api.HardwareIdProvider;
import ar.com.oxen.commons.license.api.License;
import ar.com.oxen.commons.license.api.LicenseSerializer;
import ar.com.oxen.commons.license.api.LicenseValidator;
import ar.com.oxen.commons.license.impl.DefaultLicenseInfo;

public class LicenseHelper<I extends Serializable> {
	private final static String LICENSE_FILE = "license.txt";
	private Context context;
	private LicenseSerializer<I> licenseSerializer;
	private LicenseValidator<I> licenseValidator;
	private HardwareIdProvider hardwareIdProvider;

	@Inject
	public LicenseHelper(Context context,
			LicenseSerializer<I> licenseSerializer,
			LicenseValidator<I> licenseValidator,
			HardwareIdProvider hardwareIdProvider) {
		super();
		this.context = context;
		this.licenseSerializer = licenseSerializer;
		this.licenseValidator = licenseValidator;
		this.hardwareIdProvider = hardwareIdProvider;
	}

	public void runLicensed(Runnable runnable) {
		boolean valid = false;

		try {
			valid = this.licenseValidator.validate(this.getLicense());
		} catch (Exception e) {
		}
		
		if (valid) {
			runnable.run();
		} else {
			borrame();
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

		if (licenseString != null && !licenseString.trim().equals("")) {
			return this.licenseSerializer.deserializeLicence(licenseString);
		} else {
			return null;
		}
	}

	private void borrame() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					this.context.openFileOutput("requerimiento.txt",
							Context.MODE_WORLD_WRITEABLE))));
			writer.println(this.licenseSerializer
					.serializeLicenceInfo((I) new DefaultLicenseInfo("Apple","mi modulo",
							null, "mi codigo", hardwareIdProvider
									.getHardwareId())));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
