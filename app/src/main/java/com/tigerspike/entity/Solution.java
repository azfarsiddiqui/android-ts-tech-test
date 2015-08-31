package com.tigerspike.entity;

import com.tigerspike.helper.JsonHelper;

import org.json.JSONObject;

import java.io.Serializable;

public class Solution extends BaseServiceEntity implements Serializable {

    private static final String KEY_JSON_TITLE = "name";
    private static final String KEY_JSON_DESCRIPTION = "description";
    private static final String KEY_JSON_IMAGE_URL = "icon";
    private static final String KEY_JSON_DETAIL_URL = "url";

    private String mTitle;
    private String mDescription;
    private String mImageUrl;
    private String mDetailUrl;

    @Override
    public void deserialize(JSONObject jsonObject) {

        mTitle = JsonHelper.getString(jsonObject, KEY_JSON_TITLE);
        mDescription = JsonHelper.getString(jsonObject, KEY_JSON_DESCRIPTION);
        mImageUrl = JsonHelper.getString(jsonObject, KEY_JSON_IMAGE_URL);
        mDetailUrl = JsonHelper.getString(jsonObject, KEY_JSON_DETAIL_URL);
    }

    public String getTitle() {

        return mTitle;
    }

    public String getDescription() {

        return mDescription;
    }

    public String getImageUrl() {

        return mImageUrl;
    }

    public String getDetailUrl() {

        return mDetailUrl;
    }
}
