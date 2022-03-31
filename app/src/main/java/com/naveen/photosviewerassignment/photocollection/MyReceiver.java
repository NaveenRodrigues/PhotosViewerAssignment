package com.naveen.photosviewerassignment.photocollection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.naveen.photosviewerassignment.R;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if (status.equals(context.getString(R.string.no_internet_avilable))) {
            Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        } else if (status.isEmpty()) {
            status = context.getString(R.string.no_internet_avilable);
            Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        }
    }
}
