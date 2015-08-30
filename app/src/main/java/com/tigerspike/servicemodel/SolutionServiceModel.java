package com.tigerspike.servicemodel;

import com.tigerspike.entity.SolutionsWrapper;
import com.tigerspike.listener.SolutionServiceModelListener;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class SolutionServiceModel extends BaseServiceModel {

    private SolutionServiceModelListener mSolutionServiceModelListener;

    public SolutionServiceModel(SolutionServiceModelListener solutionServiceModelListener) {

        mSolutionServiceModelListener = solutionServiceModelListener;
    }

    public void fetchAllSolutions() {

        super.get("", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {

                        try {
                            JSONObject solutionsWrapperJsonObject = new JSONObject(json);
                            SolutionsWrapper solutionsWrapperObject = new SolutionsWrapper();
                            solutionsWrapperObject.deserialize(solutionsWrapperJsonObject);

                            mSolutionServiceModelListener.onSolutionsFetchSuccess(solutionsWrapperObject);

                        } catch (JSONException e) {

                            mSolutionServiceModelListener.onSolutionsFetchError(e.getMessage());
                        }
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
