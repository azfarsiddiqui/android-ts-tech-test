package com.tigerspike.helper;

import org.json.JSONObject;

public class JsonHelper {

    public static String getString(JSONObject jsonObject, String key) {

        if (jsonObject.isNull(key)) return "";

        return jsonObject.optString(key).trim();
    }
}
