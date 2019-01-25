package com.shumilov.vladislav.recyclerviewproject;

import android.os.Parcel;
import android.os.Parcelable;

public class TextNewsItem extends BaseObject implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new TextNewsItem(source);
        }

        public TextNewsItem[] newArray(int size) {
            return new TextNewsItem[size];
        }
    };

    private String mComment;

    public TextNewsItem(String comment) {
        mComment = comment;
    }

    public TextNewsItem(Parcel in) {
        mComment = in.readString();
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mComment);
    }
}
