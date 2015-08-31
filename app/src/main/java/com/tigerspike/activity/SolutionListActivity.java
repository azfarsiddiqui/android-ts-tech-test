package com.tigerspike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tigerspike.R;
import com.tigerspike.adapter.SolutionListAdapter;
import com.tigerspike.entity.Solution;
import com.tigerspike.entity.SolutionsWrapper;
import com.tigerspike.listener.SolutionServiceModelListener;
import com.tigerspike.servicemodel.SolutionServiceModel;

public class SolutionListActivity extends BaseActivity implements SolutionServiceModelListener, SwipeRefreshLayout.OnRefreshListener {

    SolutionServiceModel mSolutionServiceModel;
    SolutionListAdapter mSolutionListAdapter;
    SolutionsWrapper mSolutionsWrapperObject;

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView mListViewSolutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
        attachListeners();
        setupServiceModel();
        super.showLoading(); //Only show ProgressDialog for initial load..
        loadData();
    }

    private void initUI() {

        setContentView(R.layout.activity_solutions_list);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mListViewSolutions = (ListView) findViewById(R.id.list_view_solutions);
    }

    private void attachListeners() {

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mListViewSolutions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Solution solution = (Solution) mSolutionListAdapter.getItem(position);
                openDetailActivity(solution);
            }
        });
    }

    private void setupServiceModel() {

        mSolutionServiceModel = new SolutionServiceModel(this);
    }

    private void loadData() {

        mSolutionServiceModel.fetchAllSolutions();
    }

    private void updateUI() {

        if (mSolutionListAdapter == null) {
            mSolutionListAdapter = new SolutionListAdapter(mSolutionsWrapperObject.getSolutions(), this);
            mListViewSolutions.setAdapter(mSolutionListAdapter);
        } else {
            mSolutionListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSolutionsFetchSuccess(SolutionsWrapper solutionsWrapperObject) {

        super.hideLoading();

        mSwipeRefreshLayout.setRefreshing(false);
        mSolutionsWrapperObject = solutionsWrapperObject;

        updateUI();
    }

    @Override
    public void onSolutionsFetchError(String error) {

        super.hideLoading();

        mSwipeRefreshLayout.setRefreshing(false);
        super.showNetworkError();
    }

    @Override
    public void onRefresh() {

        mSwipeRefreshLayout.setRefreshing(true);
        loadData();
    }

    private void openDetailActivity(Solution solution) {

        Intent intent = new Intent(this, SolutionDetailActivity.class);
        intent.putExtra("solution", solution);
        startActivity(intent);
    }
}
