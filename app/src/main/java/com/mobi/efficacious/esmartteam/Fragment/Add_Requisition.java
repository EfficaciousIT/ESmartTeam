package com.mobi.efficacious.esmartteam.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.CustomDescription;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mobi.efficacious.esmartteam.Interface.DataService;
import com.mobi.efficacious.esmartteam.R;
import com.mobi.efficacious.esmartteam.activity.MainActivity;
import com.mobi.efficacious.esmartteam.adapter.Customer_Spinner_Adapter;
import com.mobi.efficacious.esmartteam.adapter.Requision_Spinner_Adapter;
import com.mobi.efficacious.esmartteam.common.SpinnerError;
import com.mobi.efficacious.esmartteam.entity.CustomerList;
import com.mobi.efficacious.esmartteam.entity.CustomerListPojo;
import com.mobi.efficacious.esmartteam.entity.Requisition;
import com.mobi.efficacious.esmartteam.entity.RequisitionType;
import com.mobi.efficacious.esmartteam.entity.RequisitionTypePojo;
import com.mobi.efficacious.esmartteam.webApi.RetrofitInstance;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Requisition extends Fragment {
    View myview;
    Spinner Requisition_type, sp_CustomerName;
    EditText et_Job_Description, et_MobileNo, et_Address, et_Description;
    TextView tv_Targerdate;
    Button btnSubmit;
    private static final String PREFRENCES_NAME = "myprefrences";
    SharedPreferences settings;
    String EMPLOYEEID, ORGID;
    String requisitionSelected, CustomerSelected, requisitionSelected_id, CustomerSelected_id;
    Requision_Spinner_Adapter adapter;
    Customer_Spinner_Adapter adapter2;
    ArrayList<RequisitionType> RequisitionType_list = new ArrayList<RequisitionType>();
    ArrayList<CustomerList> Customer_list = new ArrayList<CustomerList>();
    SpinnerError spinnerError;
    private int mYear, mMonth, mDay;
    String JobDescription, CustMobile, CustAddress, CustTargetDate, Description, Selected_Date;
    ProgressDialog progressDoalog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.frmrequisition, null);
        try {
            spinnerError = new SpinnerError(getActivity());
            settings = getActivity().getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
            EMPLOYEEID = settings.getString("TAG_EMPLOYEEID", "");
            ORGID = settings.getString("TAG_ORGID", "");
            Requisition_type = (Spinner) myview.findViewById(R.id.Requisition_type);
            sp_CustomerName = (Spinner) myview.findViewById(R.id.sp_CustomerName);
            et_Job_Description = (EditText) myview.findViewById(R.id.et_Job_Description);
            et_MobileNo = (EditText) myview.findViewById(R.id.et_MobileNo);
            et_Address = (EditText) myview.findViewById(R.id.et_Address);
            et_Description = (EditText) myview.findViewById(R.id.et_Description);
            tv_Targerdate = (TextView) myview.findViewById(R.id.tv_Targerdate);
            btnSubmit = (Button) myview.findViewById(R.id.btnSubmit);
            try {
                Requisitiontype();
                CustomerDetail();
            }catch (Exception ex)
            {

            }

        }catch (Exception ex)
        {

        }

        tv_Targerdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Locale.setDefault(Locale.ENGLISH);
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    NumberFormat f = new DecimalFormat("00");
                                    Selected_Date = ((f.format(dayOfMonth)) + "/" + (f.format(monthOfYear + 1)) + "/" + year);
                                    CustTargetDate = (year + "/" + (f.format(monthOfYear + 1)) + "/" + (f.format(dayOfMonth)));
                                    tv_Targerdate.setText(Selected_Date);
                                }
                            }, mYear, mMonth, mDay);

                    datePickerDialog.show();
                }catch (Exception ex)
                {

                }

            }
        });
        Requisition_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    requisitionSelected_id = RequisitionType_list.get(position).getIntRequisitionTypeId().toString();
                    requisitionSelected = RequisitionType_list.get(position).getVchRequisitionType().toString();
                }catch (Exception ex)
                {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_CustomerName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    CustomerSelected_id = Customer_list.get(position).getCustId().toString();
                    CustomerSelected = Customer_list.get(position).getCustName().toString();
                    if (CustomerSelected_id.contentEquals("0")) {
                        et_MobileNo.setText("");
                        et_Address.setText("");
                    } else {
                        et_MobileNo.setText(Customer_list.get(position).getCustMobile().toString());
                        et_Address.setText(Customer_list.get(position).getCustAddress().toString());
                    }
                }catch (Exception ex)
                {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JobDescription = et_Job_Description.getText().toString();
                    CustMobile = et_MobileNo.getText().toString();
                    CustAddress = et_Address.getText().toString();
                    CustTargetDate = tv_Targerdate.getText().toString();
                    Description=et_Description.getText().toString();
                    if (CustMobile.contentEquals("") || CustAddress.contentEquals("") || CustTargetDate.contentEquals("") || requisitionSelected_id.contentEquals("0") || CustomerSelected_id.contentEquals("0") || JobDescription.contentEquals("")) {
                        if (requisitionSelected_id.contentEquals("0")) {
                            spinnerError.setSpinnerError(Requisition_type, "Invalid Requisition Type");
                        }
                        if (CustomerSelected_id.contentEquals("0")) {
                            spinnerError.setSpinnerError(sp_CustomerName, "Invalid Customer Name");
                        }
                        if (TextUtils.isEmpty(et_Job_Description.getText().toString())) {
                            et_Job_Description.setError("Enter Valid Job Description ");
                        }
                        if (TextUtils.isEmpty(et_MobileNo.getText().toString())) {
                            et_MobileNo.setError("Enter Valid Mobile No. ");
                        }
                        if (TextUtils.isEmpty(et_Address.getText().toString())) {
                            et_Address.setError("Enter Valid Address ");
                        }
                        if (TextUtils.isEmpty(tv_Targerdate.getText().toString())) {
                            tv_Targerdate.setError("Enter Valid Target Date ");
                        }

                    } else {
                        Submit();
                    }
                }catch (Exception ex)
                {

                }


            }
        });
        return myview;
    }

    public void Requisitiontype() {
        try {
            RequisitionType requisitionType = new RequisitionType(0, "--Select--", 0);
            RequisitionType_list.addAll(Collections.singleton(requisitionType));
        }catch (Exception ex)
        {

        }
        try {
            DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
            Call<RequisitionTypePojo> call = service.getRequisitionType(ORGID);
            call.enqueue(new Callback<RequisitionTypePojo>() {
                @Override
                public void onResponse(Call<RequisitionTypePojo> call, Response<RequisitionTypePojo> response) {
                    if (response.isSuccessful()) {
                        RequisitionType_list.addAll(response.body().getRequisitionType());
                        int Count = RequisitionType_list.size();

                        adapter = new Requision_Spinner_Adapter(getActivity(), RequisitionType_list, "AddRequisition");
                        Requisition_type.setAdapter(adapter);

                    } else {

                        adapter = new Requision_Spinner_Adapter(getActivity(), RequisitionType_list, "AddRequisition");
                        Requisition_type.setAdapter(adapter);

                    }

                }

                @Override
                public void onFailure(Call<RequisitionTypePojo> call, Throwable t) {
                    Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception ex)
        {

        }



    }

    public void CustomerDetail() {
        try {
            CustomerList customerList = new CustomerList(0, "--Select--", "", "", "");
            Customer_list.addAll(Collections.singleton(customerList));
            DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
            Call<CustomerListPojo> call = service.getCustomerDetail(ORGID);
            call.enqueue(new Callback<CustomerListPojo>() {
                @Override
                public void onResponse(Call<CustomerListPojo> call, Response<CustomerListPojo> response) {
                    if (response.isSuccessful()) {
                        Customer_list.addAll(response.body().getCustomerList());
                        int Count = Customer_list.size();

                        adapter2 = new Customer_Spinner_Adapter(getActivity(), Customer_list, "AddRequisition");
                        sp_CustomerName.setAdapter(adapter2);

                    } else {

                        adapter2 = new Customer_Spinner_Adapter(getActivity(), Customer_list, "AddRequisition");
                        sp_CustomerName.setAdapter(adapter2);

                    }

                }

                @Override
                public void onFailure(Call<CustomerListPojo> call, Throwable t) {
                    Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception ex)
        {

        }

    }

    public void Submit() {
        try {
            progressDoalog = new ProgressDialog(getActivity());
            progressDoalog.setCancelable(false);
            progressDoalog.setCanceledOnTouchOutside(false);
            progressDoalog.setMessage("Processing...");
            progressDoalog.show();
            Requisition insert = new Requisition(CustomerSelected_id,Integer.parseInt(requisitionSelected_id),JobDescription,CustAddress,CustTargetDate,CustMobile,Description,Integer.parseInt(ORGID));
            DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
            Call<ResponseBody> call = service.InserRequisition(insert);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    try {
                        String responsee = String.valueOf(response.code());
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Save Succesfully", Toast.LENGTH_SHORT).show();
                            progressDoalog.dismiss();
                            Add_Requisition customer_tab = new Add_Requisition ();
                            MainActivity.fragmentManager.beginTransaction().replace(R.id.content_main, customer_tab).commitAllowingStateLoss();

                        }
                        else {
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
