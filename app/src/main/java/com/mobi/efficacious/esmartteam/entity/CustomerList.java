package com.mobi.efficacious.esmartteam.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerList {

    @SerializedName("CustId")
    @Expose
    private Integer custid;
    @SerializedName("cust_Id")
    @Expose
    private Integer custId;
    @SerializedName("CustName")
    @Expose
    private String custName;
    @SerializedName("CustMobile")
    @Expose
    private String custMobile;
    @SerializedName("CustEmail")
    @Expose
    private String custEmail;
    @SerializedName("CustAddress")
    @Expose
    private String custAddress;
    @SerializedName("CustLandmark")
    @Expose
    private String custLandmark;
    @SerializedName("CustGender")
    @Expose
    private Object custGender;
    @SerializedName("CustNationaliy")
    @Expose
    private String custNationaliy;
    @SerializedName("CustDOB")
    @Expose
    private Object custDOB;
    @SerializedName("IntOrgId")
    @Expose
    private String intOrgid;
    @SerializedName("intOrg_id")
    @Expose
    private String intOrgId;
    @SerializedName("ActiveFlg")
    @Expose
    private String activeFlg;
    @SerializedName("intInserted_by")
    @Expose
    private Object intInsertedBy;
    @SerializedName("Inserted_date")
    @Expose
    private Object insertedDate;
    @SerializedName("InseretIP")
    @Expose
    private Object inseretIP;
    @SerializedName("IntUpdate_by")
    @Expose
    private Object intUpdateBy;
    @SerializedName("Updated_date")
    @Expose
    private Object updatedDate;
    @SerializedName("UpdateIP")
    @Expose
    private Object updateIP;
    @SerializedName("intDelete_by")
    @Expose
    private Object intDeleteBy;
    @SerializedName("Deleted_date")
    @Expose
    private Object deletedDate;
    @SerializedName("DeleteIP")
    @Expose
    private Object deleteIP;

    public CustomerList(String custName, String custMobile, String custEmail, String custAddress, String custLandmark, String custNationaliy,String ORGID) {
        this.custName = custName;
        this.custMobile = custMobile;
        this.custEmail = custEmail;
        this.custAddress = custAddress;
        this.custLandmark = custLandmark;
        this.custNationaliy = custNationaliy;
        this.intOrgid=ORGID;
    }
    public CustomerList(String custName, String custMobile, String custEmail, String custAddress, String custLandmark, String custNationaliy,String ORGID,int CustId) {
        this.custName = custName;
        this.custMobile = custMobile;
        this.custEmail = custEmail;
        this.custAddress = custAddress;
        this.custLandmark = custLandmark;
        this.custNationaliy = custNationaliy;
        this.intOrgid=ORGID;
        this.custid=CustId;
    }

    public CustomerList(Integer custId, String custName, String custMobile, String custAddress, String intOrgId) {
        this.custId = custId;
        this.custName = custName;
        this.custMobile = custMobile;
        this.custAddress = custAddress;
        this.intOrgId = intOrgId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustLandmark() {
        return custLandmark;
    }

    public void setCustLandmark(String custLandmark) {
        this.custLandmark = custLandmark;
    }

    public Object getCustGender() {
        return custGender;
    }

    public void setCustGender(Object custGender) {
        this.custGender = custGender;
    }

    public String getCustNationaliy() {
        return custNationaliy;
    }

    public void setCustNationaliy(String custNationaliy) {
        this.custNationaliy = custNationaliy;
    }

    public Object getCustDOB() {
        return custDOB;
    }

    public void setCustDOB(Object custDOB) {
        this.custDOB = custDOB;
    }

    public String getIntOrgId() {
        return intOrgId;
    }

    public void setIntOrgId(String intOrgId) {
        this.intOrgId = intOrgId;
    }

    public String getActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(String activeFlg) {
        this.activeFlg = activeFlg;
    }

    public Object getIntInsertedBy() {
        return intInsertedBy;
    }

    public void setIntInsertedBy(Object intInsertedBy) {
        this.intInsertedBy = intInsertedBy;
    }

    public Object getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(Object insertedDate) {
        this.insertedDate = insertedDate;
    }

    public Object getInseretIP() {
        return inseretIP;
    }

    public void setInseretIP(Object inseretIP) {
        this.inseretIP = inseretIP;
    }

    public Object getIntUpdateBy() {
        return intUpdateBy;
    }

    public void setIntUpdateBy(Object intUpdateBy) {
        this.intUpdateBy = intUpdateBy;
    }

    public Object getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Object updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Object getUpdateIP() {
        return updateIP;
    }

    public void setUpdateIP(Object updateIP) {
        this.updateIP = updateIP;
    }

    public Object getIntDeleteBy() {
        return intDeleteBy;
    }

    public void setIntDeleteBy(Object intDeleteBy) {
        this.intDeleteBy = intDeleteBy;
    }

    public Object getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Object deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Object getDeleteIP() {
        return deleteIP;
    }

    public void setDeleteIP(Object deleteIP) {
        this.deleteIP = deleteIP;
    }

    public String getIntOrgid() {
        return intOrgid;
    }

    public void setIntOrgid(String intOrgid) {
        this.intOrgid = intOrgid;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }
}
