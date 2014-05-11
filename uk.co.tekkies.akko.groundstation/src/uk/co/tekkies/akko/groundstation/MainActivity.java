package uk.co.tekkies.akko.groundstation;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	JoystickView joystickView;
	Executive executive;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		joystickView = (JoystickView) findViewById(R.id.joystickView1);
		executive = new Executive();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public JoystickView getJoystickView() {
		return joystickView;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		executive.start(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		executive.stop();
	}
	
	
}
