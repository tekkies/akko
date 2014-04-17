package uk.co.tekkies.akko;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textViewArp).setOnClickListener(this);
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
		}
	}


	private void onClickTextViewArp() {
		Toast.makeText(this, "Arp pressed", Toast.LENGTH_SHORT).show();
		doArp();
	}


	private void doArp() {
		try {
			createArpMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String, String> createArpMap() throws IOException {      
		Map<String, String> checkMapARP = new HashMap<String, String>();
		BufferedReader localBufferdReader = new BufferedReader(new FileReader(new File("/proc/net/arp")));
	    String line = "";       
	    while ((line = localBufferdReader.readLine()) == null) {
	        localBufferdReader.close();
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        localBufferdReader = new BufferedReader(new FileReader(new File("/proc/net/arp")));
	    }
	    do {            
	        String[] ipmac = line.split("[ ]+");
	        if (!ipmac[0].matches("IP")) {
	            String ip = ipmac[0];
	            String mac = ipmac[3];
	            if (!checkMapARP.containsKey(ip)) {
	                checkMapARP.put(ip, mac);               
	            }                   
	        }
	    } while ((line = localBufferdReader.readLine()) != null);
	    return Collections.unmodifiableMap(checkMapARP);
	}
    
}
