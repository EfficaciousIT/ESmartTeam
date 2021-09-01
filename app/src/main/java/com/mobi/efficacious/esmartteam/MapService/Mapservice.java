package com.mobi.efficacious.esmartteam.MapService;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.mobi.efficacious.esmartteam.Interface.DataService;
import com.mobi.efficacious.esmartteam.R;
import com.mobi.efficacious.esmartteam.entity.Requisition;
import com.mobi.efficacious.esmartteam.entity.TblLatestRec;
import com.mobi.efficacious.esmartteam.webApi.RetrofitInstance;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mapservice extends Service {
    private String mLastUpdateTime;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final String PREFRENCES_NAME = "myprefrences";
    SharedPreferences settings;

    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

    private static final int REQUEST_CHECK_SETTINGS = 100;

    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    // boolean flag to toggle the ui
    private Boolean mRequestingLocationUpdates;
String latitude,longitude,timing,currentdate,speed,vehiclestatus,altitude;
String EMPLOYEEID,ORGID,TEAMID,USERNAME;
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try
        {
            settings = getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
            EMPLOYEEID = settings.getString("TAG_EMPLOYEEID", "");
            ORGID = settings.getString("TAG_ORGID", "");
            TEAMID=settings.getString("TAG_TEAMID", "");
            USERNAME=settings.getString("TAG_USERNAME", "");
            init();
        }catch (Exception ex)
        {

        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        try {
            startLocationUpdates();
        }catch (Exception ex)
        {

        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            startLocationUpdates();
        }catch (Exception ex)
        {

        }

        //Toast.makeText(this, "Service Stoped", Toast.LENGTH_LONG).show();
    }

    private void init() {
        try {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mSettingsClient = LocationServices.getSettingsClient(this);

            mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    // location is received
                    mCurrentLocation = locationResult.getLastLocation();
                    mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

                    updateLocationUI();
                }
            };

            mRequestingLocationUpdates = false;

            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
            mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
            builder.addLocationRequest(mLocationRequest);
            mLocationSettingsRequest = builder.build();
        }catch (Exception ex)
        {

        }

    }

    private void updateLocationUI() {
        try {
            if (mCurrentLocation != null) {
                latitude = String.valueOf(mCurrentLocation.getLatitude());
                longitude = String.valueOf(mCurrentLocation.getLongitude());
                timing = String.valueOf(mCurrentLocation.getTime());
                Date date = new Date();
                SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                timing = myFormat.format(date);
                currentdate=dateFormat.format(date);
                speed = String.valueOf(mCurrentLocation.getSpeed());
                if(mCurrentLocation.getSpeed()>0)
                {
                    vehiclestatus="Running";
                }else
                {
                    vehiclestatus="Stop";
                }
                altitude = String.valueOf(mCurrentLocation.getAltitude());
                if(Double.parseDouble(longitude)>1 && Double.parseDouble(longitude)>1)
                {
                    GPSupdateASYNC ();
                    LatestrecASYNC ();
                }


            }
        }catch (Exception ex)
        {

        }


    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                    mLocationCallback, Looper.myLooper());

            updateLocationUI();
        }catch (Exception ex)
        {

        }

    }
   public void  LatestrecASYNC()
   {
       try {
           TblLatestRec insert = new TblLatestRec(Double.parseDouble(latitude),Double.parseDouble(longitude),Integer.parseInt(TEAMID),Integer.parseInt(ORGID),Integer.parseInt(EMPLOYEEID),USERNAME,USERNAME);
           DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
           Call<ResponseBody> call = service.InsertLatestRec(insert);
           call.enqueue(new Callback<ResponseBody>() {
               @Override
               public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                   try {
                       String responsee = String.valueOf(response.code());
                       if (response.isSuccessful()) {

                       }
                   } catch (Exception ex) {

                       String msg = ex.getMessage();
                   }

               }

               @Override
               public void onFailure(Call<ResponseBody> call, Throwable t) {

               }
           });
       }catch (Exception ex)
       {

       }

   }
    public void  GPSupdateASYNC()
    {
        try {
            TblLatestRec insert = new TblLatestRec(Double.parseDouble(latitude),Double.parseDouble(longitude),Integer.parseInt(TEAMID),Integer.parseInt(ORGID),Integer.parseInt(EMPLOYEEID),USERNAME,USERNAME);
            DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
            Call<ResponseBody> call = service.InsertLocation(insert);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    try {
                        String responsee = String.valueOf(response.code());
                        if (response.isSuccessful()) {

                        }
                    } catch (Exception ex) {

                        String msg = ex.getMessage();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }catch (Exception ex)
        {

        }

    }
}
