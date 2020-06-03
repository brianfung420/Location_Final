package com.example.Location_Final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    SupportMapFragment smf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new MapsFragment());                                       //讀取Fragment

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);        //設置底部的bar
        navigation.setOnNavigationItemSelectedListener(this);

        //SupportMapFragment build
        smf = SupportMapFragment.newInstance();
        smf.getMapAsync(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {           //選取bar的功能
        Fragment fragment = null;
        FragmentManager sFm = getSupportFragmentManager();

        if(smf.isAdded()){
            sFm.beginTransaction().hide(smf).commit();
        }

        switch (item.getItemId()) {

            case R.id.nav_restaurant:
                fragment = new RestartantFragment();                    //要改主頁的内容去該XXXXFragment改程式碼
                break;

            case R.id.nav_dice:
                fragment = new DiceFragment();                          //要改主頁的内容去該XXXXFragment改程式碼
                break;

            case R.id.nav_map:
                fragment = new MapsFragment();                          //要改主頁的内容去該XXXXFragment改程式碼
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

}
