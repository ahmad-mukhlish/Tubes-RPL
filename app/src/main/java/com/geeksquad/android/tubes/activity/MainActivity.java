package com.geeksquad.android.tubes.activity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.adapter.OrderRecycleAdapter;
import com.geeksquad.android.tubes.entity.Order;
import com.geeksquad.android.tubes.networking.udacity.OrderLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Order>> {
    private static final int LOADER_ID = 54;
    private OrderRecycleAdapter mOrderRecycleAdapter;
    public static List<Order> mOrders = null;
    private RecyclerView mRecyclerView;
    private ActionBar mActionBar;
    private LinearLayout mLoading;
    private ru.shmakinv.android.widget.material.searchview.SearchView mSearchView;
    private SwipeRefreshLayout mSwipe;
    private LoaderManager mLoaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOrders = null;

        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mLoading = (LinearLayout) findViewById(R.id.loading);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvOrder);
        LinearLayout error = (LinearLayout) findViewById(R.id.error);


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        ConnectivityManager mConnectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        error.setVisibility(View.GONE);


        mActionBar = getSupportActionBar();
        mActionBar.hide();

        if (isConnected) {
            mLoaderManager = getLoaderManager();
            mLoaderManager.initLoader(LOADER_ID, null, this);
        } else {
            error.setVisibility(View.VISIBLE);
        }

        mSearchView = ru.shmakinv.android.widget.material.searchview.SearchView.getInstance(this);
        mSearchView.setSuggestionAdapter(mOrderRecycleAdapter);
        mSearchView.setOnQueryTextListener(new ru.shmakinv.android.widget.material.searchview.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(@NonNull String query) {
                List<Order> selectedOrders = new ArrayList<Order>();
                for (Order currentOrder : mOrders) {
                    if ((currentOrder.getMeja() + "").toLowerCase().contains(query.toLowerCase())) {
                        selectedOrders.add(currentOrder);
                    }
                }

                mOrderRecycleAdapter.setFilter(selectedOrders);
                return true;
            }

            @Override
            public void onQueryTextChanged(@NonNull String newText) {

            }
        });

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mOrderRecycleAdapter.setFilter(mOrders);
                mSwipe.setRefreshing(false);
            }
        });

    }


    @Override
    public Loader<List<Order>> onCreateLoader(int i, Bundle bundle) {
        return new OrderLoader(this, Order.BASE_PATH + Order.JSON_REPLY_KOKI);

    }

    @Override
    public void onLoadFinished(Loader<List<Order>> loader, List<Order> orders) {
        if (mOrders == null || mOrders.isEmpty()) {
            mOrders = orders;
            updateUI(mOrders);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Order>> loader) {
    }

    public void updateUI(List<Order> orderList) {
        mActionBar.show();
        mLoading.setVisibility(View.GONE);
        mOrderRecycleAdapter = new OrderRecycleAdapter(this, orderList);
        mRecyclerView.setAdapter(mOrderRecycleAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.search:
                mSearchView.onOptionsItemSelected(getFragmentManager(), item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOrders = null;

    }
}
