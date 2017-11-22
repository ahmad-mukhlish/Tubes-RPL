package com.geeksquad.android.tubes.networking.retrofit;


import com.geeksquad.android.tubes.entity.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hiros on 10/23/2017.
 */

public interface ApiInterface {
    @GET(Order.JSON_REPLY_KOKI)
    Call<List<Order>> getOrder();
}
