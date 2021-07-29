package com.akashwebapps.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.akashwebapps.akashretrofitsample.retrofit.interfaces.OnCallBackListner;
import com.akashwebapps.akashretrofitsample.retrofit.models.ApiClient;
import com.akashwebapps.akashretrofitsample.retrofit.models.ApiRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements OnCallBackListner {

    ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiRequest = new ApiRequest(this, this, "https://jsonplaceholder.typicode.com/comments/");
        apiRequest.setDialogColor(Color.YELLOW);



       // apiRequest.getRequest("https://jsonplaceholder.typicode.com/comments", "jdd", true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        apiRequest.getRequest("https://jsonplaceholder.typicode.com/comments", "jdd", true);

    }

    @Override
    public void OnCallBackSuccess(String tag, JSONObject jsonObject) {

    }

    @Override
    public void OnCallBackError(String tag, String error, int i) {

    }
}