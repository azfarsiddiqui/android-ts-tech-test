package com.tigerspike.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tigerspike.R;
import com.tigerspike.entity.Solution;

public class SolutionDetailActivity extends BaseActivity {

    WebView mWebView;
    Solution mSolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initUI();
        attachListeners();
        setupData();
        updateUI();
    }

    private void initUI() {

        setContentView(R.layout.activity_solution_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    private void setupData() {

        mSolution = (Solution) getIntent().getSerializableExtra("solution");
    }

    private void attachListeners() {

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
                SolutionDetailActivity.super.showLoading();
            }

            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
                SolutionDetailActivity.super.hideLoading();
            }
        });
    }

    private void updateUI() {

        getSupportActionBar().setTitle(mSolution.getTitle());
        mWebView.loadUrl(mSolution.getDetailUrl());
    }
}
