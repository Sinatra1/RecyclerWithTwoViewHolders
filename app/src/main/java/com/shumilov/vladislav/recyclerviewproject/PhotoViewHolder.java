package com.shumilov.vladislav.recyclerviewproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PhotoViewHolder extends RecyclerView.ViewHolder {
    private ImageView mPhotoView;

    public PhotoViewHolder(View itemView) {
        super(itemView);

        mPhotoView = itemView.findViewById(R.id.ivPhoto);
    }

    public void bind(PhotoItem item) {
        if (item == null) {
            return;
        }

        Context context = mPhotoView.getContext();

        Picasso.with(context).load(item.getUrl()).into(mPhotoView);
    }
}
