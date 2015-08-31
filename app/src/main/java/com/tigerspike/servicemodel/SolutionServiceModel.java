package com.tigerspike.servicemodel;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tigerspike.entity.SolutionsWrapper;
import com.tigerspike.listener.SolutionServiceModelListener;

import org.json.JSONObject;

public class SolutionServiceModel extends BaseServiceModel {

    private SolutionServiceModelListener mSolutionServiceModelListener;

    public SolutionServiceModel(SolutionServiceModelListener solutionServiceModelListener) {

        mSolutionServiceModelListener = solutionServiceModelListener;
    }

    public void fetchAllSolutions() {

        super.get("", new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {

                    SolutionsWrapper solutionsWrapperObject = new SolutionsWrapper();
                    solutionsWrapperObject.deserialize(jsonObject);

                    mSolutionServiceModelListener.onSolutionsFetchSuccess(solutionsWrapperObject);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                    mSolutionServiceModelListener.onSolutionsFetchError(volleyError.getMessage());
                }
            }
        );
    }

}
