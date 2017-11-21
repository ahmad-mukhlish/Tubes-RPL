package com.geeksquad.android.tubes.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.entity.Detail;

import java.util.List;


/**
 * Created by GOODWARE1 on 9/2/2017.
 */

public class DetailRecycleAdapter extends RecyclerView.Adapter<DetailRecycleAdapter.OrderViewHolder> {

    private Context mContext;
    private List<Detail> mDetails;

    public DetailRecycleAdapter(Context mContext, List<Detail> details) {
        this.mContext = mContext;
        this.mDetails = details;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.each_order_detail, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final OrderViewHolder holder, int position) {
        final Detail currentDetail = mDetails.get(position);
        holder.mJudul.setText(currentDetail.getProduk());
        holder.mQty.setText(currentDetail.getQty() + "");
        holder.mCheckDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDetail.setDone(holder.mCheckDone.isChecked());
            }
        });

        if (position % 2 != 0)
        {
            holder.mItemView.setBackgroundColor(Color.rgb(255,255,255));
        }


    }

    @Override
    public int getItemCount() {
        return mDetails.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView mJudul, mQty;
        View mItemView;
        CheckBox mCheckDone;


        OrderViewHolder(View itemView) {
            super(itemView);
            mJudul = itemView.findViewById(R.id.judul);
            mQty = itemView.findViewById(R.id.qty);
            mCheckDone = itemView.findViewById(R.id.check_done);
            mItemView = itemView;
        }


    }


}