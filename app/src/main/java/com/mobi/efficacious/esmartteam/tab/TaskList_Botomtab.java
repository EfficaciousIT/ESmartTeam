package com.mobi.efficacious.esmartteam.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mobi.efficacious.esmartteam.Fragment.CompletedTaskList_fragment;
import com.mobi.efficacious.esmartteam.Fragment.PendingTaskList_fragment;
import com.mobi.efficacious.esmartteam.Fragment.SuspendedTaskList_fragment;
import com.mobi.efficacious.esmartteam.R;

public class TaskList_Botomtab extends Fragment{
    View myview;
    BottomNavigationView navigation;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.tasklist,null);
        navigation = (BottomNavigationView) myview.findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.nav_Pending:
                        try {
                            fragment = new PendingTaskList_fragment();
                            loadFragment(fragment);
                        }catch (Exception ex)
                        {

                        }

                        return true;
                    case R.id.nav_Suspended:
                        try {
                            fragment = new SuspendedTaskList_fragment();
                            loadFragment(fragment);
                        }catch (Exception ex)
                        {

                        }

                        return true;
                    case R.id.nav_Completed:
                        try {
                            fragment = new CompletedTaskList_fragment();
                            loadFragment(fragment);
                        }catch (Exception ex)
                        {

                        }

                        return true;
                }
                return false;
            }
        });
        try {
            Fragment fragment;
            fragment = new PendingTaskList_fragment();
            loadFragment(fragment);
        }catch (Exception ex)
        {

        }

        return myview;
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        try {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }catch (Exception ex)
        {

        }

    }
}
