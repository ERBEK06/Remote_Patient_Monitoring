package com.erbek.remotepatientm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class PatientHomepage extends AppCompatActivity {

    private BottomNavigationView bottombar;
    private PatientHomeFragment homeFragment;
    private PatientTimeFragment timeFragment;
    private PatientSettingFragment settingFragment;
    private String takenuserid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_homepage);

        homeFragment = new PatientHomeFragment();
        settingFragment = new PatientSettingFragment();
        timeFragment = new PatientTimeFragment();
        setFragment((homeFragment));

        Intent gelenintent = getIntent();
        takenuserid = gelenintent.getStringExtra("patientname");


        setContentView(R.layout.activity_patient_homepage);
        bottombar = (BottomNavigationView)findViewById(R.id.Patient_Homepage_bottomnavigation);
        bottombar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.bottombar_menu_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.bottombar_menu_time:
                        setFragment(timeFragment);
                        return true;
                    case R.id.bottombar_menu_settings:
                        setFragment(settingFragment);
                        return true;
                    default:
                        return false;

                }
            }
        });

    }
    private void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("key", takenuserid);

        homeFragment.setArguments(bundle);
        settingFragment.setArguments(bundle);
        timeFragment.setArguments(bundle);





        transaction.replace(R.id.Patient_Homepage_Framelayout, fragment);
        transaction.commit();

    }
}