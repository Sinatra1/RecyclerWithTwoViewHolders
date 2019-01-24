package com.shumilov.vladislav.recyclerviewproject;

public class PhotoItem {
    private String mUrl;

    public PhotoItem(String uri) {
        mUrl = uri;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
