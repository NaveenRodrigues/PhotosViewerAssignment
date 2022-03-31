package com.naveen.photosviewerassignment.repository;

import android.util.Log;

import com.naveen.photosviewerassignment.photocollection.DataListener;
import com.naveen.photosviewerassignment.photocollection.pojo.RootData;
import com.naveen.photosviewerassignment.photocollection.pojo.Hit;

import java.util.ArrayList;
import java.util.List;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final String BASEURL = " https://pixabay.com";


    public void getData(DataListener listner) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotosAPI Api = retrofit.create(PhotosAPI.class);
        Call<RootData> call = Api.getFeeds();
        call.enqueue(new Callback<RootData>() {
            @Override
            public void onResponse(Call<RootData> call, Response<RootData> response) {
                if (response.isSuccessful()) {
                    List<Hit> photoData;
                    RootData data;
                    data = response.body();
                    Log.d("Sucess", "data" + data);
                    if (data != null) {
                        photoData = data.hits;
                        listner.onDataFetched(photoData);
                    } else {
                        listner.onDataFetched(new ArrayList<>());
                    }

                } else {
                    listner.onDataFetched(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<RootData> call, Throwable t) {
                listner.onDataFetched(new ArrayList<>());
            }
        });

    }
}
