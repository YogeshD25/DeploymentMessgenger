
package com.neml.deploymentaapproval.Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelNotificationList implements Serializable {
    public ModelNotificationList(){
        super();
    }
    public ModelNotificationList(String DeploymentNo){
        this.deploymentNo = DeploymentNo;
    }


    @SerializedName("projectName")
    @Expose
    private String  projectName;
    @SerializedName("projectCode")
    @Expose
    private String projectCode;
    @SerializedName("rfcSeqNo")
    @Expose
    private String rfcSeqNo;
    @SerializedName("checkListNo")
    @Expose
    private String checkListNo;
    @SerializedName("deploymentType")
    @Expose
    private String deploymentType;
    @SerializedName("srnNo")
    @Expose
    private String srnNo;
    @SerializedName("versionNo")
    @Expose
    private String versionNo;
    @SerializedName("fromVersion")
    @Expose
    private String fromVersion;
    @SerializedName("toVersion")
    @Expose
    private String toVersion;
    @SerializedName("preparedBy")
    @Expose
    private String preparedBy;
    @SerializedName("reviewBy")
    @Expose
    private String reviewBy;
    @SerializedName("approvedBy")
    @Expose
    private String approvedBy;
    @SerializedName("uatApprovar")
    @Expose
    private String uatApprovar;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("rfcNo")
    @Expose
    private Object rfcNo;
    @SerializedName("isCyleDone")
    @Expose
    private Object isCyleDone;
    @SerializedName("deploymentNo")
    @Expose
    private String deploymentNo;
    @SerializedName("scriptFilePath")
    @Expose
    private Object scriptFilePath;
    @SerializedName("teamLeadName")
    @Expose
    private Object teamLeadName;
    @SerializedName("jarFileSource")
    @Expose
    private Object jarFileSource;
    @SerializedName("warFileSource")
    @Expose
    private Object warFileSource;
    @SerializedName("jarFileDestination")
    @Expose
    private Object jarFileDestination;
    @SerializedName("warFileDestination")
    @Expose
    private Object warFileDestination;
    @SerializedName("primaryServer")
    @Expose
    private Object primaryServer;
    @SerializedName("projectUrl")
    @Expose
    private String projectUrl;
    @SerializedName("serverIp")
    @Expose
    private Object serverIp;
    @SerializedName("descList")
    @Expose
    private List<Object> descList = null;
    @SerializedName("bugList")
    @Expose
    private List<Object> bugList = null;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getRfcSeqNo() {
        return rfcSeqNo;
    }

    public void setRfcSeqNo(String rfcSeqNo) {
        this.rfcSeqNo = rfcSeqNo;
    }

    public String getCheckListNo() {
        return checkListNo;
    }

    public void setCheckListNo(String checkListNo) {
        this.checkListNo = checkListNo;
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

    public String getFromVersion() {
        return fromVersion;
    }

    public void setFromVersion(String fromVersion) {
        this.fromVersion = fromVersion;
    }

    public String getToVersion() {
        return toVersion;
    }

    public void setToVersion(String toVersion) {
        this.toVersion = toVersion;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getReviewBy() {
        return reviewBy;
    }

    public void setReviewBy(String reviewBy) {
        this.reviewBy = reviewBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getUatApprovar() {
        return uatApprovar;
    }

    public void setUatApprovar(String uatApprovar) {
        this.uatApprovar = uatApprovar;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getRfcNo() {
        return rfcNo;
    }

    public void setRfcNo(Object rfcNo) {
        this.rfcNo = rfcNo;
    }

    public Object getIsCyleDone() {
        return isCyleDone;
    }

    public void setIsCyleDone(Object isCyleDone) {
        this.isCyleDone = isCyleDone;
    }

    public String getDeploymentNo() {
        return deploymentNo;
    }

    public void setDeploymentNo(String deploymentNo) {
        this.deploymentNo = deploymentNo;
    }

    public Object getScriptFilePath() {
        return scriptFilePath;
    }

    public void setScriptFilePath(Object scriptFilePath) {
        this.scriptFilePath = scriptFilePath;
    }

    public Object getTeamLeadName() {
        return teamLeadName;
    }

    public void setTeamLeadName(Object teamLeadName) {
        this.teamLeadName = teamLeadName;
    }

    public Object getJarFileSource() {
        return jarFileSource;
    }

    public void setJarFileSource(Object jarFileSource) {
        this.jarFileSource = jarFileSource;
    }

    public Object getWarFileSource() {
        return warFileSource;
    }

    public void setWarFileSource(Object warFileSource) {
        this.warFileSource = warFileSource;
    }

    public Object getJarFileDestination() {
        return jarFileDestination;
    }

    public void setJarFileDestination(Object jarFileDestination) {
        this.jarFileDestination = jarFileDestination;
    }

    public Object getWarFileDestination() {
        return warFileDestination;
    }

    public void setWarFileDestination(Object warFileDestination) {
        this.warFileDestination = warFileDestination;
    }

    public Object getPrimaryServer() {
        return primaryServer;
    }

    public void setPrimaryServer(Object primaryServer) {
        this.primaryServer = primaryServer;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public Object getServerIp() {
        return serverIp;
    }

    public void setServerIp(Object serverIp) {
        this.serverIp = serverIp;
    }

    public List<Object> getDescList() {
        return descList;
    }

    public void setDescList(List<Object> descList) {
        this.descList = descList;
    }

    public List<Object> getBugList() {
        return bugList;
    }

    public void setBugList(List<Object> bugList) {
        this.bugList = bugList;
    }

}
