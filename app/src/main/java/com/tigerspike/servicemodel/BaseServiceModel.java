package com.tigerspike.servicemodel;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tigerspike.constant.APIConstants;
import com.tigerspike.network.VolleyManager;

import org.json.JSONObject;

public abstract class BaseServiceModel {

    protected String getCompleteUrl(String method) {

        return APIConstants.API_BASE_URL + method;
    }

    protected void get(String apiMethod, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {

        String url = getCompleteUrl(apiMethod);
        JsonObjectRequest request = new JsonObjectRequest(url, null, successListener, errorListener);
        request.setShouldCache(false);
        
        VolleyManager.getInstance().getRequestQueue().add(request);
    }
}
