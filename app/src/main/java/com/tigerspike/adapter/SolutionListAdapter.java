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

        ViewHolder viewHolder;

        Solution solution = (Solution) getItem(position);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_solution, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.txt_description);
            viewHolder.imageView = (NetworkImageView) convertView.findViewById(R.id.img_thumbnail);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        updateViewForSolution(viewHolder, solution);

        return convertView;
    }

    private void updateViewForSolution(ViewHolder viewHolder, Solution solution) {

        viewHolder.txtTitle.setText(solution.getTitle());
        viewHolder.txtDescription.setText(solution.getDescription());

        String imageUrl = solution.getImageUrl();

        //Only show the ImageView when we have a image url
        //Also set a default image to show when loading is in progress
        if (imageUrl != null && imageUrl.length() > 0) {
            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.imageView.setImageUrl(imageUrl, VolleyManager.getInstance().getImageLoader());
        } else {
            viewHolder.imageView.setVisibility(View.GONE);
        }
    }

    static class ViewHolder {

        TextView txtTitle;
        TextView txtDescription;
        NetworkImageView imageView;
    }
}
