package com.shumilov.vladislav.recyclerviewproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int NEWS = 0, PHOTO = 1;

    private List<Object> items = new ArrayList<>();

    public RecyclerViewAdapter(List<Object> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == NEWS) {
            View view = layoutInflater.inflate(R.layout.textview_list_item, parent, false);
            return new TextNewsViewHolder(view);
        }

        View view = layoutInflater.inflate(R.layout.imageview_list_item, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        if (viewType == NEWS) {
            TextNewsViewHolder textViewHolder = (TextNewsViewHolder) holder;
            textViewHolder.bind((TextNewsItem) items.get(position));
            return;
        }

        if (viewType == PHOTO) {
            PhotoViewHolder imageViewHolder = (PhotoViewHolder) holder;
            imageViewHolder.bind((PhotoItem) items.get(position));
            return;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof TextNewsItem) {
            return NEWS;
        }

        if (items.get(position) instanceof PhotoItem) {
            return PHOTO;
        }

        return -1;
    }

    public void addItem(Object item) {
        if (item == null || (!(item instanceof TextNewsItem) && !(item instanceof PhotoItem))) {
            return;
        }

        items.add(item);
        notifyDataSetChanged();
    }
}
