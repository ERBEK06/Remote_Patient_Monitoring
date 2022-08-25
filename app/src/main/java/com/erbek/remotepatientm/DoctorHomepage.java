package com.erbek.remotepatientm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;

public class DoctorHomepage extends AppCompatActivity {

    private BottomNavigationView bottombar;
    private DoctorHomeFragment homeFragment;
    private DoctorTimeFragment timeFragment;
    private DoctorSettingFragment settingFragment;
    private DoctorDenemeFragment denemeFragment;
    public String takenuserid;
    private DatabaseReference mReference;
    public String us1;
    public int a1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeFragment = new DoctorHomeFragment();
        settingFragment = new DoctorSettingFragment();
        timeFragment = new DoctorTimeFragment();
        denemeFragment = new DoctorDenemeFragment();
        setFragment((homeFragment));


        Intent gelenintent = getIntent();
        takenuserid = gelenintent.getStringExtra("doctorname");






        setContentView(R.layout.activity_doctor_homepage);
        bottombar = (BottomNavigationView)findViewById(R.id.Doctor_Homepage_bottomnavigation);
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


        transaction.replace(R.id.Doctor_Homepage_Framelayout, fragment);
        transaction.commit();

    }
}