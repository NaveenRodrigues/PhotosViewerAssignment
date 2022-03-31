package com.naveen.photosviewerassignment.photocollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naveen.photosviewerassignment.R;
import com.naveen.photosviewerassignment.currentphoto.ItemClickListner;
import com.naveen.photosviewerassignment.photocollection.pojo.Hit;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {


    private List<Hit> imagePathArrayList;
    ItemClickListner mitemClickListner;

    public RecyclerViewAdapter(List<Hit> imagePathArrayList, ItemClickListner mitemClickListner) {

        this.imagePathArrayList = imagePathArrayList;
        this.mitemClickListner = mitemClickListner;
    }

    public void setData(List<Hit> articles) {
        imagePathArrayList = articles;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Hit current = imagePathArrayList.get(position);
        String imageFile = current.previewURL;

        Picasso.get().load(imageFile).into(holder.photo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mitemClickListner.onItemClick(current);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagePathArrayList == null ? 0 : imagePathArrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final ImageView photo;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photos);
        }
    }
}


