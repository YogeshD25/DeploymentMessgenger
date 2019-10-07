package com.neml.deploymentaapproval.FCMConnection;


import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.neml.deploymentaapproval.Database.AppPreference;
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
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
     ///   AppPreference.getInstance(this).setValue(getString(R.string.firebase_cloud_messaging_token), token);

        // To implement: Only if user is registered, i.e. UserId is available in preference, update token on server.
      //  int userId = SharedPreferenceUtils.getInstance(this).getIntValue(getString(R.string.user_id), 0);
        //if (userId != 0) {
            // Implement code to update registration token to server
       // }
    }


    private void sendTokenToServer() {
        AppPreference appPreference = new AppPreference(getApplicationContext());
        NetworkUtils.postConnectionSendToken(getApplicationContext(), Constants.UrlLinks.sendToken,token,appPreference.getUserID());
    }
}