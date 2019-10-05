package com.neml.deploymentaapproval.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import com.neml.deploymentaapproval.FCMConnection.FCMTokenReceiver;
import com.neml.deploymentaapproval.Logger.Logg;
import com.neml.deploymentaapproval.R;

public class MainActivity extends AppCompatActivity {
    EditText email, passWord;
    Button login;
    TextView register;
    boolean isEmailValid, isPasswordValid;
    TextInputLayout emailError, passError;
    private String URLline = "http://172.22.22.71:8080/NeMLDeploymentTracker/login/validateUserAndroid/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        Logg.d();
        sendFcmRegistrationToken();

//
//        if (android.os.Build.VERSION.SDK_INT > 9)
//        {
//            StrictMode.ThreadPolicy policy = new
//                    StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
                //loginUser();
                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(intent);
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("MyNotification","MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfull";
                        if(!task.isSuccessful()){
                            msg = "Failed";
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initUI() {
        email = (EditText) findViewById(R.id.email);
        passWord = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        passError = (TextInputLayout) findViewById(R.id.passError);
    }

    public void SetValidation() {
        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        }  else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }

        if (passWord.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }


    }
    private void loginUser(){

        final String username = email.getText().toString().trim();
        final String password = passWord.getText().toString().trim();
        //HttpsTrustManager.allowAllSSL();


//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLline,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
//                        //parseData(response);
//                       // Intent i = new Intent(MainActivity.this,MenuActivity.class);
//                       // startActivity(i);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
//                    }
//                }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("userId",username);
//                params.put("userPassword",password);
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = URLline;
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("userId", username);
            jsonBody.put("userPassword", password);
            jsonBody.put("token", FCMTokenReceiver.token);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

//                @Override
//                protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                    String responseString = "";
//                    if (response != null) {
//                        responseString = String.valueOf(response.statusCode);
//                        // can get more details such as response.headers
//                    }
//                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void parseData(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {

                    JSONObject dataobj = dataArray.getJSONObject(i);
                //    firstName = dataobj.getString("name");
                  //  hobby = dataobj.getString("hobby");
                }

                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        Logg.d();
        sendFcmRegistrationToken();
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

    private void checkGoogleApiAvailability() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (resultCode == ConnectionResult.SUCCESS) {
                Logg.d("GoogleApi is available");
            } else {
                apiAvailability.getErrorDialog(this, resultCode, 1).show();
            }
        }
    }

}