package uk.co.tekkies.akko.onboard;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import com.codeminders.ardrone.ARDrone;
import com.codeminders.ardrone.ARDrone.LED;
import com.codeminders.ardrone.DroneStatusChangeListener;
import com.codeminders.ardrone.NavData;
import com.codeminders.ardrone.NavDataListener;

import uk.co.tekkies.akko.onboard.R;
import uk.co.tekkies.akko.onboard.utils.Arp;
import uk.co.tekkies.akko.onboard.utils.Debug;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, DroneStatusChangeListener, NavDataListener {

    protected static final String TAG = "MAIN";
	private static final long CONNECT_TIMEOUT = 3000;
	LocationManager locationManager=null;
	public TextView textViewStatus = null;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        SetupLogComsumer();

		findViewById(R.id.textViewArp).setOnClickListener(this);
        findViewById(R.id.textViewDrone).setOnClickListener(this);
        findViewById(R.id.textViewLocate).setOnClickListener(this);
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        
        textViewStatus.setText("IP:"+getLocalIpAddress());
        
        //onClickTextViewDrone();
        
        new UdpAsyncTask().execute(1985);

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
		case R.id.textViewDrone:
			onClickTextViewDrone();
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
		if(locationListener != null) {
			if(locationManager != null) {
				locationManager.removeUpdates(locationListener);
				locationListener = null;
				textViewStatus.setText("Stopped");
			}
		} else {
			textViewStatus.setText("Starting GPS");
			locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
			locationListener = new LocationListener() {
			    public void onLocationChanged(Location location) {
			      // Called when a new location is found by the network location provider.
		    	String logMessage = simpleDateFormat.format(Calendar.getInstance().getTime())+","+location.toString()+"\n";
		    	Debug.i(TAG, logMessage);
				try {
					FileOutputStream outputStream = new FileOutputStream("/sdcard/akkogps.log", true);
				  outputStream.write(logMessage.getBytes());
				  outputStream.close();
				} catch (Exception e) {
				  e.printStackTrace();
				}
				textViewStatus.setText(logMessage.replace(',', '\n'));
				//textViewStatus.setText(Integer.toString((int) location.getAccuracy())+","+Integer.toString((int) location.getSpeed())+","+Integer.toString((int) location.getBearing()));
		    	//Toast.makeText(getActivity(), location.toString(), Toast.LENGTH_SHORT).show();
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {
		    	Debug.i(TAG, provider+"|"+status+"|"+extras.toString());
		    }

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
		  };

		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
		
		}
		
	}

	protected MainActivity getMainActivity() {
		return this;
	}

	private void onClickTextViewDrone() {
		ARDrone drone;
        try
        {
            // Create ARDrone object,
            // connect to drone and initialize it.
            drone = new ARDrone();
            drone.addStatusChangeListener(this);
            drone.addNavDataListener(this);
            drone.connect();
            drone.clearEmergencySignal();
            //drone.sendDemoNavigationData();
            drone.playLED(LED.RED_SNAKE, 2, 5);
            Log.i("DRONE", "State:"+drone.getState());
            // Wait until drone is ready
            drone.waitForReady(CONNECT_TIMEOUT);
            // do TRIM operation
            drone.trim();
            // Take off
            System.err.println("Taking off");
            drone.takeOff();
            // Fly a little :)
            Thread.sleep(3500);
            // Land
            System.err.println("Landing");
            drone.land();
            // Give it some time to land
            Thread.sleep(5000);
            Log.i("DRONE", "State:"+drone.getState());
            // Disconnect from the done
            drone.disconnect();
            
        } catch(Throwable e) {
        	Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        	Log.i("DRONE", e.toString());
        }
        
        try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public class LogHandler extends Handler {

		@Override
		public void close() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void flush() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void publish(LogRecord record) {
			// TODO Auto-generated method stub
			Log.i("JAVALOG", record.getMessage());
		}
	}
	
	private void SetupLogComsumer() {
		  LogHandler handler = new LogHandler();
		  java.util.logging.Logger.getLogger("com.codeminders.ardrone").addHandler(handler );
		  java.util.logging.Logger.getLogger("com.codeminders.ardrone").setLevel(java.util.logging.Level.FINER);
		  java.util.logging.Logger.getLogger("com.codeminders.ardrone").log(java.util.logging.Level.FINER,"Fine log entry");
	}

	@Override
	public void navDataReceived(NavData arg0) {
		//Log.i("NAV", arg0.toString());
	}

	@Override
	public void ready() {
		Log.i("DRONE", "Ready");
	}
	
	public String getLocalIpAddress() {
	    
		String ip="";
		
		try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress()) {
	                    ip += inetAddress.getHostAddress() + "\n";
	                    Log.i(TAG, "***** IP="+ ip);
	                }
	            }
	        }
	    } catch (SocketException ex) {
	        Log.e(TAG, ex.toString());
	    }
	    return ip;
	}
	
	
    class UdpAsyncTask extends AsyncTask<Integer, String, String> {

		private static final int MAX_UDP_DATAGRAM_LEN = 1024;

		@Override
		protected String doInBackground(Integer... params) {
			String message;
	        byte[] lmessage = new byte[MAX_UDP_DATAGRAM_LEN];
	        DatagramPacket packet = new DatagramPacket(lmessage, lmessage.length);
	        DatagramSocket socket = null;
	        boolean keepRunning = true;
            try {
				socket = new DatagramSocket(params[0]);
		        while(keepRunning) {
	                socket.receive(packet);
	                message = new String(lmessage, 0, packet.getLength());
	                this.publishProgress(message);
	                if(message.equalsIgnoreCase("exit")){
	                	keepRunning = false;
	                }
		        }
            } catch (Throwable e) {
                e.printStackTrace();
            }
	        if (socket != null) {
	            socket.close();
	        }
	        return null;
	    }
    	
		@Override
		protected void onProgressUpdate(String... values) {
			Log.i("UDP", values[0]);
			getMainActivity().textViewStatus.setText("UDP:"+values[0]);
		} 
    }
	
	


	
}
