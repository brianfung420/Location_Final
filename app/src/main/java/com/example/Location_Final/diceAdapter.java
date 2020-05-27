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

    private HashMap<String,String> mData;

    diceAdapter(HashMap<String,String> data) {
        mData = data;
    }

    // 建立ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{
        // 宣告元件
        private TextView nameItem;
        private TextView descrItem;

        ViewHolder(View itemView) {
            super(itemView);
            nameItem = (TextView) itemView.findViewById(R.id.dice_name_Item);
            descrItem = (TextView) itemView.findViewById(R.id.dice_descr_Item);

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
        // 設置txtItem要顯示的內容
        for (int i = 0;i<mData.size()/2;i++){
            holder.nameItem.setText(mData.get("name"+i));
            holder.descrItem.setText(mData.get("descr"+i));
            Log.d("RecycleView","mes:"+mData.get("name"+i)+mData.get("descr"+i));
        }
        Log.d("RecycleView","HashMap Length:"+mData.size());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
