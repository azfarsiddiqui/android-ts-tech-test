package com.tigerspike.entity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SolutionsWrapper extends BaseServiceEntity {

    private static final String KEY_JSON_ROWS = "solutions";

    ArrayList<Solution> mSolutions;

    @Override
    public void deserialize(JSONObject jsonObject) {

        try {
            mSolutions = new ArrayList<>();

            //Iterate over the json array and deserialize each item
            //to typed object and add it to array list data source

            JSONArray jsonSolutionsArray = jsonObject.getJSONArray(KEY_JSON_ROWS);

            if (jsonSolutionsArray == null || jsonSolutionsArray.length() == 0) return;

            for (int i = 0; i < jsonSolutionsArray.length(); i++) {

                JSONObject jsonFactObject = jsonSolutionsArray.getJSONObject(i);
                Solution solution = new Solution();
                solution.deserialize(jsonFactObject);

                mSolutions.add(solution);
            }
        } catch (JSONException exception) {
            Log.e(getClass().getName(), exception.getMessage());
        }
    }

    public ArrayList<Solution> getSolutions() {

        return mSolutions;
    }

}
