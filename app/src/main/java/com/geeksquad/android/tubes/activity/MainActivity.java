package com.geeksquad.android.tubes.activity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.adapter.OrderRecycleAdapter;
import com.geeksquad.android.tubes.entity.Order;
import com.geeksquad.android.tubes.networking.OrderLoader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Order>> {

    private final String LOG_TAG = MainActivity.class.getName();

    public static List<Order> mOrders = null;

    private static final int LOADER_ID = 54;
    private OrderRecycleAdapter mOrderRecycleAdapter;
    private RecyclerView mRecyclerView;
    private Toolbar mToolBar;
    private LinearLayout mLoading;
    private ru.shmakinv.android.widget.material.searchview.SearchView mSearchView;
    private SwipeRefreshLayout mSwipe;
    private Drawer mDrawer;
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
                List<Order> selectedOrders = new ArrayList<>();
                for (Order currentOrder : mOrders) {
                    if ((currentOrder.getmMeja() + "").toLowerCase().contains(query.toLowerCase())) {
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
                if (!mOrders.isEmpty())
                    mOrderRecycleAdapter.setFilter(mOrders);
                mLoaderManager.initLoader(LOADER_ID, null, MainActivity.this);
                mSwipe.setRefreshing(false);

            }
        });


        TextView tanggal = (TextView) findViewById(R.id.tanggal);
        tanggal.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("en")).format(Calendar.getInstance().getTime()));

        mToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolBar);
        mToolBar.setVisibility(View.GONE);

        initNavigationDrawer(savedInstanceState);


    }

    private void initNavigationDrawer(Bundle savedInstanceState) {

        PrimaryDrawerItem aboutUs = new PrimaryDrawerItem().
                withIdentifier(1).
                withName(R.string.drawer_about_us)
                .withIcon(R.mipmap.about_us);


        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.drawer_header)
                .withDrawerGravity(Gravity.LEFT)
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
        mToolBar.setVisibility(View.VISIBLE);
        mLoading.setVisibility(View.GONE);

        if (!orderList.isEmpty()) {
            mOrderRecycleAdapter = new OrderRecycleAdapter(this, orderList);
            mRecyclerView.setAdapter(mOrderRecycleAdapter);
        } else {
            View view = findViewById(R.id.no_food);
            view.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }

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

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen())
            mDrawer.closeDrawer();
        else
            Toast.makeText(this, R.string.toast_main_menu, Toast.LENGTH_SHORT).show();
    }

}
