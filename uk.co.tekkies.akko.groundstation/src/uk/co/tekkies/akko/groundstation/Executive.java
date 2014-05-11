package uk.co.tekkies.akko.groundstation;

import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.PointF;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;

public class Executive {
	private static final int MAX_UDP_DATAGRAM_LEN = 1024;
	private static final int MAX_THREADS=3;
	private static final int ONBOARD_PORT = 1985;
	private static final int GROUNDSTATION_PORT = 1986;
	
	private volatile boolean keepRunning = true;
	private ThreadPoolExecutor threadPoolExecutor;
	private MainActivity mainActivity=null;
	int session = 0;
	private volatile InetAddress inetAddressOnboard=null;

	public Executive() {
		threadPoolExecutor = new ThreadPoolExecutor(
				MAX_THREADS, // core thread pool size
				MAX_THREADS, // maximum thread pool size
			    1, // time to wait before resizing pool
			    TimeUnit.MINUTES, 
			    new ArrayBlockingQueue<Runnable>(MAX_THREADS, true),
			    new ThreadPoolExecutor.CallerRunsPolicy());
	}
	
	public void start(MainActivity mainActivity)	{
		this.mainActivity = mainActivity;
		keepRunning = true;
        new UdpReceiveAsyncTask().executeOnExecutor(threadPoolExecutor, GROUNDSTATION_PORT);
		new UdpSendAsyncTask().executeOnExecutor(threadPoolExecutor, 0);
	}
	
	public void stop() {
		mainActivity = null;
		keepRunning = false;
	}
	
	class UdpSendAsyncTask extends AsyncTask<Integer, String, String> {
		PointF lhsJoystick=new PointF();
		DatagramSocket datagramSocketSend=null;

		@Override
		protected String doInBackground(Integer... params) {
			while(keepRunning) {
				try {
					connectAndRun();
					Thread.sleep(1000);
				} catch (Exception e) {
					//Pokemon: Gotta keep trying
					e.printStackTrace();
				}
			}
	        return null;
	    }

		private void connectAndRun() throws IOException {
    		datagramSocketSend = new DatagramSocket();
			pingOnboard();
			if(inetAddressOnboard != null) {
				Log.i("COMMS","Connected"+inetAddressOnboard.toString());
				mainLoop();
			}
		}

		private void mainLoop() {
			while(keepRunning) {
				mainActivity.getJoystickView().getLhsStick(lhsJoystick);
				//sendUdpPacket();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void pingOnboard() throws IOException {
			inetAddressOnboard = null;
			session = 1+(int) (Math.random()*1000000);
			send("command=ping|session="+session, InetAddress.getByName("255.255.255.255"));
			int loops = 20;
			while(inetAddressOnboard == null && loops > 0) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				loops--;
			}
		}

		private void send(String message, InetAddress inetAddress) throws IOException {
			datagramSocketSend.send(new DatagramPacket(message.getBytes(), message.length(),inetAddress,ONBOARD_PORT));
		}

		private void sendUdpPacket(InetAddress inetAddress) {
			String messageStr="lhsX="+lhsJoystick.x;
			int server_port = 1985;
			try {
				int msg_length=messageStr.length();
				byte[] message = messageStr.getBytes();
				DatagramPacket p = new DatagramPacket(message, msg_length,inetAddress,server_port);
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
	
    class UdpReceiveAsyncTask extends AsyncTask<Integer, String, String> {


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
	                inetAddressOnboard = packet.getAddress();
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
