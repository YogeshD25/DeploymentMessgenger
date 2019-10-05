package com.neml.deploymentaapproval.Model;

public class ModelNotificationListItem {
    String application;
    String releaseType;
    String version;
    String releaseInfo;
    public ModelNotificationListItem(String application,String releaseType, String version, String releaseInfo){
        this.application = application;
        this.releaseType = releaseType;
        this.version = version;
        this.releaseInfo = releaseInfo;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(String releaseType) {
        this.releaseType = releaseType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReleaseInfo() {
        return releaseInfo;
    }

    public void setReleaseInfo(String releaseInfo) {
        this.releaseInfo = releaseInfo;
    }
}
