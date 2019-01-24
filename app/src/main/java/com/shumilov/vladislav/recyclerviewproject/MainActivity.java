package com.shumilov.vladislav.recyclerviewproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private static final String sHello = "Привет";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Object> list = new ArrayList<>();
        list.add(new TextNewsItem(sHello));
        list.add(new PhotoItem("https://peopletalk." +
                "ru/userfiles/images/2%281124%29.jpg"));

        mAdapter = new RecyclerViewAdapter(list);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
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
}
