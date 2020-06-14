package com.example.Location_Final;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private EditText rest_name,rest_desc;

    RestartantFragment(){
        mData = new ArrayList<HashMap<String, String>>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_restaurant,null);

        btn_rest = RootView.findViewById(R.id.btn_rest);

        rest_name = RootView.findViewById(R.id.edit_rest);
        rest_desc = RootView.findViewById(R.id.edit_desc);

        recyclerView = RootView.findViewById(R.id.Rest_RecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new restAdapter(mData);
        btn_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = rest_name.getText().toString();
                String desc = rest_desc.getText().toString();
                String nulll = "";
                if(name.equals(nulll) || desc.equals(nulll)){
                    Toast.makeText(getContext(),"請輸入完整資料",Toast.LENGTH_LONG).show();
                }
                else{
                    HashMap<String,String> temp = new HashMap<String, String>();
                    temp.put("name",name);
                    temp.put("desc",desc);
                    int count = mData.size();
                    Log.d("R_Fragment","insert number:"+count);
                    mData.add(count,temp);
                    recyclerView.setAdapter(adapter);
                    rest_name.setText("");
                    rest_desc.setText("");
                }

            }
        });
        return RootView;
    }

}
