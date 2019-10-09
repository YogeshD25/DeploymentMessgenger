package com.neml.deploymentaapproval.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.neml.deploymentaapproval.Database.AppPreference;
import com.neml.deploymentaapproval.Listerner.AlertDialogListerner;
import com.neml.deploymentaapproval.R;

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
    public static AlertDialog getSimpleDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        AppPreference preferences = new AppPreference(context);
        builder.setTitle(context.getResources().getString(R.string.app_name));
        builder.setIcon(R.drawable.neml);
        builder.setMessage(message);
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }

    public static AlertDialog getSimpleDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.neml);
        AppPreference preferences = new AppPreference(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }
    public static AlertDialog getSimpleDialog(Context context, String message, final AlertDialogListerner listener) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(false);
        alertBuilder.setIcon(R.drawable.neml);
        alertBuilder.setMessage(message);
        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                listener.onConfirmed(true);
            }
        });


        final AlertDialog alertDialog = alertBuilder.create();
        return alertDialog;
    }


}
