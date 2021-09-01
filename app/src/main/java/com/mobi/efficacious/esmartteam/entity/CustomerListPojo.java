package com.mobi.efficacious.esmartteam.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerListPojo {

    @SerializedName("CustomerList")
    @Expose
    private List<CustomerList> customerList = null;

    public List<CustomerList> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerList> customerList) {
        this.customerList = customerList;
    }

}
