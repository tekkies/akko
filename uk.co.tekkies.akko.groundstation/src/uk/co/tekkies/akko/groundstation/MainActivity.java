package uk.co.tekkies.akko.groundstation;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new UdpAsyncTask().execute(0);
	}
	
    class UdpAsyncTask extends AsyncTask<Integer, String, String> {

    	@Override
		protected String doInBackground(Integer... params) {
			sendUdpPacket();
	        return null;
	    }

    }	

	private void sendUdpPacket() {
		String messageStr="Message from groundstation";
		int server_port = 1985;
		DatagramSocket s=null;
		InetAddress local=null;
		try {
			s = new DatagramSocket();
			local = InetAddress.getByName("localhost");
			int msg_length=messageStr.length();
			byte[] message = messageStr.getBytes();
			DatagramPacket p = new DatagramPacket(message, msg_length,local,server_port);
			s.send(p);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
