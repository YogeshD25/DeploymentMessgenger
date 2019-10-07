package com.neml.deploymentaapproval.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.neml.deploymentaapproval.Database.AppPreference;
import com.neml.deploymentaapproval.FCMConnection.FCMTokenReceiver;
import com.neml.deploymentaapproval.Logger.Logg;
import com.neml.deploymentaapproval.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 3000;
    AppPreference appPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        appPreference = new AppPreference(SplashActivity.this);
       // sendFcmRegistrationToken();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!appPreference.getLoginDone()){
                    Intent i=new Intent(SplashActivity.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i=new Intent(SplashActivity.this,
                            MenuActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
    @Override
    protected void onResume() {
        super.onResume();

        Logg.d();
        //sendFcmRegistrationToken();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logg.d();
    }

    private void sendFcmRegistrationToken() {
        Intent intent = new Intent(this, FCMTokenReceiver.class);
        startService(intent);
    }
}
