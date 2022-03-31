package com.naveen.photosviewerassignment.photocollection;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.naveen.photosviewerassignment.R;
import com.naveen.photosviewerassignment.currentphoto.ItemClickListner;
import com.naveen.photosviewerassignment.currentphoto.SelectedPictureFragment;
import com.naveen.photosviewerassignment.photocollection.pojo.Hit;

import java.util.ArrayList;
import java.util.List;


public class PictureCollectionFragment extends Fragment {
    RecyclerViewAdapter imageRVAdapter;
    private RecyclerView imageRecyclerView;
    private List<Hit> data;
    public static final String INTENT_KEY_ARTICLE = "dataKey";
    DataViewModel mDataViewModel;
    SwipeRefreshLayout swipeRefresh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        imageRVAdapter = new RecyclerViewAdapter(data, new ItemClickListner() {
            @Override
            public void onItemClick(Hit item) {
                Gson gson = new Gson();
                String jsonString = gson.toJson(item);

                Bundle bundle = new Bundle();
                bundle.putString(INTENT_KEY_ARTICLE, jsonString);

                SelectedPictureFragment selectedPictureFragment = new SelectedPictureFragment();
                bundle.putString(INTENT_KEY_ARTICLE, jsonString);
                selectedPictureFragment.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.mainActivity, selectedPictureFragment)
                        .addToBackStack("tag")
                        .commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_picture_collection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        prepareRecyclerView(view);
        registerForDataUpdate();
    }


    private void prepareRecyclerView(View view) {

        imageRecyclerView = view.findViewById(R.id.imageRecyclerViewFragement);

        swipeRefresh = view.findViewById(R.id.pictureCollectionFragement);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                registerForDataUpdate();
                swipeRefresh.setRefreshing(false);
            }
        });

        data = new ArrayList<>();

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            imageRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        } else {
            imageRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 8));
        }

        imageRecyclerView.setAdapter(imageRVAdapter);
    }

    private void registerForDataUpdate() {

        mDataViewModel.getPhotoData().observe(getViewLifecycleOwner(), new Observer<List<Hit>>() {
            @Override
            public void onChanged(List<Hit> hits) {
                if (hits != null) {
                    if (hits.isEmpty()) {
                        Toast.makeText(getActivity(), "Failed to get data from API", Toast.LENGTH_SHORT).show();
                    } else {
                        data.clear();
                        data.addAll(hits);
                        Log.d("Received", "data:" + data);
                        imageRVAdapter.setData(data);
                        imageRVAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}