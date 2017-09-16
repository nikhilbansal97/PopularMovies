package com.example.nikhil.popularmovies.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nikhil.popularmovies.R;
import com.example.nikhil.popularmovies.pojos.movie_details.ReviewResults;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil on 11/9/17.
 */

public class ReviewsAdapter extends ArrayAdapter<ReviewResults>{

    private Context context;
    private List<ReviewResults> mList = new ArrayList<>();

    public ReviewsAdapter(@NonNull Context context, List<ReviewResults> list) {
        super(context, 0, list);
        this.context = context;
        mList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.review_list_item,parent,false);
        }
        ReviewResults result = mList.get(position);

        TextView name = (TextView) listItemView.findViewById(R.id.textView_review_name);
        name.setText(result.getAuthor());

        TextView body = (TextView) listItemView.findViewById(R.id.textView_review_body);
        body.setText(result.getContent());

        return listItemView;
    }
}
