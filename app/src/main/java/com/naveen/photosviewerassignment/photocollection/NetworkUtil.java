package com.naveen.photosviewerassignment.photocollection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.naveen.photosviewerassignment.R;

public class NetworkUtil {
    public static String getConnectivityStatusString(Context context) {
        String status = null;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = context.getString(R.string.wifi_enabled);
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = context.getString(R.string.mobile_data_enabled);
                return status;
            }
        } else {
            status = context.getString(R.string.no_internet_avilable);
            return status;
        }
        return status;
    }
}
