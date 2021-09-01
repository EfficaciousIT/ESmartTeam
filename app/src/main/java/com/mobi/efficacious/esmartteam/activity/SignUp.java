package com.mobi.efficacious.esmartteam.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobi.efficacious.esmartteam.Interface.DataService;
import com.mobi.efficacious.esmartteam.R;
import com.mobi.efficacious.esmartteam.entity.Login;
import com.mobi.efficacious.esmartteam.entity.LoginPojo;
import com.mobi.efficacious.esmartteam.webApi.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUp extends AppCompatActivity {
    private static final String TAG = "ServicesDemo";
    Button buttonLogin, buttonCancel;
    EditText etUserName;
    EditText etPassword;
    String Username,Password;
    String EMPLOYEEID,TEAMID,ORGID,EMAILID,MOBILENO;
    public String IMEIKEY;
    public String SIMSERIALNO;
    private static final String PREFRENCES_NAME = "myprefrences";
    static SharedPreferences settings;
    static final int ACTIVATION_REQUEST = 47;
    TelephonyManager tel;
    int LoginResponse=0;
     ProgressDialog progressDoalog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        settings = getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
        buttonLogin = (Button) findViewById(R.id.xbtnSubmit_Signup);
        buttonCancel = (Button) findViewById(R.id.xbtnCancel_Signup);
        etUserName = (EditText) findViewById(R.id.xedtUserName_SignUp);
        etPassword = (EditText) findViewById(R.id.xedtPassword_SignUp);

        try {
            tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            try {
                IMEIKEY = tel.getDeviceId().toString();
                SIMSERIALNO = tel.getSimSerialNumber();
            }catch (Exception ex)
            {

            }

        }
        catch (Exception e)
        {
            IMEIKEY = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Username=etUserName.getText().toString();
                    Password=etPassword.getText().toString();
                    if(Username.contentEquals("")||Password.contentEquals(""))
                    {
                        if(TextUtils.isEmpty(Username)) {
                            etUserName.setError("Enter Valid Username ");
                        }
                        if(TextUtils.isEmpty(Password)) {
                            etPassword.setError("Enter Valid Password ");
                        }
                    }
                    else
                    {
                        LoginAsync (Username,Password);
                    }
                }catch (Exception ex)
                {

                }



            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    finish();
                }catch (Exception ex)
                {

                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case ACTIVATION_REQUEST:
                try {
                    if (resultCode == Activity.RESULT_OK)
                    {
                        Log.i(TAG, "Administration enabled!");
                    }
                    else
                    {
                        Log.i(TAG, "Administration enable FAILED!");
                        finish();
                    }
                }catch (Exception ex)
                {

                }

                return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


        public void  LoginAsync(String username, String password) {
            try {
                progressDoalog = new ProgressDialog(SignUp.this);
                progressDoalog.setMessage("loading....");
                progressDoalog.setTitle("Login");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.show();
                LoginResponse = 0;
                DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
                Call<LoginPojo> call = service.getLoginData(username, password);
                call.enqueue(new Callback<LoginPojo>() {
                    @Override
                    public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {
                        LoginResponse = response.code();

                        if (response.isSuccessful()) {
                            generateEmployeeList((ArrayList<Login>) response.body().getLogin());

                        } else {
                            progressDoalog.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginPojo> call, Throwable t) {
                        LoginResponse = 0;
                        Toast.makeText(SignUp.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        progressDoalog.dismiss();
                    }
                });
            } catch (Exception ex) {
                LoginResponse = 0;
                progressDoalog.dismiss();
            }

        }

        public  void Login()
        {
            try {
                if(LoginResponse==200)
                {
                    if(TEAMID.equalsIgnoreCase("0"))
                    {
//                    Intent HomeScreenIntent=new Intent(SignUp.this, MainActivity.class);
//                    startActivity(HomeScreenIntent);
//                    finish();
                    }
                    else
                    {
                        Intent HomeScreenIntent=new Intent(SignUp.this, MainActivity.class);
                        startActivity(HomeScreenIntent);
                        finish();
                    }

                }else
                {
                    Toast.makeText(SignUp.this, "Invalid Username Or Password", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception ex)
            {

            }

        }
    public  void generateEmployeeList(ArrayList<Login> loginDataList) {

        try {
            if((loginDataList != null && !loginDataList.isEmpty()))
            {
                for (int i = 0; i < loginDataList.size(); i++) {
                    try
                    {
                        EMPLOYEEID = String.valueOf(loginDataList.get(i).getIntEmployeeId());
                        TEAMID = String.valueOf(loginDataList.get(i).getIntTeamId());
                        ORGID = String.valueOf(loginDataList.get(i).getIntOrgId());
                        EMAILID=loginDataList.get(i).getVchEmail();
                        MOBILENO=loginDataList.get(i).getVchMobileNo();
                    }catch (Exception ex)
                    {

                    }

                }

                try
                {
                    progressDoalog.dismiss();
                    settings.edit().putString("TAG_EMPLOYEEID", EMPLOYEEID).commit();
                    settings.edit().putString("TAG_TEAMID", TEAMID).commit();
                    settings.edit().putString("TAG_ORGID", ORGID).commit();
                    settings.edit().putString("TAG_IMEIKEY", IMEIKEY).commit();
                    settings.edit().putString("TAG_EMAILID", EMAILID).commit();
                    settings.edit().putString("TAG_MOBILENO", MOBILENO).commit();
                    settings.edit().putString("TAG_SIMSERIALNO", SIMSERIALNO).commit();
                    settings.edit().putString("TAG_USERNAME", etUserName.getText().toString()).commit();

                    Login();
                }catch (Exception ex)
                {

                }
            }else
            {
                progressDoalog.dismiss();
                LoginResponse=0;
                Toast.makeText(SignUp.this, "Invalid Username Or Password", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex)
        {

        }



    }
}
