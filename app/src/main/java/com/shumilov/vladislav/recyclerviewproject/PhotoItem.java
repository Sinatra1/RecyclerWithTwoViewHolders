package com.shumilov.vladislav.recyclerviewproject;

import android.os.Parcel;
import android.os.Parcelable;

public class PhotoItem extends BaseObject implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new PhotoItem(source);
        }

        public PhotoItem[] newArray(int size) {
            return new PhotoItem[size];
        }
    };

    private String mUrl;

    public PhotoItem(String uri) {
        mUrl = uri;
    }

    public PhotoItem(Parcel in) {
        mUrl = in.readString();
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUrl);
    }
}
