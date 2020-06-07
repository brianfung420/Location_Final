package com.example.Location_Final;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class DiceFragment extends Fragment {

    double latitude = 120.646538;
    double longitude = 24.178843;

    private List<HashMap<String,String>> mData;
    private RecyclerView recyclerView;
    private diceAdapter adapter;
    private Button btn_dice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_dice,null);

        btn_dice = RootView.findViewById(R.id.btn_diceRandom);

        recyclerView = RootView.findViewById(R.id.Dice_RecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LoadMapsInfo loadMapsInfo = new LoadMapsInfo(latitude,longitude);
        mData = loadMapsInfo.excute_PlacesTask();

        if(mData.isEmpty()) {
            mData = new ArrayList<HashMap<String, String>>();
            Log.d("OnCreate","Is null");
            HashMap<String,String> temp = new HashMap<>();

            temp.put("name","Little Tibet");
            temp.put("lat","24.175309");
            temp.put("long","120.64681");
            mData.add(0,temp);

            temp = new HashMap<String,String>();
            temp.put("name","Mr. Chicken Head");
            temp.put("lat","24.181797");
            temp.put("long","120.644607");
            mData.add(1,temp);

            temp = new HashMap<String,String>();
            temp.put("name","Cafe Buddha 佈達咖啡");
            temp.put("lat","24.17674199999999");
            temp.put("long","120.645502");
            mData.add(2,temp);

            temp = new HashMap<String,String>();
            temp.put("name","Yixin Vegetarian Stinky Tofu");
            temp.put("lat","24.1778562");
            temp.put("long","120.6451825");
            mData.add(3,temp);

            temp = new HashMap<String,String>();temp.put("name","Mr.38 The Beat Curry");
            temp.put("lat","24.1769469");
            temp.put("long","120.6433179");
            mData.add(4,temp);

            temp = new HashMap<String,String>();temp.put("name","樂樂和風食堂-台中青海店");
            temp.put("lat","24.171686");
            temp.put("long","120.643981");
            mData.add(5,temp);

            temp = new HashMap<String,String>();temp.put("name","官芝霖大腸包小腸");
            temp.put("lat","24.1788954");
            temp.put("long","120.6456488");
            mData.add(6,temp);

            temp = new HashMap<String,String>();temp.put("name","In Sky Hotel");
            temp.put("lat","24.1828703");
            temp.put("long","120.6450211");
            mData.add(7,temp);

            temp = new HashMap<String,String>();temp.put("name","梅香小吃");
            temp.put("lat","24.180263");
            temp.put("long","120.645765");
            mData.add(8,temp);

            temp = new HashMap<String,String>();temp.put("name","陶板屋 台中河南店");
            temp.put("lat","24.1715678");
            temp.put("long","120.6447569");
            mData.add(9,temp);

            temp = new HashMap<String,String>();
            temp.put("name","Plant Plan");
            temp.put("lat","24.1711714");
            temp.put("long","120.6476609");
            mData.add(10,temp);

            temp = new HashMap<String,String>();
            temp.put("name","联亭泡菜鍋-逢甲店");
            temp.put("lat","24.1827307");
            temp.put("long","120.6447184");
            mData.add(11,temp);

            temp = new HashMap<String,String>();
            temp.put("name","三號創意料理廚房");
            temp.put("lat","24.1765332");
            temp.put("long","120.6446064");
            mData.add(12,temp);

            temp = new HashMap<String,String>();
            temp.put("name","激旨燒き鳥Gekiuma Yakitori 台灣總店");
            temp.put("lat","24.1831289");
            temp.put("long","120.6466054");
            mData.add(13,temp);

            temp = new HashMap<String,String>();
            temp.put("name","龍涎居雞膳食坊 台中逢甲店");
            temp.put("lat","24.1795836");
            temp.put("long","120.6451754");
            mData.add(14,temp);

            temp = new HashMap<String,String>();
            temp.put("name","長谷川壽司專賣店");
            temp.put("lat","24.1715984");
            temp.put("long","120.64594");
            mData.add(15,temp);

            temp = new HashMap<String,String>();
            temp.put("name","台南鍋燒麵");
            temp.put("lat","24.1755365");
            temp.put("long","120.6449622");
            mData.add(16,temp);

            temp = new HashMap<String,String>();
            temp.put("name","鍋神日式涮涮鍋");
            temp.put("lat","24.1835551");
            temp.put("long","120.6446369");
            mData.add(17,temp);

            temp = new HashMap<String,String>();
            temp.put("name","韓石館");
            temp.put("lat","24.1767214");
            temp.put("long","120.6426575");
            mData.add(18,temp);

            temp = new HashMap<String,String>();
            temp.put("name","月島文字燒 (台中逢甲店)");
            temp.put("lat","24.174742");
            temp.put("long","120.6468268");
            mData.add(19,temp);

            temp = new HashMap<String,String>();
            temp.put("name","七味厨坊");
            temp.put("lat","24.179503");
            temp.put("long","120.646416");
            mData.add(20,temp);

            temp = new HashMap<String,String>();
            temp.put("name","無名麻辣鴨血 逢甲店");
            temp.put("lat","24.181244");
            temp.put("long","120.646336");
            mData.add(21,temp);

        }

        Log.d("DiceFragmet","list size:"+mData.size());
        // 將資料交給adapter
        adapter = new diceAdapter(mData);
        // 設置adapter給recycler_view
        recyclerView.setAdapter(adapter);

        btn_dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int count = random.nextInt((mData.size()));
                Log.d("btn_dice","Clicked,Random Number:"+count+" ,mData.size():"+mData.size());
            }
        });

        return RootView;
    }

}
