package com.shumilov.vladislav.recyclerviewproject;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseObject implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new BaseObject();
        }

        public BaseObject[] newArray(int size) {
            return new BaseObject[size];
        }
    };

    private String mUrl;

    public BaseObject() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

}
