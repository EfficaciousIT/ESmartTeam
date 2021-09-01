package com.mobi.efficacious.esmartteam.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.mobi.efficacious.esmartteam.R;
import com.mobi.efficacious.esmartteam.common.ConnectionDetector;

import java.util.Timer;
import java.util.TimerTask;


public class Splash extends Activity
{
	TimerTask mTimerTask;
	Timer mTimer;
	Context mContext;
	String flag;
	String TEAMID;
	String USERNAME;
	String passWord;
	String role;
	ConnectionDetector cd;
	private static final String PREFRENCES_NAME = "myprefrences";
	SharedPreferences settings;
	public String SIMSERIALNO;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		settings = getSharedPreferences(PREFRENCES_NAME,Context.MODE_PRIVATE);
		TEAMID = settings.getString("TAG_TEAMID", "");
		USERNAME = settings.getString("TAG_USERNAME", "");
		mContext=Splash.this;
		cd = new ConnectionDetector(getApplicationContext());
		if (!cd.isConnectingToInternet())
		{
			AlertDialog.Builder alert = new AlertDialog.Builder(Splash.this);
			alert.setMessage("No Internet Connection");
			alert.setPositiveButton("OK",null);
			alert.show();
		}
		else
		{
			try {
				mTimerTask=new TimerTask()
				{
					@Override
					public void run()
					{
						if(TEAMID.equalsIgnoreCase("0"))
						{
//						Intent HomeScreenIntent=new Intent(mContext, MainActivity.class);
//						startActivity(HomeScreenIntent);
//						finish();
						}
						else
						{
							if(USERNAME.equalsIgnoreCase(""))
							{
								Intent MainScreenIntent=new Intent(mContext, SignUp.class);
								startActivity(MainScreenIntent);
								finish();
							}
							else
							{
								Intent HomeScreenIntent=new Intent(mContext, MainActivity.class);
								startActivity(HomeScreenIntent);
								finish();
							}

						}
					}
				};
				mTimer=new Timer();
				mTimer.schedule(mTimerTask, 1000);
			}catch (Exception ex)
			{

			}

		}
	}
}
