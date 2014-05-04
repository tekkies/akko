package uk.co.tekkies.akko.onboard.utils;

import android.util.Log;

public class Debug {

	private static boolean debug = true;

	public static void i(String tag, String string) {
		if (debug) {
			Log.i(tag, string);
		}
	}

	public static void printStacktrace(Exception e) {
		if (debug) {
			e.printStackTrace();
		}
	}

}
