package com.naveen.photosviewerassignment.photocollection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.naveen.photosviewerassignment.photocollection.pojo.Hit;
import com.naveen.photosviewerassignment.repository.Repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

public class DataViewModelTest {
    Repository mockRepository;
    DataViewModel dataViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    @Before public void setup(){
        mockRepository = mock(Repository.class);
        dataViewModel = new DataViewModel(new Application());
        dataViewModel.repository = mockRepository;
    }

    @Test
    public void getPhotoDataTest() {

        List<Hit> data;
        data = new ArrayList<>();
        data.add(new Hit());

        List<Hit> data1;
        data1 = new ArrayList<>();
        data1.add(new Hit());

        LiveData<List<Hit>> mDataDetails = dataViewModel.getPhotoData();
        mDataDetails.observeForever(new Observer<List<Hit>>() {
            @Override
            public void onChanged(List<Hit> hits) {

            }
        });
        ArgumentCaptor<DataListener> captor = ArgumentCaptor.forClass(DataListener.class);
        verify(mockRepository).getData(captor.capture());
        DataListener listener = captor.getValue();
        listener.onDataFetched(data);
        assertEquals(mDataDetails.getValue(),data);
    }

}
