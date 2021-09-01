package com.mobi.efficacious.esmartteam.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobi.efficacious.esmartteam.Interface.DataService;
import com.mobi.efficacious.esmartteam.R;
import com.mobi.efficacious.esmartteam.activity.MainActivity;
import com.mobi.efficacious.esmartteam.entity.CustomerList;
import com.mobi.efficacious.esmartteam.tab.Customer_Tab;
import com.mobi.efficacious.esmartteam.webApi.RetrofitInstance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateCustomer extends Fragment {
    View myview;
    EditText et_CustomerName, et_CustomerMobileNo, et_CustomerEmailId, et_CustomerAddress, et_CustomerLandmark, et_CustomerNationality;
    Button sumbitbtn;
    String CustomerName, CustomerMobileNo,Customer_EmailId,Customer_Address,Customer_Landmark,Customer_Nantionality;
    ProgressDialog progressDoalog;
    String ORGID,CustId;
    static final String PREFRENCES_NAME = "myprefrences";
    static SharedPreferences settings;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.add_customer, null);
        try {
            settings = getActivity().getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
            ORGID = settings.getString("TAG_ORGID", "");
            et_CustomerName = (EditText) myview.findViewById(R.id.et_CustomerName);
            et_CustomerMobileNo = (EditText) myview.findViewById(R.id.et_CustomerMobileNo);
            et_CustomerEmailId = (EditText) myview.findViewById(R.id.et_CustomerEmailId);
            et_CustomerAddress = (EditText) myview.findViewById(R.id.et_CustomerAddress);
            et_CustomerLandmark = (EditText) myview.findViewById(R.id.et_CustomerLandmark);
            et_CustomerNationality = (EditText) myview.findViewById(R.id.et_CustomerNationality);
            sumbitbtn = (Button) myview.findViewById(R.id.sumbitbtn);
            sumbitbtn.setText("Update");
        }catch (Exception ex)
        {

        }

        try
        {
            CustId = getArguments().getString("Cust_Id");
            CustomerName = getArguments().getString("Cust_Name");
            CustomerMobileNo = getArguments().getString("Cust_MobileNo");
            Customer_EmailId=getArguments().getString("Cust_Email");
            Customer_Address=getArguments().getString("Cust_Address");
            Customer_Landmark=getArguments().getString("Cust_Landmark");
            Customer_Nantionality=getArguments().getString("Cust_Nationality");

           et_CustomerName.setText(CustomerName);
            et_CustomerMobileNo.setText(CustomerMobileNo);
            et_CustomerEmailId.setText(Customer_EmailId);
           et_CustomerAddress.setText(Customer_Address);
            et_CustomerLandmark.setText(Customer_Landmark);
            et_CustomerNationality.setText(Customer_Nantionality);
        }catch (Exception ex)
        {

        }



        sumbitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CustomerName = et_CustomerName.getText().toString();
                    CustomerMobileNo = et_CustomerMobileNo.getText().toString();
                    Customer_EmailId=et_CustomerEmailId.getText().toString();

                    Customer_Address=et_CustomerAddress.getText().toString();
                    Customer_Landmark=et_CustomerLandmark.getText().toString();
                    Customer_Nantionality=et_CustomerNationality.getText().toString();
                    if (CustomerName == null || CustomerName.isEmpty() || CustomerName.trim().isEmpty()|| CustomerMobileNo == null || CustomerMobileNo.isEmpty()||CustomerMobileNo.trim().isEmpty())
                    {
                        if (TextUtils.isEmpty(CustomerName)) {
                            et_CustomerName.setError("Enter Valid Customer Name ");
                        }
                        if (TextUtils.isEmpty(CustomerMobileNo)) {
                            et_CustomerMobileNo.setError("Enter Valid Mobile No ");
                        }
                    } else {
                        if(Customer_EmailId==null || Customer_EmailId.isEmpty()||Customer_EmailId.trim().isEmpty())
                        {
                            AddCustomer();

                        }else
                        {
                            boolean email_status=android.util.Patterns.EMAIL_ADDRESS.matcher(Customer_EmailId).matches();
                            if(email_status==true)
                            {
                                AddCustomer();
                            }else
                            {
                                et_CustomerEmailId.setError("Enter Valid Email Id ");
                            }
                        }
                    }

                } catch (Exception ex) {

                }


            }
        });

        return myview;
    }

    public void AddCustomer()
    {
        try {
            progressDoalog = new ProgressDialog(getActivity());
            progressDoalog.setCancelable(false);
            progressDoalog.setCanceledOnTouchOutside(false);
            progressDoalog.setMessage("Processing...");
            progressDoalog.show();
            int Cust_id= Integer.parseInt(CustId);
            CustomerList update = new CustomerList(CustomerName,CustomerMobileNo,Customer_EmailId,Customer_Address,Customer_Landmark,Customer_Nantionality,ORGID,Cust_id);
            DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
            Call<ResponseBody> call = service.UpdateCustomer(update);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    try {
                        String responsee = String.valueOf(response.code());
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Updaate Done Succesfully", Toast.LENGTH_SHORT).show();
                            progressDoalog.dismiss();
                            ((MainActivity) getActivity()).setActionBarTitle("Customer");
                            Customer_Tab customer_tab = new Customer_Tab ();
                            MainActivity.fragmentManager.beginTransaction().replace(R.id.content_main, customer_tab).commitAllowingStateLoss();


                        } else {
                            progressDoalog.dismiss();
                            Toast.makeText(getActivity(), "Error While Updating Data", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex) {
                        progressDoalog.dismiss();
                        String msg = ex.getMessage();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDoalog.dismiss();
                    Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception ex)
        {

        }

    }
}
