package com.mobi.efficacious.esmartteam.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskList {

    @SerializedName("dtSuspendedDate")
    @Expose
    private String dtSuspendedDate;
    @SerializedName("IntRequisitionId")
    @Expose
    private Integer intRequisitionIid;
    @SerializedName("IntEmployeeId")
    @Expose
    private Integer intEmployeeId;
    @SerializedName("IntTeamId")
    @Expose
    private Integer intTeamId;
    @SerializedName("intRequisition_id")
    @Expose
    private Integer intRequisitionId;
    @SerializedName("intRequisitionType_id")
    @Expose
    private Integer intRequisitionTypeId;
    @SerializedName("intOrg_id")
    @Expose
    private Integer intOrgId;
    @SerializedName("vchName")
    @Expose
    private String vchName;
    @SerializedName("vchMobile")
    @Expose
    private String vchMobile;
    @SerializedName("vchAddress")
    @Expose
    private String vchAddress;
    @SerializedName("vchRequisition")
    @Expose
    private String vchRequisition;
    @SerializedName("dtTargetDate")
    @Expose
    private String dtTargetDate;
    @SerializedName("vchRemark")
    @Expose
    private String vchRemark;
    @SerializedName("vchStatus")
    @Expose
    private String vchStatus;

    public TaskList(Integer intRequisitionId, Integer intEmployeeId, String vchStatus,  String vchName,Integer intTeamId, String vchRemark) {
        this.intTeamId = intTeamId;
        this.intEmployeeId = intEmployeeId;
        this.intRequisitionIid = intRequisitionId;
        this.vchName = vchName;
        this.vchRemark = vchRemark;
        this.vchStatus = vchStatus;
    }

    public TaskList(Integer intRequisitionId, Integer intEmployeeId, String vchName,Integer intTeamId, String vchRemark, String dtSuspendedDate) {
        this.intTeamId = intTeamId;
        this.intEmployeeId = intEmployeeId;
        this.intRequisitionIid = intRequisitionId;
        this.vchName = vchName;
        this.vchRemark = vchRemark;
        this.dtSuspendedDate = dtSuspendedDate;
    }

    public Integer getIntRequisitionId() {
        return intRequisitionId;
    }

    public void setIntRequisitionId(Integer intRequisitionId) {
        this.intRequisitionId = intRequisitionId;
    }

    public Integer getIntRequisitionTypeId() {
        return intRequisitionTypeId;
    }

    public void setIntRequisitionTypeId(Integer intRequisitionTypeId) {
        this.intRequisitionTypeId = intRequisitionTypeId;
    }

    public Integer getIntOrgId() {
        return intOrgId;
    }

    public void setIntOrgId(Integer intOrgId) {
        this.intOrgId = intOrgId;
    }

    public String getVchName() {
        return vchName;
    }

    public void setVchName(String vchName) {
        this.vchName = vchName;
    }

    public String getVchMobile() {
        return vchMobile;
    }

    public void setVchMobile(String vchMobile) {
        this.vchMobile = vchMobile;
    }

    public String getVchAddress() {
        return vchAddress;
    }

    public void setVchAddress(String vchAddress) {
        this.vchAddress = vchAddress;
    }

    public String getVchRequisition() {
        return vchRequisition;
    }

    public void setVchRequisition(String vchRequisition) {
        this.vchRequisition = vchRequisition;
    }

    public String getDtTargetDate() {
        return dtTargetDate;
    }

    public void setDtTargetDate(String dtTargetDate) {
        this.dtTargetDate = dtTargetDate;
    }

    public String getVchRemark() {
        return vchRemark;
    }

    public void setVchRemark(String vchRemark) {
        this.vchRemark = vchRemark;
    }

    public String getVchStatus() {
        return vchStatus;
    }

    public void setVchStatus(String vchStatus) {
        this.vchStatus = vchStatus;
    }




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

    public Integer getIntRequisitionIid() {
        return intRequisitionIid;
    }

    public void setIntRequisitionIid(Integer intRequisitionIid) {
        this.intRequisitionIid = intRequisitionIid;
    }
    public String getDtSuspendedDate() {
        return dtSuspendedDate;
    }

    public void setDtSuspendedDate(String dtSuspendedDate) {
        this.dtSuspendedDate = dtSuspendedDate;
    }
}
