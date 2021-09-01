package com.mobi.efficacious.esmartteam.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobi.efficacious.esmartteam.Interface.DataService;
import com.mobi.efficacious.esmartteam.R;
import com.mobi.efficacious.esmartteam.activity.MainActivity;
import com.mobi.efficacious.esmartteam.entity.TaskList;
import com.mobi.efficacious.esmartteam.entity.TaskListPojo;
import com.mobi.efficacious.esmartteam.tab.TaskList_Botomtab;
import com.mobi.efficacious.esmartteam.webApi.RetrofitInstance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DispatchedDetails_fragement extends Fragment {
    View myview;
    String RequisitionId;

    String Name;
    String MobileNo;
    String Address;
    String Requisition;
    String TargetDate;
    String Description;
    String Status;


    ProgressDialog progressDoalog;

    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView txtName;
    TextView txtRequistion;
    String Dispatch_name, Description_,suspendedDate;
    EditText etDescription;
    EditText etSuspendDate;
    Button btnSubmit;

    Button btnCal;
    Spinner spStatus;
    static String EMPLOYEEID;
    static String IMEIKEY;
    static String GCMKEY;
    static String ORGID;
    static String TEAMID;
    static final String PREFRENCES_NAME = "myprefrences";
    static SharedPreferences settings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.dispatched_layout, null);
        try {
            settings = getActivity().getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
            EMPLOYEEID = settings.getString("TAG_EMPLOYEEID", "");
            IMEIKEY = settings.getString("TAG_IMEIKEY", "");
            ORGID = settings.getString("TAG_ORGID", "");
            TEAMID = settings.getString("TAG_TEAMID", "");
            GCMKEY = settings.getString("TAG_GCMKEY", "");

            RequisitionId = getArguments().getString("Tag_RequisitionId");
            Name = getArguments().getString("Tag_Name");
            Requisition = getArguments().getString("Tag_Requisition");
            Requisition = getArguments().getString("Tag_Requisition");
            MobileNo = getArguments().getString("Tag_MobileNo");
            Address = getArguments().getString("Tag_Address");
            TargetDate = getArguments().getString("Tag_TargetDate");
            Description = getArguments().getString("Tag_Description");
            Status = getArguments().getString("Tag_Status");

            txtName = (TextView) myview.findViewById(R.id.NameTextView_dispatched);
            txtRequistion = (TextView) myview.findViewById(R.id.RequisitionTextView_dispached);
            spStatus = (Spinner) myview.findViewById(R.id.spinnerstatus_dispached);
            etDescription = (EditText) myview.findViewById(R.id.DescriptionEditText_dispatched);
            etSuspendDate = (EditText) myview.findViewById(R.id.suspendDateEditText_dispatch);
            btnCal = (Button) myview.findViewById(R.id.calbutton_dispatch);
            btnSubmit = (Button) myview.findViewById(R.id.xbtnSubmit_Dispatch);


            List<String> stat = new ArrayList<String>();
            stat.add("Select");
            stat.add("Start");
            stat.add("Pending");
            stat.add("Completed");
            stat.add("Suspend");

            ArrayAdapter<String> dataada = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stat);
            dataada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spStatus.setAdapter(dataada);
        }catch (Exception ex)
        {

        }


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    String status = spStatus.getSelectedItem().toString();
                    if (status.equalsIgnoreCase("Completed")) {
                        ActionRequistationEnd();
                    } else if (status.equalsIgnoreCase("Start")) {
                        ActionRequistationStart();
                    } else if (status.equalsIgnoreCase("Suspend")) {
                        InsertRequisitionSuspended();
                    } else if (status.equalsIgnoreCase("Pending")) {
                        ActionRequistationPending();
                    } else {
                        Toast.makeText(getActivity(), "Please select Status", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex)
                {

                }

            }
        });


        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                    // Launch Date Picker Dialog
                    DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    monthOfYear = (monthOfYear + 1);
                                    String dd = String.valueOf(dayOfMonth);
                                    String mm = String.valueOf(monthOfYear);
                                    int ddlength = dd.length();
                                    int mmlenght = mm.length();
                                    if (ddlength == 1) {
                                        dd = "0" + dd;
                                    }

                                    if (mmlenght == 1) {
                                        mm = "0" + mm;
                                    }

                                    etSuspendDate.setText(mm + "/" + dd + "/" + year);
                                }
                            }, mYear, mMonth, mDay);
                    dpd.show();
                }catch (Exception ex)
                {

                }


            }
        });
        try {
            txtName.setText(Name);
            txtRequistion.setText(Requisition);
            etDescription.setText(Description);
        }catch (Exception ex)
        {

        }

        return myview;
    }

    public void ActionRequistationEnd() {
        try {
            Dispatch_name = txtName.getText().toString();
            Description_ = etDescription.getText().toString();
            if (Dispatch_name.contentEquals("") || Description_.contentEquals("")) {
                if (TextUtils.isEmpty(Dispatch_name)) {
                    txtName.setError("Enter Valid Name ");
                }
                if (TextUtils.isEmpty(Description_)) {
                    etDescription.setError("Enter Valid Description ");
                }
            } else {
                progressDoalog = new ProgressDialog(getActivity());
                progressDoalog.setCancelable(false);
                progressDoalog.setCanceledOnTouchOutside(false);
                progressDoalog.setMessage("Processing...");
                progressDoalog.show();

                TaskList update = new TaskList(Integer.parseInt(RequisitionId), Integer.parseInt(EMPLOYEEID), "Completed", txtName.getText().toString(), Integer.parseInt(TEAMID), etDescription.getText().toString());
                DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
                Call<ResponseBody> call = service.UpdateInsertTaskList("ActionRequistationEnd",update);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {
                            String responsee = String.valueOf(response.code());
                            if (response.isSuccessful()) {
                                Toast.makeText(getActivity(), "Save Succesfully", Toast.LENGTH_SHORT).show();
                                progressDoalog.dismiss();
                                TaskList_Botomtab dailyTaskList_fragment = new TaskList_Botomtab ();
                                MainActivity.fragmentManager.beginTransaction().replace(R.id.content_main, dailyTaskList_fragment).addToBackStack(null).commitAllowingStateLoss();
                            } else {
                                progressDoalog.dismiss();
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
            }
        }catch (Exception ex)
        {

        }

    }

    public void ActionRequistationStart() {
        try {
            Dispatch_name = txtName.getText().toString();
            Description_ = etDescription.getText().toString();
            if (Dispatch_name.contentEquals("") || Description_.contentEquals("")) {
                if (TextUtils.isEmpty(Dispatch_name)) {
                    txtName.setError("Enter Valid Name ");
                }
                if (TextUtils.isEmpty(Description_)) {
                    etDescription.setError("Enter Valid Description ");
                }
            } else {
                progressDoalog = new ProgressDialog(getActivity());
                progressDoalog.setCancelable(false);
                progressDoalog.setCanceledOnTouchOutside(false);
                progressDoalog.setMessage("Processing...");
                progressDoalog.show();
                TaskList update = new TaskList(Integer.parseInt(RequisitionId), Integer.parseInt(EMPLOYEEID), "Start", txtName.getText().toString(), Integer.parseInt(TEAMID), etDescription.getText().toString());
                DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
                Call<ResponseBody> call = service.UpdateInsertTaskList("ActionRequistationStart",update);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {
                            String responsee = String.valueOf(response.code());
                            if (response.isSuccessful()) {
                                Toast.makeText(getActivity(), "Save Succesfully", Toast.LENGTH_SHORT).show();
                                progressDoalog.dismiss();
                                TaskList_Botomtab dailyTaskList_fragment = new TaskList_Botomtab ();
                                MainActivity.fragmentManager.beginTransaction().replace(R.id.content_main, dailyTaskList_fragment).addToBackStack(null).commitAllowingStateLoss();
                            } else {
                                progressDoalog.dismiss();
                            }
                        } catch (Exception ex) {
                            String msg = ex.getMessage();
                            progressDoalog.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressDoalog.dismiss();
                        Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }catch (Exception ex)
        {

        }

    }
    public void InsertRequisitionSuspended() {
        try {
            suspendedDate =  etSuspendDate.getText().toString();
            Description_ = etDescription.getText().toString();
            if (suspendedDate.contentEquals("") || Description_.contentEquals("")) {
                if (TextUtils.isEmpty(suspendedDate)) {
                    etSuspendDate.setError("Enter Valid Date ");
                }
                if (TextUtils.isEmpty(Description_)) {
                    etDescription.setError("Enter Valid Description ");
                }
            } else {
                progressDoalog = new ProgressDialog(getActivity());
                progressDoalog.setCancelable(false);
                progressDoalog.setCanceledOnTouchOutside(false);
                progressDoalog.setMessage("Processing...");
                progressDoalog.show();
                TaskList update = new TaskList(Integer.parseInt(RequisitionId), Integer.parseInt(EMPLOYEEID), txtName.getText().toString(), Integer.parseInt(TEAMID), etDescription.getText().toString(),etSuspendDate.getText().toString());
                DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
                Call<ResponseBody> call = service.UpdateInsertTaskList("ActionRequistationSuspend",update);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {
                            String responsee = String.valueOf(response.code());
                            if (response.isSuccessful()) {
                                Toast.makeText(getActivity(), "Save Succesfully", Toast.LENGTH_SHORT).show();
                                progressDoalog.dismiss();
                                TaskList_Botomtab dailyTaskList_fragment = new TaskList_Botomtab ();
                                MainActivity.fragmentManager.beginTransaction().replace(R.id.content_main, dailyTaskList_fragment).addToBackStack(null).commitAllowingStateLoss();
                            } else {
                                progressDoalog.dismiss();
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
            }
        }catch (Exception ex)
        {

        }

    }
    public void ActionRequistationPending() {
        try {
            Dispatch_name = txtName.getText().toString();
            Description_ = etDescription.getText().toString();
            if (Dispatch_name.contentEquals("") || Description_.contentEquals("")) {
                if (TextUtils.isEmpty(Dispatch_name)) {
                    txtName.setError("Enter Valid Name ");
                }
                if (TextUtils.isEmpty(Description_)) {
                    etDescription.setError("Enter Valid Description ");
                }
            } else {
                progressDoalog = new ProgressDialog(getActivity());
                progressDoalog.setCancelable(false);
                progressDoalog.setCanceledOnTouchOutside(false);
                progressDoalog.setMessage("Processing...");
                progressDoalog.show();
                TaskList update = new TaskList(Integer.parseInt(RequisitionId), Integer.parseInt(EMPLOYEEID), "Pending", txtName.getText().toString(), Integer.parseInt(TEAMID), etDescription.getText().toString());
                DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
                Call<ResponseBody> call = service.UpdateInsertTaskList("ActionRequistationPending",update);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {
                            String responsee = String.valueOf(response.code());
                            if (response.isSuccessful()) {
                                Toast.makeText(getActivity(), "Save Succesfully", Toast.LENGTH_SHORT).show();
                                progressDoalog.dismiss();
                                TaskList_Botomtab dailyTaskList_fragment = new TaskList_Botomtab ();
                                MainActivity.fragmentManager.beginTransaction().replace(R.id.content_main, dailyTaskList_fragment).addToBackStack(null).commitAllowingStateLoss();
                            } else {
                                progressDoalog.dismiss();
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
            }
        }catch (Exception ex)
        {

        }

    }
}

