package com.neml.deploymentaapproval.FCMConnection;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.neml.deploymentaapproval.Activities.NotificationList;
import com.neml.deploymentaapproval.Logger.Logg;
import com.neml.deploymentaapproval.R;
import com.neml.deploymentaapproval.Utils.Constants;

public class FCMMessageReceiver extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        String deploymentNo = data.get(Constants.fcmMessageKey.deploymentNo);
        String versionNo = data.get(Constants.fcmMessageKey.versionNo);
        String projectName = data.get(Constants.fcmMessageKey.projectName);
        showNotification(deploymentNo, versionNo,projectName);
    }

    public void showNotification(String title, String message ,String applicatioName){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("MyNotification","MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(getApplicationContext(), NotificationList.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"MyNotification")
                .setContentTitle("Deployment No: "+ title)
                .setSmallIcon(R.drawable.shrot_logo)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentText("Application Name: "+applicatioName)
                .setContentInfo("Version No: "+message)
                .setSubText("Tap to open App")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());
    }
    @Override
    public void onNewToken(String registrationToken) {
        Logg.d("Firebase #onNewToken registrationToken=" + registrationToken);
        startService(new Intent(this, FCMTokenReceiver.class));
    }
}
