package uk.co.tekkies.akko.groundstation;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.PointF;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	JoystickView joystickView;
	ThreadPoolExecutor threadPoolExecutor; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		joystickView = (JoystickView) findViewById(R.id.joystickView1);
		
		int maxThreads=3;
		AsyncTask<Integer, String, String> udpSendAsyncTask = new UdpSendAsyncTask();
		threadPoolExecutor = new ThreadPoolExecutor(
				maxThreads, // core thread pool size
			    maxThreads, // maximum thread pool size
			    1, // time to wait before resizing pool
			    TimeUnit.MINUTES, 
			    new ArrayBlockingQueue<Runnable>(maxThreads, true),
			    new ThreadPoolExecutor.CallerRunsPolicy());
		udpSendAsyncTask.executeOnExecutor(threadPoolExecutor, 0);
        new UdpReceiveAsyncTask().executeOnExecutor(threadPoolExecutor, 1986);
	}
	
    class UdpSendAsyncTask extends AsyncTask<Integer, String, String> {
    	PointF lhsJoystick=new PointF();
		DatagramSocket datagramSocketSend=null;

    	@Override
		protected String doInBackground(Integer... params) {
    		boolean keepRunning = true;
    		while(keepRunning) {
	    		joystickView.getLhsStick(lhsJoystick);
				sendUdpPacket();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
	        return null;
	    }

    	private void sendUdpPacket() {
    		String messageStr="lhsX="+lhsJoystick.x;
    		int server_port = 1985;
    		InetAddress local=null;
    		try {
        		datagramSocketSend = new DatagramSocket();
    			local = InetAddress.getByName("localhost");
    			int msg_length=messageStr.length();
    			byte[] message = messageStr.getBytes();
    			DatagramPacket p = new DatagramPacket(message, msg_length,local,server_port);
    			datagramSocketSend.send(p);
    		} catch (UnknownHostException e) {
    			e.printStackTrace();
    		} catch (SocketException e1) {
    			e1.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}	
    	}
    	
    }	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
    class UdpReceiveAsyncTask extends AsyncTask<Integer, String, String> {

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
			Log.i("UdpRx", values[0]);
			//getMainActivity().textViewStatus.setText("UDP:"+values[0]);
		} 
    }
	
	
	
}
