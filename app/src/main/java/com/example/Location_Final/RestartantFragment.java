package com.example.Location_Final;

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

import java.util.ArrayList;
import java.util.HashMap;

public class RestartantFragment extends Fragment {

    private Button btn_rest;
    private RecyclerView recyclerView;
    private ArrayList<HashMap<String,String>> mData;
    private restAdapter adapter;

    RestartantFragment(){
        mData = new ArrayList<HashMap<String, String>>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_restaurant,null);

        btn_rest = RootView.findViewById(R.id.btn_rest);

        recyclerView = RootView.findViewById(R.id.Rest_RecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new restAdapter(mData);
        btn_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> temp = new HashMap<String,String>();
                temp.put("name","餐廳名字");
                temp.put("desc","餐廳描述");
                int count = mData.size();
                Log.d("R_Fragment","insert number:"+count);
                mData.add(count,temp);
                recyclerView.setAdapter(adapter);
            }
        });
        return RootView;
    }

}
