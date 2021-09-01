package com.mobi.efficacious.esmartteam.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequisitionType {

    @SerializedName("intRequisitionType_id")
    @Expose
    private Integer intRequisitionTypeId;
    @SerializedName("vchRequisitionType")
    @Expose
    private String vchRequisitionType;
    @SerializedName("intOrg_id")
    @Expose
    private Integer intOrgId;

    public RequisitionType(Integer intRequisitionTypeId, String vchRequisitionType, Integer intOrgId) {
        this.intRequisitionTypeId = intRequisitionTypeId;
        this.vchRequisitionType = vchRequisitionType;
        this.intOrgId = intOrgId;
    }

    public Integer getIntRequisitionTypeId() {
        return intRequisitionTypeId;
    }

    public void setIntRequisitionTypeId(Integer intRequisitionTypeId) {
        this.intRequisitionTypeId = intRequisitionTypeId;
    }

    public String getVchRequisitionType() {
        return vchRequisitionType;
    }

    public void setVchRequisitionType(String vchRequisitionType) {
        this.vchRequisitionType = vchRequisitionType;
    }

    public Integer getIntOrgId() {
        return intOrgId;
    }

    public void setIntOrgId(Integer intOrgId) {
        this.intOrgId = intOrgId;
    }

}