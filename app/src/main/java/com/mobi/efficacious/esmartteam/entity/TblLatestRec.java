package com.mobi.efficacious.esmartteam.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblLatestRec {

    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;
    @SerializedName("DtdateTime")
    @Expose
    private String dtdateTime;
    @SerializedName("VchImeiNo")
    @Expose
    private String vchImeiNo;
    @SerializedName("IntTeamId")
    @Expose
    private Integer intTeamId;
    @SerializedName("IntOrgId")
    @Expose
    private Integer intOrgId;
    @SerializedName("Gcmkey")
    @Expose
    private String gCMKey;
    @SerializedName("IntEmployeeId")
    @Expose
    private Integer intEmployeeId;
    @SerializedName("DtUploadDate")
    @Expose
    private String dtUploadDate;
    @SerializedName("VchDeviceAssignedUser")
    @Expose
    private String vchDeviceAssignedUser;
    @SerializedName("VchLoggedUser")
    @Expose
    private String vchLoggedUser;

    public TblLatestRec(Double latitude, Double longitude, Integer intTeamId, Integer intOrgId, Integer intEmployeeId, String vchDeviceAssignedUser, String vchLoggedUser) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.intTeamId = intTeamId;
        this.intOrgId = intOrgId;
        this.intEmployeeId = intEmployeeId;
        this.vchDeviceAssignedUser = vchDeviceAssignedUser;
        this.vchLoggedUser = vchLoggedUser;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDtdateTime() {
        return dtdateTime;
    }

    public void setDtdateTime(String dtdateTime) {
        this.dtdateTime = dtdateTime;
    }

    public String getVchImeiNo() {
        return vchImeiNo;
    }

    public void setVchImeiNo(String vchImeiNo) {
        this.vchImeiNo = vchImeiNo;
    }

    public Integer getIntTeamId() {
        return intTeamId;
    }

    public void setIntTeamId(Integer intTeamId) {
        this.intTeamId = intTeamId;
    }

    public Integer getIntOrgId() {
        return intOrgId;
    }

    public void setIntOrgId(Integer intOrgId) {
        this.intOrgId = intOrgId;
    }

    public String getGCMKey() {
        return gCMKey;
    }

    public void setGCMKey(String gCMKey) {
        this.gCMKey = gCMKey;
    }

    public Integer getIntEmployeeId() {
        return intEmployeeId;
    }

    public void setIntEmployeeId(Integer intEmployeeId) {
        this.intEmployeeId = intEmployeeId;
    }

    public String getDtUploadDate() {
        return dtUploadDate;
    }

    public void setDtUploadDate(String dtUploadDate) {
        this.dtUploadDate = dtUploadDate;
    }

    public String getVchDeviceAssignedUser() {
        return vchDeviceAssignedUser;
    }

    public void setVchDeviceAssignedUser(String vchDeviceAssignedUser) {
        this.vchDeviceAssignedUser = vchDeviceAssignedUser;
    }

    public String getVchLoggedUser() {
        return vchLoggedUser;
    }

    public void setVchLoggedUser(String vchLoggedUser) {
        this.vchLoggedUser = vchLoggedUser;
    }

}