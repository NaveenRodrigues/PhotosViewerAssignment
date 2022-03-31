//package com.naveen.photosviewerassignment;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.mockito.ArgumentCaptor;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//import android.app.Application;
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.Observer;
//
//import com.naveen.photosviewerassignment.photocollection.DataListener;
//import com.naveen.photosviewerassignment.photocollection.DataViewModel;
//import com.naveen.photosviewerassignment.repository.Repository;
//import com.naveen.photosviewerassignment.photocollection.pojo.Hit;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Example local unit test, which will execute on the development machine (host).
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//public class ExampleUnitTest {
//    @Rule
//    public InstantTaskExecutorRule instantTaskExecutorRule =
//            new InstantTaskExecutorRule();
//
//    @Test
//    public void addition_isCorrect() {
//        Repository mockRepository = mock(Repository.class);
//        DataViewModel dataViewModel = new DataViewModel(new Application());
//        dataViewModel.repository = mockRepository;
//        List<Hit> data;
//        data = new ArrayList<>();
//        data.add(new Hit());
//
//        List<Hit> data1;
//        data1 = new ArrayList<>();
//        data1.add(new Hit());
//
//        LiveData<List<Hit>> mDataDetails = dataViewModel.getPhotoData();
//        mDataDetails.observeForever(new Observer<List<Hit>>() {
//            @Override
//            public void onChanged(List<Hit> hits) {
//
//            }
//        });
//        ArgumentCaptor<DataListener> captor = ArgumentCaptor.forClass(DataListener.class);
//        verify(mockRepository).getData(captor.capture());
//        DataListener listener = captor.getValue();
//        listener.onDataFetched(data);
//        assertEquals(mDataDetails.getValue(),data1);
//    }
//
//    public void testSomething(){
//
//    }
//}