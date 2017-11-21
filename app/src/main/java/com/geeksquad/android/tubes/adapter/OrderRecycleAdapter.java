package com.geeksquad.android.tubes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.activity.DetailActivity;
import com.geeksquad.android.tubes.entity.Detail;
import com.geeksquad.android.tubes.entity.Order;

import java.util.ArrayList;
import java.util.List;

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
        holder.noTable.setText("Meja " + orderNow.getNo_meja());
        holder.dateOrder.setText(orderNow.getTanggal());
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
            Order clickedOrder = mOrders.get(position);
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("no_meja", clickedOrder.getNo_meja());
            intent.putExtra("tanggal", clickedOrder.getTanggal());
            intent.putExtra("items", clickedOrder.getItems());
            intent.putExtra("keterangan", clickedOrder.getKeterangan());
            intent.putExtra("detail", (ArrayList<Detail>) clickedOrder.getDetail());
            view.getContext().startActivity(intent);
        }
    }
}
