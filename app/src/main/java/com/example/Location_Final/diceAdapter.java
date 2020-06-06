package com.example.Location_Final;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

class diceAdapter extends RecyclerView.Adapter<diceAdapter.ViewHolder> {

    private List<HashMap<String,String>> mData;

    diceAdapter(List<HashMap<String,String>> data) {
        mData = data;
    }

    // 建立ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{
        // 宣告元件
        private TextView nameItem;

        ViewHolder(View itemView) {
            super(itemView);
            nameItem = (TextView) itemView.findViewById(R.id.dice_name_Item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 連結項目布局檔list_item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dice_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap<String,String> temp = mData.get(position);
        holder.nameItem.setText(temp.get("name"));
        Log.d("RecycleView","Now Item:"+position+" Name:"+temp.get("name"));
    }

    @Override
    public int getItemCount() {
        //決定了RecyclerView的長度
        return mData.size();
    }

}
