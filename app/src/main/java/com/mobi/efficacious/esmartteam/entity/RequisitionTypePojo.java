package com.mobi.efficacious.esmartteam.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequisitionTypePojo {

    @SerializedName("RequisitionType")
    @Expose
    private List<RequisitionType> requisitionType = null;

    public List<RequisitionType> getRequisitionType() {
        return requisitionType;
    }

    public void setRequisitionType(List<RequisitionType> requisitionType) {
        this.requisitionType = requisitionType;
    }

}