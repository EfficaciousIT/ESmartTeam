package com.mobi.efficacious.esmartteam.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Requisition {

    @SerializedName("vchName")
    @Expose
    private String vchName;
    @SerializedName("IntRequisitionTypeId")
    @Expose
    private Integer intRequisitionTypeId;
    @SerializedName("VchRequisition")
    @Expose
    private String vchRequisition;
    @SerializedName("VchAddress")
    @Expose
    private String vchAddress;
    @SerializedName("DtTargetDate")
    @Expose
    private String dtTargetDate;
    @SerializedName("VchMobile")
    @Expose
    private String vchMobile;
    @SerializedName("VchRemark")
    @Expose
    private String vchRemark;
    @SerializedName("vchstatus")
    @Expose
    private String vchstatus;
    @SerializedName("IntOrgId")
    @Expose
    private Integer intOrgId;

    public Requisition(String vchName, Integer intRequisitionTypeId, String vchRequisition, String vchAddress, String dtTargetDate, String vchMobile, String vchRemark, Integer intOrgId) {
        this.vchName = vchName;
        this.intRequisitionTypeId = intRequisitionTypeId;
        this.vchRequisition = vchRequisition;
        this.vchAddress = vchAddress;
        this.dtTargetDate = dtTargetDate;
        this.vchMobile = vchMobile;
        this.vchRemark = vchRemark;
        this.intOrgId = intOrgId;
    }

    public String getVchName() {
        return vchName;
    }

    public void setVchName(String vchName) {
        this.vchName = vchName;
    }

    public Integer getIntRequisitionTypeId() {
        return intRequisitionTypeId;
    }

    public void setIntRequisitionTypeId(Integer intRequisitionTypeId) {
        this.intRequisitionTypeId = intRequisitionTypeId;
    }

    public String getVchRequisition() {
        return vchRequisition;
    }

    public void setVchRequisition(String vchRequisition) {
        this.vchRequisition = vchRequisition;
    }

    public String getVchAddress() {
        return vchAddress;
    }

    public void setVchAddress(String vchAddress) {
        this.vchAddress = vchAddress;
    }

    public String getDtTargetDate() {
        return dtTargetDate;
    }

    public void setDtTargetDate(String dtTargetDate) {
        this.dtTargetDate = dtTargetDate;
    }

    public String getVchMobile() {
        return vchMobile;
    }

    public void setVchMobile(String vchMobile) {
        this.vchMobile = vchMobile;
    }

    public String getVchRemark() {
        return vchRemark;
    }

    public void setVchRemark(String vchRemark) {
        this.vchRemark = vchRemark;
    }

    public String getVchstatus() {
        return vchstatus;
    }

    public void setVchstatus(String vchstatus) {
        this.vchstatus = vchstatus;
    }

    public Integer getIntOrgId() {
        return intOrgId;
    }

    public void setIntOrgId(Integer intOrgId) {
        this.intOrgId = intOrgId;
    }

}