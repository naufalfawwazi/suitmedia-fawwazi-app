package com.fawwazi.suitmediafawwaziapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public final static String url_base="https://reqres.in/";
    public static Retrofit retrofit;

    public static Retrofit Connect()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(url_base)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
