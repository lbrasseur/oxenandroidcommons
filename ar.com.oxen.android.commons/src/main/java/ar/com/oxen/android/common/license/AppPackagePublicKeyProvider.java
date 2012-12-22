package ar.com.oxen.android.common.license;

import java.io.ByteArrayInputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.inject.Inject;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import ar.com.oxen.commons.license.api.PublicKeyProvider;

public class AppPackagePublicKeyProvider implements PublicKeyProvider {
	private Context context;

	@Inject
	public AppPackagePublicKeyProvider(Context context) {
		super();
		this.context = context;
	}

	@Override
	public PublicKey getPublicKey() {
		try {
			Signature signatures[] = context.getPackageManager()
					.getPackageInfo(context.getPackageName(),
							PackageManager.GET_SIGNATURES).signatures;

			byte[] cert = signatures[0].toByteArray();

			CertificateFactory certificateFactory = CertificateFactory
					.getInstance("X509");

			X509Certificate certificate = (X509Certificate) certificateFactory
					.generateCertificate(new ByteArrayInputStream(cert));

			return certificate.getPublicKey();
		} catch (NameNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (CertificateException e) {
			throw new IllegalStateException(e);
		}
	}
}
