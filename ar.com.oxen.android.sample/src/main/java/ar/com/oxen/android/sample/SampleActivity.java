package ar.com.oxen.android.sample;

import roboguice.activity.GuiceActivity;
import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Sample activity.
 * @author Lautaro
 *
 */
public class SampleActivity extends GuiceActivity {
	@InjectView(R.id.minOrmSampleButton)
	private Button bnMiniOrmSample;

	@InjectView(R.id.mvpButton)
	private Button bnMvp;

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

		bnMvp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent("ar.com.oxen.android.sample.MVP"));
			}
		});
	}
}
