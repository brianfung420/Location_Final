package com.example.Location_Final;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;

public class result_dialog extends DialogFragment{
    private static final String TAG = "CustomDialog";
    public LatLng restaurant_coor;
    private String restaurant_name;
    private TextView mActionCancel, mActionOk;
    private TextView result_name;
    private List<HashMap<String,String>> mData;

    public result_dialog(LatLng res_coor, String name) {
        this.restaurant_coor = res_coor;
        this.restaurant_name = name;
    }

    



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        mActionCancel = view.findViewById(R.id.action_cancel);
        mActionOk = view.findViewById(R.id.action_ok);
        result_name = view.findViewById(R.id.textView);
        result_name.setText(restaurant_name);

        mActionCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("action_cancel", "onClick: User decided not to go at the moment");
                getDialog().dismiss();
            }
        });

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMap();
                Log.d("action_ok", "onClick: Navigating user to given coordinates");

            }
        });
        return view;
    }
    
    public void launchMap(){
        //String uriString = String.format("google.navigation:q=%f,%f", restaurant_coor.latitude,restaurant_coor.longitude);
        String uriString = String.format("geo:?q=%f,%f", restaurant_coor.latitude,restaurant_coor.longitude);
        Log.d("action_ok", "lat:"+restaurant_coor.latitude+" lng: " + restaurant_coor.longitude);
        Uri intentUri = Uri.parse(uriString);
        Intent intent = new Intent(Intent.ACTION_VIEW, intentUri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);

    }


}
