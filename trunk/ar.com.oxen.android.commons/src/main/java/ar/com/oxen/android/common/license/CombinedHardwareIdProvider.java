package ar.com.oxen.android.common.license;

import javax.inject.Inject;

import ar.com.oxen.commons.license.api.HardwareIdProvider;

public class CombinedHardwareIdProvider implements HardwareIdProvider {
	private IdHelper idHelper;

	@Inject
	public CombinedHardwareIdProvider(IdHelper idHelper) {
		super();
		this.idHelper = idHelper;
	}

	@Override
	public byte[] getHardwareId() {
		String combinedId = this.idHelper.getImei()
				+ this.idHelper.getPseudoId() + this.idHelper.getAndroidId()
				+ this.idHelper.getWifiMac() + this.idHelper.getBluetoothMac();
		return combinedId.getBytes();
	}

}
