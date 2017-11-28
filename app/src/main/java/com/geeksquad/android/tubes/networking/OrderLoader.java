package com.geeksquad.android.tubes.networking;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.geeksquad.android.tubes.activity.MainActivity;
import com.geeksquad.android.tubes.entity.Order;

import java.util.List;


public class OrderLoader extends AsyncTaskLoader<List<Order>> {

    private final String LOG_TAG = OrderLoader.class.getName();

    private String mUrl;

    public OrderLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        if (MainActivity.mOrders == null)
            forceLoad();
    }

    @Override
    public List<Order> loadInBackground() {
        return QueryUtils.fetchData(mUrl) ;
    }

}