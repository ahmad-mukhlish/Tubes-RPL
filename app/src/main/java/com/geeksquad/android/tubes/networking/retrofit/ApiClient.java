package com.geeksquad.android.tubes.networking.retrofit;

import com.geeksquad.android.tubes.entity.Order;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hiros on 10/23/2017.
 */

public class ApiClient {
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Order.BASE_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
