package com.androidheight.firebaseanalytics.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidheight.firebaseanalytics.MainActivity;
import com.androidheight.firebaseanalytics.R;

/**
 * Created by prabhakar on 04/06/17.
 */

public class CategoryListAdapter  extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    public String[] categoryList;
    Context context;
    OnViewClickListener onViewClickListener;
    //Interface for clicklistener///////

    public interface OnViewClickListener {
        void onViewClicked(View view, int position);

    }

    public CategoryListAdapter(String[] categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
        if (context instanceof MainActivity)
        onViewClickListener =(MainActivity) context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                             int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.category_list_item, viewGroup, false);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.category_item.setText(categoryList[position].toString());

        if(context instanceof  MainActivity) {
            viewHolder.category_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onViewClickListener.onViewClicked(v, position);
                }
            });
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categoryList.length;
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView category_item;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            category_item = (TextView) itemLayoutView
                    .findViewById(R.id.category_item);


        }
    }

}

