package com.neml.deploymentaapproval.FCMConnection;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.neml.deploymentaapproval.Logger.Logg;
import com.neml.deploymentaapproval.NetworkUtils.NetworkUtils;
import com.neml.deploymentaapproval.Utils.Constants;

import androidx.annotation.NonNull;

public class FCMTokenReceiver extends IntentService {
    public static String token = "";

    public FCMTokenReceiver() {
        super("FcmTokenRegistrationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {

                            Logg.d("Firebase getInstanceId failed " + task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        token = task.getResult().getToken();

                        Logg.d("Firebase registrationToken= " + token);
                        sendTokenToServer();

                        //TODO register token to your server.

                    }
                });
    }

    private void sendTokenToServer() {

        NetworkUtils.postConnectionSendToken(getApplicationContext(), Constants.UrlLinks.sendToken,token);
    }
}