package com.tigerspike.activity;

import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.tigerspike.R;

public class BaseActivity extends ActionBarActivity {

    protected void showNetworkError() {

        Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show();
    }
}
