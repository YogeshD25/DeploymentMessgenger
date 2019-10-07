package com.neml.deploymentaapproval.Database;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    private static Context mContext;
    private SharedPreferences preferences;
    private static AppPreference instance;
    public static AppPreference getInstance(){
        if ( instance == null){
            instance = new AppPreference(mContext);
        }
        return instance;
    }

    public AppPreference(Context context) {
        mContext = context;
        preferences = mContext.getSharedPreferences(Config.PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }
    public String getPassword() {
        return preferences.getString(Keys.KEY_USER_PASSWORD, null);
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Keys.KEY_USER_PASSWORD, password);
        editor.commit();
    }
    public String getUserID() {
        return preferences.getString(Keys.KEY_USERID, null);
    }

    public void setUserID(String userID) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Keys.KEY_USERID, userID);
        editor.commit();
    }
    public boolean getLoginDone() {
        return preferences.getBoolean(Keys.KEY_IS_LOGIN_DONE, false);
    }

    public void setloginDone(boolean isDone) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Keys.KEY_IS_LOGIN_DONE, isDone);
        editor.commit();
    }

    private interface Config {
        String PREFERENCE_NAME = "DeploymentMessenger";
    }
    private interface Keys extends AppPreference.Config {
        String KEY_USERID = "_userId";
        String KEY_USER_PASSWORD = "_password";
        String KEY_IS_LOGIN_DONE = "false";
    }
}
