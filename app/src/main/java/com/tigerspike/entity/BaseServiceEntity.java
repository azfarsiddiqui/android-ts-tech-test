package com.tigerspike.entity;

import org.json.JSONObject;

public abstract class BaseServiceEntity {

    //This method should be used to convert JSON representation
    //to a typed object
    protected abstract void deserialize(JSONObject jsonObject);
}
