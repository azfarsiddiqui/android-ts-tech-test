package com.tigerspike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tigerspike.R;
import com.tigerspike.entity.Solution;
import com.tigerspike.network.VolleyManager;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

public class SolutionListAdapter extends BaseAdapter {

    ArrayList<Solution> mSolutions;
    Context mContext;

    public SolutionListAdapter(ArrayList<Solution> solutions, Context context) {

        mSolutions = solutions;
        mContext = context;
    }

    @Override
    public int getCount() {

        return mSolutions.size();
    }

    @Override
    public Object getItem(int position) {

        return mSolutions.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Solution solution = (Solution) getItem(position);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_solution, parent, false);
        }

        return updateViewForSolution(convertView, solution);
    }

    private View updateViewForSolution(View view, Solution solution) {

        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
        TextView txtDescription = (TextView) view.findViewById(R.id.txt_description);
        NetworkImageView imageView = (NetworkImageView) view.findViewById(R.id.img_thumbnail);

        txtTitle.setText(solution.getTitle());
        txtDescription.setText(solution.getDescription());

        String imageUrl = solution.getImageUrl();

        //Only show the ImageView when we have a image url
        //Also set a default image to show when loading is in progress
        if (imageUrl != null && imageUrl.length() > 0) {
            imageView.setDefaultImageResId(R.mipmap.ic_launcher);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageUrl(imageUrl, VolleyManager.getInstance().getImageLoader());
        } else {
            imageView.setVisibility(View.GONE);
        }

        return view;
    }
}
