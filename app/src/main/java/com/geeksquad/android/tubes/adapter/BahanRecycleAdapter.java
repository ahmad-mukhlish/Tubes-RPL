package com.geeksquad.android.tubes.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.entity.Bahan;

import java.util.List;


public class BahanRecycleAdapter extends RecyclerView.Adapter<BahanRecycleAdapter.OrderViewHolder> {

    private final String LOG_TAG = BahanRecycleAdapter.class.getName();


    private Context mContext;
    private List<Bahan> mBahans;

    public BahanRecycleAdapter(Context mContext, List<Bahan> mBahans) {
        this.mContext = mContext;
        this.mBahans = mBahans;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.each_bahan, parent, false);
        return new OrderViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {

        Bahan currentBahan = mBahans.get(position);
        holder.mIngredient.setText(currentBahan.getmNamaBahan());
        holder.mGram.setText(currentBahan.getmJumlahBahan() + "");

        if (position % 2 != 0) {
            holder.mItemView.setBackgroundColor(Color.rgb(255, 255, 255));
        }

    }


    @Override
    public int getItemCount() {
        return mBahans.size();
    }


    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView mIngredient, mGram;
        View mItemView;

        OrderViewHolder(View itemView) {
            super(itemView);
            mIngredient = itemView.findViewById(R.id.ingredient);
            mGram = itemView.findViewById(R.id.gram);
            mItemView = itemView;
        }
    }

}
