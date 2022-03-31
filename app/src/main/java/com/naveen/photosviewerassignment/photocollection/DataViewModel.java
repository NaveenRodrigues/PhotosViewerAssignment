package com.naveen.photosviewerassignment.photocollection;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.naveen.photosviewerassignment.photocollection.pojo.Hit;
import com.naveen.photosviewerassignment.repository.Repository;

import java.util.List;

public class DataViewModel extends AndroidViewModel {

    Repository repository = new Repository();

    MediatorLiveData<List<Hit>> mDataDetails = new MediatorLiveData<>();

    public DataViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Hit>> getPhotoData(){

        repository.getData(new DataListener() {
            @Override
            public void onDataFetched(List<Hit> data) {
                mDataDetails.postValue(data);
            }
        });
        return mDataDetails;
    }

}


