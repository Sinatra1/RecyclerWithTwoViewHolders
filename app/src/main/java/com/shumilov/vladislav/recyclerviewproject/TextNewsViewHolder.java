package com.shumilov.vladislav.recyclerviewproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TextNewsViewHolder extends RecyclerView.ViewHolder {
    private TextView mCommentTextView;

    public TextNewsViewHolder(View itemView) {
        super(itemView);

        mCommentTextView = itemView.findViewById(R.id.tvComment);
    }


    public void bind(final TextNewsItem listItem) {
        if (listItem == null) {
            return;
        }

        mCommentTextView.setText(listItem.getComment());
    }

    public void setListener(final OnItemClickListener listener, final TextNewsItem listItem, final int position) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(listItem, position);
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(Object object, int position);
    }
}
