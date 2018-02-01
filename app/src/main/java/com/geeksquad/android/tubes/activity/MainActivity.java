package com.geeksquad.android.tubes.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.adapter.OrderRecycleAdapter;
import com.geeksquad.android.tubes.entity.Order;
import com.geeksquad.android.tubes.networking.QueryUtils;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getName();

    public static List<Order> mOrders = null;

    private RecyclerView mRecyclerView;
    private Toolbar mToolBar;
    private LinearLayout mLoading;
    private SwipeRefreshLayout mSwipe;
    private Drawer mDrawer;
    private View mNoFood;
    private Handler mHandler;


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


        if (isConnected) {
            new OrderAsyncTask().execute(Order.BASE_PATH);
        } else {
            error.setVisibility(View.VISIBLE);
        }

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new OrderAsyncTask().execute(Order.BASE_PATH);
            }
        });


        TextView tanggal = (TextView) findViewById(R.id.tanggal);
        tanggal.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("en")).format(Calendar.getInstance().getTime()));

        mToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolBar.setVisibility(View.GONE);

        TextView title = mToolBar.findViewById(R.id.toolbar_title);
        title.setText("Mr. Broto Chef");


        initNavigationDrawer(savedInstanceState);
        mNoFood = findViewById(R.id.no_food);

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 3000);


    }

    private final Runnable mRunnable = new Runnable() {
        public void run() {
            new OrderAsyncTask().execute(Order.BASE_PATH);
            mHandler.postDelayed(mRunnable, 3000);
        }

    };


    private void initNavigationDrawer(Bundle savedInstanceState) {

        PrimaryDrawerItem aboutUs = new PrimaryDrawerItem().
                withIdentifier(1).
                withName(R.string.drawer_about_us)
                .withIcon(R.mipmap.about_us);


        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.drawer_header)
                .withDrawerGravity(Gravity.START)
                .withSavedInstance(savedInstanceState)
                .withToolbar(mToolBar)
                .withSelectedItem(-1)
                .addDrawerItems(aboutUs, new DividerDrawerItem()
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {

                            case 1: {
                                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                                break;
                            }


                        }

                        return true;
                    }
                })
                .build();


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mDrawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);


    }

    public void dialogueMore(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View rootDialog = LayoutInflater.from(this).inflate(R.layout.dialogue_more, null);

        Button logout = rootDialog.findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer();
                mOrders = null;
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), R.string.toast_logout, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        builder.setView(rootDialog);
        final AlertDialog dialog = builder.create();
        dialog.show();


    }


    public class OrderAsyncTask extends AsyncTask<String, Void, List<Order>> {


        OrderAsyncTask() {
        }


        @Override
        protected List<Order> doInBackground(String... urls) {

            if (urls[0] == null || urls.length < 1)
                return null;


            return QueryUtils.fetchData(urls[0]);
        }

        @Override
        protected void onPostExecute(List<Order> orders) {
            super.onPostExecute(orders);
            updateUI(orders);
            mSwipe.setRefreshing(false);
        }
    }


    public void updateUI(List<Order> orderList) {
        mToolBar.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.GONE);

        OrderRecycleAdapter mOrderRecycleAdapter = new OrderRecycleAdapter(this, orderList);
        mRecyclerView.setAdapter(mOrderRecycleAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);
        mNoFood.setVisibility(View.GONE);

        Log.v("cik", orderList.size() + "");
        if (orderList.size() < 1) {


            mNoFood.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOrders = null;

    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen())
            mDrawer.closeDrawer();
        else
            Toast.makeText(this, R.string.toast_main_menu, Toast.LENGTH_SHORT).show();
    }

}
