package com.akashwebapps.akashretrofitsample.retrofit.interfaces;

import org.json.JSONObject;

public interface OnCallBackListner {

    void OnCallBackSuccess(String tag, JSONObject jsonObject);
    void OnCallBackError(String tag, String error, int i);
}
