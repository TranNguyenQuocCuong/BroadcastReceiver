package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {
    private static final String TAG = "BatteryReceiver";
    private static final int LOW_BATTERY_THRESHOLD = 20;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            float batteryPct = level * 100 / (float) scale;

            Log.d(TAG, "Battery level changed to: " + batteryPct + "%");

            if (batteryPct <= LOW_BATTERY_THRESHOLD) {
                Toast.makeText(context, "Warning: Low Battery! (" + batteryPct + "%)", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Battery level: " + batteryPct + "%", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

