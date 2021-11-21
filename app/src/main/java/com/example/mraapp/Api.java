package com.example.mraapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit = null;
    public static ApiService getService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.mra.mw/sandbox/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        ApiService service = retrofit.create(ApiService.class);
        return service;
    }
}
