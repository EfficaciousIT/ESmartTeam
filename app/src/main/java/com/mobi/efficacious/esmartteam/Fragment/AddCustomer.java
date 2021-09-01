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
import com.mobi.efficacious.esmartteam.entity.TaskList;
import com.mobi.efficacious.esmartteam.tab.Customer_Tab;
import com.mobi.efficacious.esmartteam.tab.TaskList_Botomtab;
import com.mobi.efficacious.esmartteam.webApi.RetrofitInstance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCustomer extends Fragment {
    View myview;
    EditText et_CustomerName, et_CustomerMobileNo, et_CustomerEmailId, et_CustomerAddress, et_CustomerLandmark, et_CustomerNationality;
    Button sumbitbtn;
    String CustomerName, CustomerMobileNo,Customer_EmailId,Customer_Address,Customer_Landmark,Customer_Nantionality;
    ProgressDialog progressDoalog;
     String ORGID;
    static final String PREFRENCES_NAME = "myprefrences";
    static SharedPreferences settings;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.add_customer, null);
        settings = getActivity().getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
        ORGID = settings.getString("TAG_ORGID", "");
        et_CustomerName = (EditText) myview.findViewById(R.id.et_CustomerName);
        et_CustomerMobileNo = (EditText) myview.findViewById(R.id.et_CustomerMobileNo);
        et_CustomerEmailId = (EditText) myview.findViewById(R.id.et_CustomerEmailId);
        et_CustomerAddress = (EditText) myview.findViewById(R.id.et_CustomerAddress);
        et_CustomerLandmark = (EditText) myview.findViewById(R.id.et_CustomerLandmark);
        et_CustomerNationality = (EditText) myview.findViewById(R.id.et_CustomerNationality);
        sumbitbtn = (Button) myview.findViewById(R.id.sumbitbtn);

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
            CustomerList insert = new CustomerList(CustomerName,CustomerMobileNo,Customer_EmailId,Customer_Address,Customer_Landmark,Customer_Nantionality,ORGID);
            DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
            Call<ResponseBody> call = service.InsertCustomer(insert);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    try {
                        String responsee = String.valueOf(response.code());
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Save Succesfully", Toast.LENGTH_SHORT).show();
                            progressDoalog.dismiss();
                            Customer_Tab customer_tab = new Customer_Tab ();
                            MainActivity.fragmentManager.beginTransaction().replace(R.id.content_main, customer_tab).commitAllowingStateLoss();
                        } else {
                            progressDoalog.dismiss();
                            Toast.makeText(getActivity(), "Error While Uploading Data", Toast.LENGTH_SHORT).show();
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
