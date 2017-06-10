package com.androidheight.firebaseanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidheight.firebaseanalytics.adapter.CategoryListAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements CategoryListAdapter.OnViewClickListener {

    String[] categoryList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryList = new String[]{"Shirt", "Paint", "T-shirt", "Shoes", "Watch"};



        mRecyclerView = (RecyclerView) findViewById(R.id.rvCategoryList);


        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CategoryListAdapter(categoryList,MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);


    }



    @Override
    public void onViewClicked(View view, int position) {
        Intent intent = new Intent(MainActivity.this,ProductListActivity.class);
        intent.putExtra("category",categoryList[position]);
        startActivity(intent);


    }
}
