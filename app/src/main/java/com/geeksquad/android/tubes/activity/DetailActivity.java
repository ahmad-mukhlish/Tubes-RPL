package com.geeksquad.android.tubes.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.adapter.DetailRecycleAdapter;
import com.geeksquad.android.tubes.entity.Detail;

import java.util.List;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        List<Detail> detail = bundle.getParcelableArrayList("detail");

        DetailRecycleAdapter billingRecycleAdapter =
                new DetailRecycleAdapter(this, detail);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvBilling);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(billingRecycleAdapter);



    }


}
