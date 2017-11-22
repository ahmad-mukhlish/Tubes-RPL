package com.geeksquad.android.tubes.activity;

import android.content.Context;
import android.content.Intent;
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

import java.util.List;

public class MakananActivity extends AppCompatActivity {

    List<Makanan> mMakanans;
    int mItems;
    Bundle mBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan);

        mBundle = getIntent().getExtras();
        mMakanans = mBundle.getParcelableArrayList("makanan");
        mItems = mBundle.getInt("items");


        MakananRecycleAdapter makananRecycleAdapter =
                new MakananRecycleAdapter(this, mMakanans);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvMakanan);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(makananRecycleAdapter);


        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new doneListener(this));

        setTitle("Pesanan Meja " + mBundle.getInt("no_meja"));


    }

    private class doneListener implements View.OnClickListener {

        private Context mContext;

        public doneListener(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public void onClick(View view) {
            int checkDetail = 0;

            for (Makanan makananNow : mMakanans) {

                if (makananNow.isDone()) {
                    checkDetail++;
                }

            }

            if (checkDetail == mItems) {

                //TODO make database delete stuff here via retrofit before intent back to MainActivity
                //TODO make notif to waiter


                startActivity(new Intent(mContext, MainActivity.class));


            } else {

                Toast.makeText(mContext, "Ada item yang belum selesai...", Toast.LENGTH_SHORT).show();

            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.note:
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

                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
