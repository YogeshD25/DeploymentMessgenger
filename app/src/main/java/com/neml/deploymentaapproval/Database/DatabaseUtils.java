package com.neml.deploymentaapproval.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.neml.deploymentaapproval.Logger.Logg;
import com.neml.deploymentaapproval.Model.ModelDisplayDetails;
import com.neml.deploymentaapproval.Model.ModelRegistration;

public class DatabaseUtils {

    public static boolean isExists(SQLiteDatabase database, String table, String columnName, String value) {
        boolean isExists = false;
        Cursor cursor = database.query(table, null, columnName + "=?", new String[]{value}, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                isExists = true;
            }
        }
        cursor.close();
        return isExists;
    }
    public static void insertOrUpdateUser(Context context, ModelRegistration entryItem) {
        try {
            SQLiteDatabase database = DatabaseOpenHelper.getInstance(context);
            ContentValues values = new ContentValues();
            values.put(DatabaseTableHelper.UserCredentials.USER_ID, entryItem.getUserId());
            values.put(DatabaseTableHelper.UserCredentials.PASSWORD, entryItem.getUserPassowrd());

            boolean isExists = isExists(database, DatabaseTableHelper.UserCredentials.TABLE_NAME,
                    DatabaseTableHelper.UserCredentials.USER_ID, entryItem.getUserId());

            if (isExists) {
                database.update(DatabaseTableHelper.UserCredentials.TABLE_NAME, values,
                        DatabaseTableHelper.UserCredentials.USER_ID + "=? ",
                        new String[]{entryItem.getUserId()});
            } else {
                database.insert(DatabaseTableHelper.UserCredentials.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
            Logg.d(e.toString());
        }
    }

    private static void insertOrUpdateDeploymentData(Context context, ModelDisplayDetails l_comm, SQLiteDatabase database) {
        try {
            AppPreference pref = new AppPreference(context);
            ContentValues values = new ContentValues();

            values.put(DatabaseTableHelper.DeploymentInfo.DEPLOYEMENTNO, l_comm.getDeploymentNo());
            values.put(DatabaseTableHelper.DeploymentInfo.RFCSEQNO, l_comm.getRfcSeqNo());
            values.put(DatabaseTableHelper.DeploymentInfo.REQUESTERNAME, l_comm.getRequesterName());
            values.put(DatabaseTableHelper.DeploymentInfo.REQUESTERMANAGER, l_comm.getRequesterManager());
            values.put(DatabaseTableHelper.DeploymentInfo.APPLICATIONURL, l_comm.getApplicationUrl());
            values.put(DatabaseTableHelper.DeploymentInfo.CREATED_DATE, l_comm.getCreatedDate());
            values.put(DatabaseTableHelper.DeploymentInfo.UATAPPROVALNAME, l_comm.getUatApprovarName());
            values.put(DatabaseTableHelper.DeploymentInfo.PROJECTNAME, l_comm.getProjectName());
            values.put(DatabaseTableHelper.DeploymentInfo.DEPLOYMENTTYPE, l_comm.getDeploymentType());
            values.put(DatabaseTableHelper.DeploymentInfo.SRNNO, l_comm.getSrnNo());
            values.put(DatabaseTableHelper.DeploymentInfo.VERSIONNO, l_comm.getVersionNo());
            values.put(DatabaseTableHelper.DeploymentInfo.APPROVEREJECT, l_comm.getApproveReject());
            values.put(DatabaseTableHelper.DeploymentInfo.USERID, pref.getUserID());

            boolean isExists = isExists(database, DatabaseTableHelper.DeploymentInfo.TABLE_NAME,
                    DatabaseTableHelper.DeploymentInfo.DEPLOYEMENTNO, l_comm.getDeploymentNo());

            if (isExists) {
                database.update(DatabaseTableHelper.DeploymentInfo.TABLE_NAME, values,
                        DatabaseTableHelper.DeploymentInfo.DEPLOYEMENTNO + "=? ",
                        new String[]{l_comm.getDeploymentNo()});

            } else {
                database.insert(DatabaseTableHelper.DeploymentInfo.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
            Logg.d(e.toString());
        }

    }



}
