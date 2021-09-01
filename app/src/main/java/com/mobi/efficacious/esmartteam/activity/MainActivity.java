package com.mobi.efficacious.esmartteam.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.mobi.efficacious.esmartteam.Fragment.Add_Requisition;
import com.mobi.efficacious.esmartteam.Fragment.DailyTaskList_fragment;
import com.mobi.efficacious.esmartteam.MapService.Mapservice;
import com.mobi.efficacious.esmartteam.R;
import com.mobi.efficacious.esmartteam.tab.Customer_Tab;
import com.mobi.efficacious.esmartteam.tab.TaskList_Botomtab;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String PREFRENCES_NAME = "myprefrences";
    SharedPreferences settings;
    String title;
    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
        fragmentManager=getSupportFragmentManager();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        startService(new Intent(getBaseContext(), Mapservice.class));
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        title = "Today's TaskList";
        getSupportActionBar().setTitle(title);
        try{
            DailyTaskList_fragment dailyTaskList_fragment = new DailyTaskList_fragment ();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, dailyTaskList_fragment).commitAllowingStateLoss();
        }catch (Exception ex)
        {

        }

    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure want to Log Out?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager mfragment = getSupportFragmentManager();

        if (id == R.id.nav_dailyTask) {
            title = "Today's TaskList";
            try {
                DailyTaskList_fragment dailyTaskList_fragment = new DailyTaskList_fragment ();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, dailyTaskList_fragment).commitAllowingStateLoss();
            }catch (Exception ex)
            {

            }

        } else if (id == R.id.nav_taskStatus) {
            title = "TaskList";
            try {
                TaskList_Botomtab dailyTaskList_fragment = new TaskList_Botomtab ();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, dailyTaskList_fragment).commitAllowingStateLoss();
            }catch (Exception ex)
            {

            }


        }else if(id==R.id.nav_Customer)
        {
            title = "Customer";
            try {
                Customer_Tab customer_tab = new Customer_Tab ();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, customer_tab).commitAllowingStateLoss();
            }catch (Exception ex)
            {

            }



        }
        else if(id==R.id.nav_AddRequisition)
        {
            title = "Requisition";
            try {
                Add_Requisition add_requisition = new Add_Requisition ();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, add_requisition).commitAllowingStateLoss();
            }catch (Exception ex)
            {

            }


        }
        else if (id == R.id.nav_Logout) {
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure want to Log Out?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }catch (Exception ex)
            {

            }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        getSupportActionBar().setTitle(title);
        return true;
    }
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        SharedPreferences.Editor editor_delete = settings.edit();
                        editor_delete.clear().commit();
                        stopService(new Intent(getBaseContext(), Mapservice.class));
//            FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(MainActivity.this, SignUp.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }catch (Exception ex)
                    {

                    }

                    break;
                case DialogInterface.BUTTON_NEGATIVE:

                    break;
            }
        }
    };
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
