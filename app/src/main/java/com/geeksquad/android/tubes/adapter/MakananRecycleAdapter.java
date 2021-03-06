package com.geeksquad.android.tubes.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.entity.Makanan;

import java.util.List;

public class MakananRecycleAdapter extends RecyclerView.Adapter<MakananRecycleAdapter.OrderViewHolder> {

    private final String LOG_TAG = MakananRecycleAdapter.class.getName();

    private Context mContext;
    private List<Makanan> mMakanans;

    public MakananRecycleAdapter(Context mContext, List<Makanan> makanans) {
        this.mContext = mContext;
        this.mMakanans = makanans;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.each_makanan, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final OrderViewHolder holder, int position) {
        final Makanan currentMakanan = mMakanans.get(position);
        holder.mJudul.setText(currentMakanan.getmProduk());
        holder.mQty.setText(currentMakanan.getmQty() + "");


        if (position % 2 != 0) {
            holder.mItemView.setBackgroundColor(Color.rgb(255, 255, 255));
        }

        holder.mItemView.setOnClickListener(new MakananListener(position));

    }

    @Override
    public int getItemCount() {
        return mMakanans.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView mJudul, mQty;
        View mItemView;


        OrderViewHolder(View itemView) {
            super(itemView);
            mJudul = itemView.findViewById(R.id.judul);
            mQty = itemView.findViewById(R.id.qty);
            mItemView = itemView;
        }


    }

    private class MakananListener implements View.OnClickListener

    {

        private int mPosition;

        public MakananListener(int mPosition) {
            this.mPosition = mPosition;
        }

        @Override
        public void onClick(View view) {

            dialogueBahan();

        }


        private void dialogueBahan() {

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            View rootDialog = LayoutInflater.from(mContext).inflate(R.layout.dialogue_bahan, null);


            BahanRecycleAdapter bahanRecycleAdapter =
                    new BahanRecycleAdapter(mContext, mMakanans.get(mPosition).getmBahans());

            RecyclerView recyclerView = rootDialog.findViewById(R.id.rvBahan);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, 1);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(bahanRecycleAdapter);


            builder.setView(rootDialog);
            final AlertDialog dialog = builder.create();
            dialog.show();


            TextView ok = rootDialog.findViewById(R.id.ok);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }


    }


}