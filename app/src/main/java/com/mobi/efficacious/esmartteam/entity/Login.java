package com.mobi.efficacious.esmartteam.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("intEmployee_id")
    @Expose
    private Integer intEmployeeId;
    @SerializedName("intTeam_id")
    @Expose
    private Integer intTeamId;
    @SerializedName("intOrg_id")
    @Expose
    private Integer intOrgId;
    @SerializedName("vchEmail")
    @Expose
    private String vchEmail;
    @SerializedName("vchMobileNo")
    @Expose
    private String vchMobileNo;
    @SerializedName("vchDeviceAssigned_user")
    @Expose
    private String vchDeviceAssignedUser;
    @SerializedName("emergencyConOffice")
    @Expose
    private String emergencyConOffice;
    @SerializedName("emergencyConPersonal")
    @Expose
    private String emergencyConPersonal;

    public Integer getIntEmployeeId() {
        return intEmployeeId;
    }

    public void setIntEmployeeId(Integer intEmployeeId) {
        this.intEmployeeId = intEmployeeId;
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

    public String getVchEmail() {
        return vchEmail;
    }

    public void setVchEmail(String vchEmail) {
        this.vchEmail = vchEmail;
    }

    public String getVchMobileNo() {
        return vchMobileNo;
    }

    public void setVchMobileNo(String vchMobileNo) {
        this.vchMobileNo = vchMobileNo;
    }

    public String getVchDeviceAssignedUser() {
        return vchDeviceAssignedUser;
    }

    public void setVchDeviceAssignedUser(String vchDeviceAssignedUser) {
        this.vchDeviceAssignedUser = vchDeviceAssignedUser;
    }

    public String getEmergencyConOffice() {
        return emergencyConOffice;
    }

    public void setEmergencyConOffice(String emergencyConOffice) {
        this.emergencyConOffice = emergencyConOffice;
    }

    public String getEmergencyConPersonal() {
        return emergencyConPersonal;
    }

    public void setEmergencyConPersonal(String emergencyConPersonal) {
        this.emergencyConPersonal = emergencyConPersonal;
    }

}