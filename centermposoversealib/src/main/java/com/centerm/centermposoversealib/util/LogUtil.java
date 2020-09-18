package com.centerm.centermposoversealib.util;

import android.util.Log;

public class LogUtil {

	private static final boolean isDebug = true;

	private static final String TAG = "CENTERM-POS-SDK-OVERSEA";

	public static void print(String msg) {
		if (isDebug) {
			Log.d(TAG, msg);
		}
	}

}
