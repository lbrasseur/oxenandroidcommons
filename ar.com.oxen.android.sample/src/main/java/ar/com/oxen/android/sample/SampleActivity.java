/**
 * 
 */
package ar.com.oxen.android.sample;

import roboguice.activity.GuiceActivity;
import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Actividad con ejemplos de uso de manejo de Accounts en ShellBO
 * 
 * @author mana
 */
public class SampleActivity extends GuiceActivity {
	@InjectView(R.id.minOrmSampleButton)
	private Button bnMiniOrmSample;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bnMiniOrmSample.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
						SampleActivity.this);
				dialogBuilder.setTitle("Ooops!");
				dialogBuilder.setMessage("Available soon!");

				dialogBuilder.create().show();
			}
		});
	}
}
