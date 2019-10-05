package com.neml.deploymentaapproval.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class utils {

    public static boolean isNetworkAvailable(Context context) {

        boolean isConnected = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        if (netInfo != null && netInfo.isConnected()) {
            isConnected = true;
        }
        return isConnected;
    }

}
