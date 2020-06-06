package com.example.Location_Final;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    HashMap<String,String> restartant_info = new HashMap<>();

    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadMapsInfo loadMapsInfo = new LoadMapsInfo();
        restartant_info = loadMapsInfo.excute_PlacesTask();

        if(restartant_info.isEmpty()){
            Log.d("OnCreate","Is null");
            restartant_info.put("name"+"0","127");
            restartant_info.put("descr"+"0","咖喱飯");
            restartant_info.put("lat"+"0","24.180014");
            restartant_info.put("long"+"0","120.645990");


            restartant_info.put("name"+"1","七味厨坊");
            restartant_info.put("descr"+"1","便當");
            restartant_info.put("lat"+"1","24.179503");
            restartant_info.put("long"+"1","120.646416");


            restartant_info.put("name"+"2","無名麻辣鴨血 逢甲店");
            restartant_info.put("descr"+"2","鴨血");
            restartant_info.put("lat"+"2","24.181244");
            restartant_info.put("long"+"2","120.646336");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_maps, container, false);
        mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        if(mapFragment==null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map,mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng FengChia = new LatLng(24.178843,120.646538);
        BitmapDescriptor descriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);    //設置標記點
        mMap.addMarker(new MarkerOptions().position(FengChia).title("逢甲大學").icon(descriptor).snippet("逢甲大學的地標"));                  //.icon 加入標記點資訊
        for(int i=0;i<restartant_info.size()/4;i++){
            LatLng restartant = new LatLng(Double.parseDouble(restartant_info.get("lat"+i)),Double.parseDouble(restartant_info.get("long"+i)));
            String name = restartant_info.get("name"+i);
            String descr = restartant_info.get("descr"+i);
            mMap.addMarker(new MarkerOptions().position(restartant).title(name).icon(descriptor).snippet(descr));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(FengChia));

        CircleOptions circleOptions = new CircleOptions().center(FengChia).radius(1000);    //設置圓的資料
        mMap.addCircle(circleOptions);                          //畫圓圈

        mMap.getUiSettings().setZoomControlsEnabled(true);      //右下角的放大縮小功能
        mMap.getUiSettings().setCompassEnabled(true);           //左上角的指南針，要兩指旋轉才會出現
        mMap.getUiSettings().setMapToolbarEnabled(true);        //右下角的導覽及開啓 Google Map功能

        mMap.animateCamera(CameraUpdateFactory.zoomTo(16)); //放大

    }
}
