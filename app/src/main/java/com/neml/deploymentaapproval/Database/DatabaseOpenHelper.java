package com.neml.deploymentaapproval.Database;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static DatabaseOpenHelper _instance;
    private static SQLiteDatabase _database;
    private static final String DATABASE_NAME = "UMPePermit.db3";
    private static final int DATABASE_VERSION_2 = 2;
    private DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION_2);
    }

    public static SQLiteDatabase getInstance(Context context) {
        if (_instance == null) {
            _instance = new DatabaseOpenHelper(context);
        }
        if (_database == null) {
            _database = _instance.getWritableDatabase();
        }
        return _database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create User Table
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DatabaseTableHelper.UserCredentials.TABLE_NAME
                + " ( " + DatabaseTableHelper.UserCredentials.USER_ID + " TEXT,"
                + DatabaseTableHelper.UserCredentials.PASSWORD + " TEXT"
                + ")");


        //Create Deployment Details table
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DatabaseTableHelper.DeploymentInfo.TABLE_NAME
                + " ( " + DatabaseTableHelper.DeploymentInfo.DEPLOYEMENTNO + " TEXT,"
                + DatabaseTableHelper.DeploymentInfo.RFCSEQNO + " TEXT,"
                + DatabaseTableHelper.DeploymentInfo.REQUESTERNAME + " TEXT,"
                + DatabaseTableHelper.DeploymentInfo.REQUESTERMANAGER + " TEXT,"
                + DatabaseTableHelper.DeploymentInfo.APPLICATIONURL + " TEXT,"
                + DatabaseTableHelper.DeploymentInfo.CREATED_DATE + " TEXT,"
                + DatabaseTableHelper.DeploymentInfo.UATAPPROVALNAME + " TEXT,"
                + DatabaseTableHelper.DeploymentInfo.PROJECTNAME + " TEXT,"
                + DatabaseTableHelper.DeploymentInfo.DEPLOYMENTTYPE + " TEXT,"
                + DatabaseTableHelper.DeploymentInfo.SRNNO + " TEXT,"
                + DatabaseTableHelper.DeploymentInfo.VERSIONNO + " TEXT,"
                + DatabaseTableHelper.DeploymentInfo.USERID + "TEXT,"
                + DatabaseTableHelper.DeploymentInfo.APPROVEREJECT + " TEXT "
                + ")"
        );



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case DATABASE_VERSION_2:
                runVersion3UpdateScript(sqLiteDatabase);
    }
}
    private void runVersion3UpdateScript(SQLiteDatabase sqLiteDatabase) {

    }
    }


