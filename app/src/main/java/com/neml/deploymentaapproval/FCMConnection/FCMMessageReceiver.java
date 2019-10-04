package com.neml.deploymentaapproval.FCMConnection;

import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.neml.deploymentaapproval.Logger.Logg;
import com.neml.deploymentaapproval.FCMConnection.FCMTokenReceiver;
import com.neml.deploymentaapproval.R;

public class FCMMessageReceiver extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        String value1 = data.get("myKey1");
        String value2 = data.get("myKey2");
        Logg.d(value1);
        Logg.d(value2);
        showNotification(value1, value2);
    }

    public void showNotification(String title, String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"MyNotification")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_normal)
                .setAutoCancel(true)
                .setContentText(message);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());
    }
    @Override
    public void onNewToken(String registrationToken) {


        Logg.d("Firebase #onNewToken registrationToken=" + registrationToken);

        startService(new Intent(this, FCMTokenReceiver.class));
    }
}
