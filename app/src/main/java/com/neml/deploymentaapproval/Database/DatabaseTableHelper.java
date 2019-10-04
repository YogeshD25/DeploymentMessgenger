package com.neml.deploymentaapproval.Database;

public class DatabaseTableHelper {

    public interface UserCredentials {
        String TABLE_NAME = "USER_CREDENTAILS";
        String USER_ID = "USERID";
        String PASSWORD = "PASSWORD";
    }
    public interface DeploymentInfo {
        String TABLE_NAME = "DEPLOYMENT_INFO";
        String DEPLOYEMENTNO = "DEPLOYMENT_NO";
        String RFCSEQNO = "RFC_SEQ_NO";
        String REQUESTERNAME = "REQUESTER_NAME";
        String REQUESTERMANAGER = "REQUESTER_NAME";
        String APPLICATIONURL = "APPLICATION_URL";
        String CREATED_DATE = "CREATED_DATE";
        String UATAPPROVALNAME = "UAT_APPROVAL_NAME";
        String PROJECTNAME = "PROJECT_NAME";
        String DEPLOYMENTTYPE = "DEPLOYMENT_TYPE";
        String SRNNO = "SRN_NO";
        String VERSIONNO = "VERSION_NO";
        String APPROVEREJECT = "APPROVAL_REJECT";
        String USERID = "USER_ID";
    }
}
