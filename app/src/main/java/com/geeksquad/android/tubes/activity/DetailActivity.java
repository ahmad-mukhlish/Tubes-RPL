package com.geeksquad.android.tubes.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.adapter.DetailRecycleAdapter;
import com.geeksquad.android.tubes.entity.Detail;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    List<Detail> mDetail;
    int mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        mDetail = bundle.getParcelableArrayList("detail");
        mItems = bundle.getInt("items");

        DetailRecycleAdapter billingRecycleAdapter =
                new DetailRecycleAdapter(this, mDetail);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvBilling);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(billingRecycleAdapter);


        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new doneListener(this));


    }

    private class doneListener implements View.OnClickListener {

        private Context mContext;

        public doneListener(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public void onClick(View view) {
            int checkDetail = 0;

            for (Detail detailNow : mDetail) {

                if (detailNow.isDone()) {
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


}
