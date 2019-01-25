package com.shumilov.vladislav.recyclerviewproject;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private Parcelable mListState;
    private static final String sHello = "Привет";
    private static final String LIST_STATE_KEY = "LIST_STATE_KEY";
    private static final String ITEMS_KEY = "ITEMS_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new RecyclerViewAdapter();
        mAdapter.setListener(this);

        if (savedInstanceState == null) {
            fillByDefaultData();
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void fillByDefaultData() {
        ArrayList<BaseObject> list = new ArrayList<>();
        list.add(new TextNewsItem(sHello));
        list.add(new PhotoItem("https://peopletalk." +
                "ru/userfiles/images/2%281124%29.jpg"));

        mAdapter.addItems(list);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mListState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, mListState);
        outState.putParcelableArrayList(ITEMS_KEY, mAdapter.getItems());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState == null) {
            return;
        }

        mListState = savedInstanceState.getParcelable(LIST_STATE_KEY);

        ArrayList<BaseObject> items = savedInstanceState.getParcelableArrayList(ITEMS_KEY);

        if (items != null) {
            mAdapter.addItems(items);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mListState != null) {
            mLayoutManager.onRestoreInstanceState(mListState);
        }

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addNews) {
            TextNewsItem textNewsItem = new TextNewsItem(sHello + " " + mAdapter.getItemCount());
            mAdapter.addItem(textNewsItem);
            return true;
        }

        if (item.getItemId() == R.id.addPhoto) {
            PhotoItem photoItem = new PhotoItem("https://www.cheatsheet.com/wp-content/uploads/2016/03/star-trek-originial-cast.jpg");
            mAdapter.addItem(photoItem);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Object object, int position) {
        String text = "";

        if (object instanceof TextNewsItem) {
            text = getString(R.string.news_was_deleted) + ((TextNewsItem) object).getComment();
        } else if (object instanceof PhotoItem) {
            text = getString(R.string.photo_was_deleted) + ((PhotoItem) object).getUrl();
        }

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                fillByDefaultData();

                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        }, 2000);
    }
}
