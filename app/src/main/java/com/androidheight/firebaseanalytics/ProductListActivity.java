package com.androidheight.firebaseanalytics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.androidheight.firebaseanalytics.adapter.CategoryListAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Random;

public class ProductListActivity extends AppCompatActivity {

    private FirebaseAnalytics firebaseAnalytics;
    String Category;
    String[] productlist;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        productlist = new String[]{"product 1", "product 2", "product 3", "product 4", "product 5"};
        Category = getIntent().getStringExtra("category");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Category);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvProductList);


        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CategoryListAdapter(productlist,ProductListActivity.this);
        mRecyclerView.setAdapter(mAdapter);





        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Product product = new Product();
        product.setId(1);

        //select random product name
        product.setName(productlist[randomIndex()]);
        Bundle bundle = new Bundle();
        bundle.putInt(FirebaseAnalytics.Param.ITEM_ID, product.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, product.getName());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, Category);

        //Logs an app event. 
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        //Sets whether analytics collection is enabled for this app on this device. 
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);

        //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds just for the fun 
        firebaseAnalytics.setMinimumSessionDuration(20000);
        //Sets the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes). 
        firebaseAnalytics.setSessionTimeoutDuration(500);
        //Sets the user ID property. 
        firebaseAnalytics.setUserId(String.valueOf(product.getId()));

        //Sets a user property to a given value. 
        firebaseAnalytics.setUserProperty("Product", product.getName());

    }
        /* */
   // }  


    private int randomIndex() {
        int min = 0;
        int max = productlist.length - 1;
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}

