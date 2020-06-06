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

    private HashMap<String,String> mData = new HashMap<>();
    private RecyclerView recyclerView;
    private diceAdapter adapter;
    private Button btn_dice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_dice,null);

        btn_dice = RootView.findViewById(R.id.btn_diceRandom);

        recyclerView = RootView.findViewById(R.id.Dice_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LoadMapsInfo loadMapsInfo = new LoadMapsInfo();
        mData = loadMapsInfo.excute_PlacesTask();

        if(mData.isEmpty()) {
            Log.d("OnCreate","Is null");
            mData.put("name"+"0","127");
            mData.put("descr"+"0","咖喱飯");

            mData.put("name"+"1","七味");
            mData.put("descr"+"1","便當");

            mData.put("name"+"2","123");
            mData.put("descr"+"2","便當");

            mData.put("name"+"3","456");
            mData.put("descr"+"3","便當");

            mData.put("name"+"4","789");
            mData.put("descr"+"4","便當");

            mData.put("name"+"5","159");
            mData.put("descr"+"5","便當");

            mData.put("name"+"6","753");
            mData.put("descr"+"6","便當");
        }

        // 將資料交給adapter
        adapter = new diceAdapter(mData);
        // 設置adapter給recycler_view
        recyclerView.setAdapter(adapter);

        btn_dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int count = random.nextInt((mData.size()/2));
                Log.d("btn_dice","Clicked,Random Number:"+count+" ,mData.size():"+mData.size());
            }
        });

        return RootView;
    }

}
