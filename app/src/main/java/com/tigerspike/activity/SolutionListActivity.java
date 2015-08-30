package com.tigerspike.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.tigerspike.R;
import com.tigerspike.adapter.SolutionListAdapter;
import com.tigerspike.entity.SolutionsWrapper;
import com.tigerspike.listener.SolutionServiceModelListener;
import com.tigerspike.servicemodel.SolutionServiceModel;

public class SolutionListActivity extends BaseActivity implements SolutionServiceModelListener, SwipeRefreshLayout.OnRefreshListener {

    SolutionServiceModel mFactServiceModel;
    SolutionListAdapter mFactListAdapter;
    SolutionsWrapper mSolutionsWrapperObject;

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView mListViewSolutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setupUI();
        setupServiceModel();
        loadData();
    }

    private void setupUI() {

        setContentView(R.layout.activity_solutions_list);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mListViewSolutions = (ListView) findViewById(R.id.list_view_solutions);
    }

    private void setupServiceModel() {

        mFactServiceModel = new SolutionServiceModel(this);
    }

    private void loadData() {

        mFactServiceModel.fetchAllSolutions();
    }

    private void updateUI() {

        if (mFactListAdapter == null) {
            mFactListAdapter = new SolutionListAdapter(mSolutionsWrapperObject.getSolutions(), this);
            mListViewSolutions.setAdapter(mFactListAdapter);
        } else {
            mFactListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSolutionsFetchSuccess(SolutionsWrapper solutionsWrapperObject) {

        mSwipeRefreshLayout.setRefreshing(false);
        mSolutionsWrapperObject = solutionsWrapperObject;

        updateUI();
    }

    @Override
    public void onSolutionsFetchError(String error) {

        mSwipeRefreshLayout.setRefreshing(false);
        super.showNetworkError();
    }

    @Override
    public void onRefresh() {

        mSwipeRefreshLayout.setRefreshing(true);
        loadData();
    }
}
