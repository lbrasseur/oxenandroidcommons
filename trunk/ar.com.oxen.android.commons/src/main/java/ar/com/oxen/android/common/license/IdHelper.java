package ar.com.oxen.android.common.license;

import javax.inject.Inject;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class IdHelper {
	private Context context;

	@Inject
	public IdHelper(Context context) {
		super();
		this.context = context;
	}

	public String getImei() {
		TelephonyManager telephonyManager = (TelephonyManager) this.context
				.getSystemService(Context.TELEPHONY_SERVICE);

		if (telephonyManager != null) {
			/* Requires READ_PHONE_STATE */
			return telephonyManager.getDeviceId();
		} else {
			return "";
		}
	}

	public String getPseudoId() {
		return "35" + // we make this look like a valid IMEI
				Build.BOARD.length() % 10 + Build.BRAND.length() % 10
				+ Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10
				+ Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
				+ Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10
				+ Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10
				+ Build.TAGS.length() % 10 + Build.TYPE.length() % 10
				+ Build.USER.length() % 10; // 13 digits
	}

	public String getAndroidId() {
		return Secure.getString(this.context.getContentResolver(),
				Secure.ANDROID_ID);
	}

	public String getWifiMac() {
		WifiManager wifiManager = (WifiManager) this.context
				.getSystemService(Context.WIFI_SERVICE);

		WifiInfo connectionInfo = null;

		if (wifiManager != null) {
			connectionInfo = wifiManager.getConnectionInfo();
		}

		if (connectionInfo != null) {
			/* requires android.permission.ACCESS_WIFI_STATE or comes as null */
			return connectionInfo.getMacAddress();
		} else {
			return "";
		}
	}

	public String getBluetoothMac() {
		BluetoothAdapter bluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (bluetoothAdapter != null) {
			/* requires android.permission.BLUETOOTH */
			return bluetoothAdapter.getAddress();
		} else {
			return "";
		}
	}
}
