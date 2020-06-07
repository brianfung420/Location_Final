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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private List<HashMap<String,String>> restartant_info;

    public MapsFragment() {
        // Required empty public constructor
        restartant_info  = new ArrayList<HashMap<String, String>>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadMapsInfo loadMapsInfo = new LoadMapsInfo(24.179231,120.649661);

        restartant_info = loadMapsInfo.excute_PlacesTask();

        if(restartant_info.isEmpty()){
            Log.d("OnCreate","Is null");
            restartant_info  = new ArrayList<HashMap<String, String>>();
            HashMap<String,String> temp = new HashMap<String,String>();

            temp.put("name","Little Tibet");
            temp.put("lat","24.175309");
            temp.put("long","120.64681");
            restartant_info.add(0,temp);

            temp = new HashMap<String,String>();
            temp.put("name","Mr. Chicken Head");
            temp.put("lat","24.181797");
            temp.put("long","120.644607");
            restartant_info.add(1,temp);

            temp = new HashMap<String,String>();
            temp.put("name","Cafe Buddha 佈達咖啡");
            temp.put("lat","24.17674199999999");
            temp.put("long","120.645502");
            restartant_info.add(2,temp);

            temp = new HashMap<String,String>();
            temp.put("name","Yixin Vegetarian Stinky Tofu");
            temp.put("lat","24.1778562");
            temp.put("long","120.6451825");
            restartant_info.add(3,temp);

            temp = new HashMap<String,String>();temp.put("name","Mr.38 The Beat Curry");
            temp.put("lat","24.1769469");
            temp.put("long","120.6433179");
            restartant_info.add(4,temp);

            temp = new HashMap<String,String>();temp.put("name","樂樂和風食堂-台中青海店");
            temp.put("lat","24.171686");
            temp.put("long","120.643981");
            restartant_info.add(5,temp);

            temp = new HashMap<String,String>();temp.put("name","官芝霖大腸包小腸");
            temp.put("lat","24.1788954");
            temp.put("long","120.6456488");
            restartant_info.add(6,temp);

            temp = new HashMap<String,String>();temp.put("name","In Sky Hotel");
            temp.put("lat","24.1828703");
            temp.put("long","120.6450211");
            restartant_info.add(7,temp);

            temp = new HashMap<String,String>();temp.put("name","梅香小吃");
            temp.put("lat","24.180263");
            temp.put("long","120.645765");
            restartant_info.add(8,temp);

            temp = new HashMap<String,String>();temp.put("name","陶板屋 台中河南店");
            temp.put("lat","24.1715678");
            temp.put("long","120.6447569");
            restartant_info.add(9,temp);

            temp = new HashMap<String,String>();
            temp.put("name","Plant Plan");
            temp.put("lat","24.1711714");
            temp.put("long","120.6476609");
            restartant_info.add(10,temp);

            temp = new HashMap<String,String>();
            temp.put("name","联亭泡菜鍋-逢甲店");
            temp.put("lat","24.1827307");
            temp.put("long","120.6447184");
            restartant_info.add(11,temp);

            temp = new HashMap<String,String>();
            temp.put("name","三號創意料理廚房");
            temp.put("lat","24.1765332");
            temp.put("long","120.6446064");
            restartant_info.add(12,temp);

            temp = new HashMap<String,String>();
            temp.put("name","激旨燒き鳥Gekiuma Yakitori 台灣總店");
            temp.put("lat","24.1831289");
            temp.put("long","120.6466054");
            restartant_info.add(13,temp);

            temp = new HashMap<String,String>();
            temp.put("name","龍涎居雞膳食坊 台中逢甲店");
            temp.put("lat","24.1795836");
            temp.put("long","120.6451754");
            restartant_info.add(14,temp);

            temp = new HashMap<String,String>();
            temp.put("name","長谷川壽司專賣店");
            temp.put("lat","24.1715984");
            temp.put("long","120.64594");
            restartant_info.add(15,temp);

            temp = new HashMap<String,String>();
            temp.put("name","台南鍋燒麵");
            temp.put("lat","24.1755365");
            temp.put("long","120.6449622");
            restartant_info.add(16,temp);

            temp = new HashMap<String,String>();
            temp.put("name","鍋神日式涮涮鍋");
            temp.put("lat","24.1835551");
            temp.put("long","120.6446369");
            restartant_info.add(17,temp);

            temp = new HashMap<String,String>();
            temp.put("name","韓石館");
            temp.put("lat","24.1767214");
            temp.put("long","120.6426575");
            restartant_info.add(18,temp);

            temp = new HashMap<String,String>();
            temp.put("name","月島文字燒 (台中逢甲店)");
            temp.put("lat","24.174742");
            temp.put("long","120.6468268");
            restartant_info.add(19,temp);

            temp = new HashMap<String,String>();
            temp.put("name","七味厨坊");
            temp.put("lat","24.179503");
            temp.put("long","120.646416");
            restartant_info.add(20,temp);

            temp = new HashMap<String,String>();
            temp.put("name","無名麻辣鴨血 逢甲店");
            temp.put("lat","24.181244");
            temp.put("long","120.646336");
            restartant_info.add(21,temp);
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

        Log.d("MapsFragmet","list size:"+restartant_info.size());

        // Add a marker in Sydney and move the camera
        LatLng FengChia = new LatLng(24.178843,120.646538);
        BitmapDescriptor descriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);    //設置標記點
        mMap.addMarker(new MarkerOptions().position(FengChia).title("逢甲大學").icon(descriptor).snippet("逢甲大學的地標"));                  //.icon 加入標記點資訊
        for(int i=0;i<restartant_info.size();i++){
            HashMap<String,String> temp = restartant_info.get(i);
            Double lat = Double.parseDouble(temp.get("lat"));
            Double lng = Double.parseDouble(temp.get("long"));
            LatLng restartant = new LatLng(lat,lng);
            String name = temp.get("name");
            Log.d("MapsFragmet","i:"+i+" name:"+name + " lat:"+lat +" lng:"+lng);
            mMap.addMarker(new MarkerOptions().position(restartant).title(name).icon(descriptor));
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
