package com.naveen.photosviewerassignment.repository;

import com.naveen.photosviewerassignment.photocollection.pojo.RootData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotosAPI {
    final String APIKEY = "/api/?key=26327107-848ef86bc7c30ce73dae7674a&q=yellow+flowers&image_type=photo";
    @GET(APIKEY)
    Call<RootData>  getFeeds();
}
