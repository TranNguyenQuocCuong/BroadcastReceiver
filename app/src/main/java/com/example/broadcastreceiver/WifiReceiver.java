package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;
import android.util.Log;
import android.os.Handler;
import android.os.Looper;

public class WifiReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {
            int wifiState =
                    intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                            WifiManager.WIFI_STATE_UNKNOWN);
            Log.d(TAG, "WiFi state changed: " + wifiState);

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    switch (wifiState) {
                        case WifiManager.WIFI_STATE_ENABLED:
                            Toast.makeText(context, "WiFi is enabled", Toast.LENGTH_LONG).show();
                            break;
                        case WifiManager.WIFI_STATE_DISABLED:
                            Toast.makeText(context, "WiFi is disabled", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(context, "WiFi state unknown", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            });
        }
    }
}
