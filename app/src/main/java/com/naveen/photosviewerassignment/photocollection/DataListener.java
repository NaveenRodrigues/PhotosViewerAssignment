package com.naveen.photosviewerassignment.photocollection;

import com.naveen.photosviewerassignment.photocollection.pojo.Hit;

import java.util.List;

public interface DataListener {
    public void onDataFetched(List<Hit> data);
}
