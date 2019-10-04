package com.neml.deploymentaapproval.Model;

public class ModelDisplayDetails {
    public ModelDisplayDetails(String deploymentNo, String rfcSeqNo, String requesterName, String requesterManager,
                         String applicationUrl, String createdDate, String uatApprovarName, String projectName,
                         String deploymentType, String srnNo, String versionNo) {
        this.deploymentNo = deploymentNo;
        this.rfcSeqNo = rfcSeqNo;
        this.requesterName = requesterName;
        this.requesterManager = requesterManager;
        this.applicationUrl = applicationUrl;
        this.createdDate = createdDate;
        this.uatApprovarName = uatApprovarName;
        this.projectName = projectName;
        this.deploymentType = deploymentType;
        this.srnNo = srnNo;
        this.versionNo = versionNo;

    }

    private String deploymentNo;

    public String getDeploymentNo() {
        return deploymentNo;
    }

    public void setDeploymentNo(String deploymentNo) {
        this.deploymentNo = deploymentNo;
    }

    public String getRfcSeqNo() {
        return rfcSeqNo;
    }

    public void setRfcSeqNo(String rfcSeqNo) {
        this.rfcSeqNo = rfcSeqNo;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getRequesterManager() {
        return requesterManager;
    }

    public void setRequesterManager(String requesterManager) {
        this.requesterManager = requesterManager;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUatApprovarName() {
        return uatApprovarName;
    }

    public void setUatApprovarName(String uatApprovarName) {
        this.uatApprovarName = uatApprovarName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDeploymentType() {
        return deploymentType;
    }

    public void setDeploymentType(String deploymentType) {
        this.deploymentType = deploymentType;
    }

    public String getSrnNo() {
        return srnNo;
    }

    public void setSrnNo(String srnNo) {
        this.srnNo = srnNo;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getApproveReject() {
        return approveReject;
    }

    public void setApproveReject(String approveReject) {
        this.approveReject = approveReject;
    }

    private String rfcSeqNo;
    private String requesterName;
    private String requesterManager;
    private String applicationUrl;
    private String createdDate;
    private String uatApprovarName;
    private String projectName;
    private String deploymentType;
    private String srnNo;
    private String versionNo;
    private String approveReject;
}
