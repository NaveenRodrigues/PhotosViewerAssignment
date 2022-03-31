package com.naveen.photosviewerassignment.currentphoto;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.naveen.photosviewerassignment.photocollection.PictureCollectionFragment;
import com.naveen.photosviewerassignment.R;
import com.naveen.photosviewerassignment.photocollection.pojo.Hit;
import com.squareup.picasso.Picasso;


public class SelectedPictureFragment extends Fragment {
    Hit currentHit;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_selected_picture, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        ImageView imageView = view.findViewById(R.id.fragmentImageView);

        if (getArguments() != null) {

            String data = getArguments().getString(PictureCollectionFragment.INTENT_KEY_ARTICLE);
            Gson gson = new Gson();
            try {
                currentHit = gson.fromJson(data, Hit.class);

                String currentImage = currentHit.largeImageURL;

                Picasso.get().load(currentImage).into(imageView);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
            //TODO Handle error scenarios
        }
    }
}