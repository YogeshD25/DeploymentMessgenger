package com.neml.deploymentaapproval.Utils;

public class Constants {


    public interface UrlLinks{
        public static String login = "https://deploymenttracker.neml.xyz/NeMLDeploymentTracker/login/validateUserAndroid";
        public static String signIn = "";
        public static String details = "https://deploymenttracker.neml.xyz/NeMLDeploymentTracker/DeploymentCtrl/getDeploymentDetailsAndroid";
        public static String approve = "https://deploymenttracker.neml.xyz/NeMLDeploymentTracker/DeploymentCtrl/approveDeployment";
        public static String reject = "";
        public static String sendToken = "https://deploymenttracker.neml.xyz/NeMLDeploymentTracker/DeploymentCtrl/updateToken";

    }
    public interface postAttributeName{
        public static String deploymentNo = "deploymentNo";
        public static String userId = "userId";
        public static String fcmToken = "fcmToken";
        public static String password = "userPassword";
        public static String mantisID = "mantisId";
    }
    public interface fcmMessageKey{
        public static String deploymentNo = "deploymentNo";
        public static String versionNo = "versionNo";
        public static String projectName = "projectName";
    }
}
