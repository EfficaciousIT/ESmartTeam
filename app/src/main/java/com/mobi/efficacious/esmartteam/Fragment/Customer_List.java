package com.mobi.efficacious.esmartteam.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mobi.efficacious.esmartteam.Interface.DataService;
import com.mobi.efficacious.esmartteam.R;
import com.mobi.efficacious.esmartteam.adapter.CustomerListAdapter;
import com.mobi.efficacious.esmartteam.entity.CustomerList;
import com.mobi.efficacious.esmartteam.entity.CustomerListPojo;
import com.mobi.efficacious.esmartteam.webApi.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Customer_List extends Fragment {
    View myview;
    ProgressDialog progressDoalog;
    private static final String PREFRENCES_NAME = "myprefrences";
    SharedPreferences settings;
    String EMPLOYEEID, ORGID;
    RecyclerView recyclerView;
    public static CustomerListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.tasklist_recyclerview, null);
        try {
            recyclerView = (RecyclerView) myview.findViewById(R.id.recyclerView_tasklist);
            settings = getActivity().getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
            EMPLOYEEID = settings.getString("TAG_EMPLOYEEID", "");
            ORGID = settings.getString("TAG_ORGID", "");

            progressDoalog = new ProgressDialog(getActivity());
            progressDoalog.setMessage("loading....");
            progressDoalog.setTitle("Customer List");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();
            DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
            Call<CustomerListPojo> call = service.getCustomerDetail(ORGID);


            call.enqueue(new Callback<CustomerListPojo>() {
                @Override
                public void onResponse(Call<CustomerListPojo> call, Response<CustomerListPojo> response) {
                    String responsee = String.valueOf(response.code());

                    if (response.isSuccessful()) {
                        generateEmployeeList((ArrayList<CustomerList>) response.body().getCustomerList());
                        progressDoalog.dismiss();
                    } else {
                        progressDoalog.dismiss();
                        Toast.makeText(getActivity(), "Something went wrong...Please try Again!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<CustomerListPojo> call, Throwable t) {
                    progressDoalog.dismiss();
                    Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception ex)
        {

        }

        return myview;
    }

    public void generateEmployeeList(ArrayList<CustomerList> taskListDataList) {
        try {
            if ((taskListDataList != null && !taskListDataList.isEmpty())) {
                adapter = new CustomerListAdapter(taskListDataList, getContext());

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

                recyclerView.setLayoutManager(layoutManager);

                recyclerView.setAdapter(adapter);
            }
        }catch (Exception ex)
        {

        }


    }
}
