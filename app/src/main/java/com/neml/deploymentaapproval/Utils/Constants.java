package com.neml.deploymentaapproval.Utils;

public class Constants {


    public interface UrlLinks{
        public static String login = "http://172.22.22.12:8080/NeMLDeploymentTracker/login/validateUserAndroid";
        public static String signIn = "";
        public static String details = "http://172.22.22.12:8080/NeMLDeploymentTracker/DeploymentCtrl/getDeploymentDetailsAndroid";
        public static String approve = "http://172.22.22.12:8080/NeMLDeploymentTracker/DeploymentCtrl/approveDeployment ";
        public static String reject = "";
        public static String sendToken = "http://172.22.22.12:8080/NeMLDeploymentTracker/DeploymentCtrl/updateToken";

    }
    public interface postAttributeName{
        public static String deploymentNo = "deploymentNo";
        public static String userId = "userId";
        public static String fcmToken = "fcmToken";
        public static String password = "userPassword";
    }
    public interface fcmMessageKey{
        public static String deploymentNo = "deploymentNo";
        public static String versionNo = "versionNo";
        public static String projectName = "projectName";
    }
}
