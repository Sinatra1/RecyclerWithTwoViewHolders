package com.shumilov.vladislav.recyclerviewproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TextNewsViewHolder.OnItemClickListener, PhotoViewHolder.OnItemClickListener {
    private final int NEWS = 0, PHOTO = 1;
    private RecyclerViewListener mListener;

    private ArrayList<BaseObject> mItems = new ArrayList<>();

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);

        if (viewType == NEWS) {
            TextNewsViewHolder textViewHolder = (TextNewsViewHolder) holder;
            textViewHolder.bind((TextNewsItem) mItems.get(position));
            textViewHolder.setListener(this, (TextNewsItem) mItems.get(position), position);
            return;
        }

        if (viewType == PHOTO) {
            PhotoViewHolder imageViewHolder = (PhotoViewHolder) holder;
            imageViewHolder.bind((PhotoItem) mItems.get(position));
            imageViewHolder.setListener(this, (PhotoItem) mItems.get(position), position);
            return;
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position) instanceof TextNewsItem) {
            return NEWS;
        }

        if (mItems.get(position) instanceof PhotoItem) {
            return PHOTO;
        }

        return -1;
    }

    public void addItems(ArrayList<BaseObject> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    public void addItem(BaseObject item) {
        if (item == null || (!(item instanceof TextNewsItem) && !(item instanceof PhotoItem))) {
            return;
        }

        mItems.add(item);
        notifyDataSetChanged();

        mListener.onItemAdd(item, mItems.size() - 1);
    }

    public void setListener(RecyclerViewListener listener) {
        mListener = listener;
    }

    @Override
    public void onItemClick(Object object, int position) {
        mListener.onItemClick(mItems.get(position), position);
        removeItem(position);
    }

    public interface RecyclerViewListener {
        void onItemClick(Object object, int position);

        void onItemAdd(Object object, int position);
    }

    private void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mItems.size());
    }

    public ArrayList<BaseObject> getItems() {
        return mItems;
    }
}
