package uk.co.tekkies.akko.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Arp {
	private static final String TAG = "ARP";

	public static Map<String, String> createArpMap() throws IOException {      
		Map<String, String> checkMapARP = new HashMap<String, String>();
		BufferedReader localBufferdReader = new BufferedReader(new FileReader(new File("/proc/net/arp")));
	    try {
			String line = "";       
			while ((line = localBufferdReader.readLine()) != null) {            
			    String[] ipmac = line.split("[ ]+");
			    if (!ipmac[0].matches("IP")) {
			        String ip = ipmac[0];
			        String mac = ipmac[3];
			        Debug.i(TAG, ip+" "+mac);
			        if (!checkMapARP.containsKey(ip)) {
			            checkMapARP.put(ip, mac);               
			        }                   
			    }
			}
		} catch (Exception e) {
			Debug.printStacktrace(e);
		} finally {
			localBufferdReader.close();
		}
	    return Collections.unmodifiableMap(checkMapARP);
	}
}
