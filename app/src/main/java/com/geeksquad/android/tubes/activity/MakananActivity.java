package com.geeksquad.android.tubes.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.adapter.MakananRecycleAdapter;
import com.geeksquad.android.tubes.entity.Makanan;
import com.geeksquad.android.tubes.entity.Order;
import com.geeksquad.android.tubes.networking.QueryUtils;

import java.util.List;

public class MakananActivity extends AppCompatActivity {

    private final String LOG_TAG = MakananActivity.class.getName();

    private Bundle mBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan);

        mBundle = getIntent().getExtras();
        List<Makanan> makanans = mBundle.getParcelableArrayList("makanan");


        MakananRecycleAdapter makananRecycleAdapter =
                new MakananRecycleAdapter(this, makanans);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvMakanan);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(makananRecycleAdapter);


        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new doneListener(this));

        setTitle(getString(R.string.order_table) + " " + mBundle.getInt("noMeja"));

    }

    private class doneListener implements View.OnClickListener {

        private Context mContext;

        doneListener(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public void onClick(View view) {

           int kodePesanan = mBundle.getInt("kodePesanan") ;
           new ConfirmCookedAsyncTask(mContext).execute(Order.BASE_PATH + Order.JSON_CONFIRM + kodePesanan);

        }
    }

    private class ConfirmCookedAsyncTask extends AsyncTask<String, Void, String> {

        private Context mContext;

        ConfirmCookedAsyncTask(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected String doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            QueryUtils.fetchResponse(urls[0]);

            return null;
        }


        @Override
        protected void onPostExecute(String response) {
            Toast.makeText(mContext, R.string.toast_confirm, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(mContext, MainActivity.class));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.note) {
            dialogueKeterangan();
        }

        return super.onOptionsItemSelected(item);
    }


    private void dialogueKeterangan() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View rootDialog = LayoutInflater.from(this).inflate(R.layout.dialogue_keterangan, null);
        TextView keterangan = rootDialog.findViewById(R.id.keterangan);
        keterangan.setText(mBundle.getString("keterangan"));

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
