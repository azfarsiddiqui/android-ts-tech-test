package com.tigerspike.activity;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.tigerspike.R;

public class BaseActivity extends ActionBarActivity {

    ProgressDialog mProgressDialog;

    protected void showNetworkError() {

        Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show();
    }

    protected void showLoading() {

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();
    }

    protected void hideLoading() {

        mProgressDialog.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
