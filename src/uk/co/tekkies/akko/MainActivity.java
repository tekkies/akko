package uk.co.tekkies.akko;

import java.io.IOException;

import uk.co.tekkies.akko.utils.Arp;
import uk.co.tekkies.akko.utils.Debug;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    protected static final String TAG = "MAIN";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textViewArp).setOnClickListener(this);
        findViewById(R.id.textViewLocate).setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.textViewArp:
			onClickTextViewArp();
			break;
		case R.id.textViewLocate:
			onClickTextViewLocate();
			break;
		}
	}

	private void onClickTextViewArp() {
		Toast.makeText(this, "Arp pressed", Toast.LENGTH_SHORT).show();
		doArp();
	}


	private void doArp() {
		try {
			Arp.createArpMap();
		} catch (IOException e) {
			Debug.printStacktrace(e);
		}
	}

	private void onClickTextViewLocate() {
		doLocate();
	}

	LocationListener locationListener=null;

	private void doLocate() {
		if(locationListener == null) {
		
		
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {
		      // Called when a new location is found by the network location provider.
		    	Debug.i(TAG, location.toString());
		    	//Toast.makeText(getActivity(), location.toString(), Toast.LENGTH_SHORT).show();
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
		  };

		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
		
		}
		
	}

	protected Context getActivity() {
		return this;
	}


    
}
