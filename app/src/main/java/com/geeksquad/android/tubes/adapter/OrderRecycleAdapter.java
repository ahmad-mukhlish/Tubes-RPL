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

/**
 * Created by ASUS on 25/10/2017.
 */

public class OrderRecycleAdapter extends RecyclerView.Adapter<OrderRecycleAdapter.OrderViewHolder> {

    private Context mContext;
    private List<Order> mOrders;

    public OrderRecycleAdapter(Context mContext, List<Order> mOrders) {
        this.mContext = mContext;
        this.mOrders = mOrders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_order, parent, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(view);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order orderNow = mOrders.get(position);
        holder.noTable.setText("Meja " + orderNow.getMeja());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        holder.dateOrder.setText(getFormattedDate(orderNow.getTanggal(),df));
        holder.items.setText(orderNow.getItems() + " items");
        holder.rootView.setOnClickListener(new DetailListener(position));

    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView noTable, dateOrder, items;
        View rootView;

        public OrderViewHolder(View itemView) {
            super(itemView);
            noTable = itemView.findViewById(R.id.no_table);
            dateOrder = itemView.findViewById(R.id.date_order);
            items = itemView.findViewById(R.id.items);
            rootView = itemView;
        }
    }

    public void setFilter(List<Order> selectedFilms) {

        mOrders = new ArrayList<Order>();
        mOrders.addAll(selectedFilms);
        notifyDataSetChanged();
    }

    private class DetailListener implements View.OnClickListener {


        private int position;

        DetailListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {


            if (position == 0) {
                Order clickedOrder = mOrders.get(position);
                Intent intent = new Intent(mContext, MakananActivity.class);
                intent.putExtra("no_meja", clickedOrder.getMeja());
                intent.putExtra("tanggal", clickedOrder.getTanggal());
                intent.putExtra("items", clickedOrder.getItems());
                intent.putExtra("keterangan", clickedOrder.getCatatan());
                intent.putExtra("makanan", (ArrayList<Makanan>) clickedOrder.getListmakanan());
                view.getContext().startActivity(intent);
            } else {
                Toast.makeText(view.getContext(), "Silakan kerjakan yang paling atas dahulu..", Toast.LENGTH_SHORT).show();

            }
        }
    }


    private String getFormattedDate(String stringDate, SimpleDateFormat sdf) {
        SimpleDateFormat output = new SimpleDateFormat("HH:mm:ss",Locale.ENGLISH);
        Date date = new Date() ;

        try {
            date = sdf.parse(stringDate);
        } catch (java.text.ParseException e) {
            Log.e("apa", "jelly", e);
        }


        return output.format(date);
    }

}
