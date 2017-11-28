package com.geeksquad.android.tubes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.activity.MakananActivity;
import com.geeksquad.android.tubes.entity.Makanan;
import com.geeksquad.android.tubes.entity.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderRecycleAdapter extends RecyclerView.Adapter<OrderRecycleAdapter.OrderViewHolder> {

    private final String LOG_TAG = OrderRecycleAdapter.class.getName();

    private Context mContext;
    private List<Order> mOrders;

    public OrderRecycleAdapter(Context mContext, List<Order> mOrders) {
        this.mContext = mContext;
        this.mOrders = mOrders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order orderNow = mOrders.get(position);
        holder.mNoTable.setText(mContext.getString(R.string.table) + " " + orderNow.getmMeja());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        holder.mDateOrder.setText(getFormattedDate(orderNow.getmTanggal(), df));
        holder.mItems.setText(orderNow.getmItems() + " items");
        holder.mRootView.setOnClickListener(new DetailListener(position));

    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView mNoTable, mDateOrder, mItems;
        private View mRootView;

        public OrderViewHolder(View itemView) {
            super(itemView);
            mNoTable = itemView.findViewById(R.id.no_table);
            mDateOrder = itemView.findViewById(R.id.date_order);
            mItems = itemView.findViewById(R.id.items);
            mRootView = itemView;
        }
    }

    public void setFilter(List<Order> selectedFilms) {

        mOrders = new ArrayList<>();
        mOrders.addAll(selectedFilms);
        notifyDataSetChanged();
    }

    private class DetailListener implements View.OnClickListener {

        private int mPosition;

        DetailListener(int position) {
            this.mPosition = position;
        }

        @Override
        public void onClick(View view) {


            if (mPosition == 0) {
                Order clickedOrder = mOrders.get(mPosition);
                Intent intent = new Intent(mContext, MakananActivity.class);
                intent.putExtra("no_meja", clickedOrder.getmMeja());
                intent.putExtra("tanggal", clickedOrder.getmTanggal());
                intent.putExtra("items", clickedOrder.getmItems());
                intent.putExtra("keterangan", clickedOrder.getmCatatan());
                intent.putExtra("makanan", (ArrayList<Makanan>) clickedOrder.getListmakanan());
                view.getContext().startActivity(intent);
            } else {
                Toast.makeText(view.getContext(), R.string.up, Toast.LENGTH_SHORT).show();

            }
        }
    }


    private String getFormattedDate(String stringDate, SimpleDateFormat sdf) {
        SimpleDateFormat output = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        Date date = new Date();

        try {
            date = sdf.parse(stringDate);
        } catch (java.text.ParseException e) {
            Log.e(LOG_TAG, "Error when parse date", e);
        }


        return output.format(date);
    }

}
