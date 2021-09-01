package com.mobi.efficacious.esmartteam.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobi.efficacious.esmartteam.Fragment.UpdateCustomer;
import com.mobi.efficacious.esmartteam.R;
import com.mobi.efficacious.esmartteam.activity.MainActivity;
import com.mobi.efficacious.esmartteam.entity.CustomerList;

import java.util.ArrayList;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder> {

    private ArrayList<CustomerList> dataList;
    Context mcontext;
    public CustomerListAdapter(ArrayList<CustomerList> dataList, Context context) {
        this.dataList = dataList;
        this.mcontext=context;
    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.customer_list_adapter, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder holder, final int position) {
        try {
            holder.txtcustName.setText(dataList.get(position).getCustName());

            holder.txtCustPhone.setText(dataList.get(position).getCustMobile());
        }catch (Exception ex)
        {

        }

        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String Id= String.valueOf(dataList.get(position).getCustid());
                    UpdateCustomer updateCustomer = new UpdateCustomer();
                    Bundle b = new Bundle();
                    b.putString("Cust_Id", String.valueOf(dataList.get(position).getCustId()));
                    b.putString("Cust_Name", String.valueOf(dataList.get(position).getCustName()));
                    b.putString("Cust_MobileNo", String.valueOf(dataList.get(position).getCustMobile()));
                    b.putString("Cust_Email", String.valueOf(dataList.get(position).getCustEmail()));
                    b.putString("Cust_Address", String.valueOf(dataList.get(position).getCustAddress()));
                    b.putString("Cust_Landmark", String.valueOf(dataList.get(position).getCustLandmark()));
                    b.putString("Cust_Nationality", String.valueOf(dataList.get(position).getCustNationaliy()));

                    updateCustomer.setArguments(b);
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.content_main,updateCustomer).addToBackStack(null).commitAllowingStateLoss();

                }catch (Exception ex)
                {

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder {

        TextView txtcustName, txtCustPhone;
        LinearLayout linear;
        CustomerViewHolder(View itemView) {
            super(itemView);
            txtcustName = (TextView) itemView.findViewById(R.id.txt_Customer_name);
            txtCustPhone = (TextView) itemView.findViewById(R.id.txt_customer_phone);
            linear=(LinearLayout) itemView.findViewById(R.id.linear);
        }
    }

}

