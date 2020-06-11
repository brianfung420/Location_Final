package com.example.Location_Final;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class restAdapter extends RecyclerView.Adapter<restAdapter.ViewHolder> {

    private ArrayList<HashMap<String,String>> mData;

    restAdapter(ArrayList<HashMap<String,String>> data) {
        this.mData = data;
        Log.d("restAda","data size:"+mData.size());
    }

    // 建立ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameItem;
        TextView descItem;
        ViewHolder(View itemView) {
            super(itemView);
            nameItem = (TextView) itemView.findViewById(R.id.rest_item_name);
            descItem = (TextView) itemView.findViewById(R.id.rest_item_desc);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 連結項目布局檔list_item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rest_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String,String> temp = mData.get(position);
        Log.d("restAda","data size:"+getItemCount() + " position:"+position);
        holder.nameItem.setText(temp.get("name"));
        holder.descItem.setText(temp.get("desc"));
        Log.d("RecycleView","Now Item:"+position+" Name:"+temp.get("name")+" Desc:"+temp.get("desc"));
    }

    @Override
    public int getItemCount() {
        //決定了RecyclerView的長度
        return mData.size();
    }

}
