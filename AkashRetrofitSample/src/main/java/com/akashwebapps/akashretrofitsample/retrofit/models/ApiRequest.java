package com.akashwebapps.akashretrofitsample.retrofit.models;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.akashwebapps.akashretrofitsample.R;
import com.akashwebapps.akashretrofitsample.retrofit.NetWorkChecker;
import com.akashwebapps.akashretrofitsample.retrofit.interfaces.ApiInterface;
import com.akashwebapps.akashretrofitsample.retrofit.interfaces.OnCallBackListner;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRequest {
    private Context context;
    private OnCallBackListner listner;
    private String BASE_URL = "";
    public Dialog progressDialog;

    public ApiRequest(Context context, OnCallBackListner listner, String url) {
        this.context = context;
        this.listner = listner;
        this.BASE_URL = url;

    }

    private void loader() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new Dialog(context);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.dialog);


    }


    public void getRequest(String url, String tag, boolean loader) {
        if (NetWorkChecker.check(context)) {

            if (loader) {
                loader();
                if (progressDialog != null) {
                    progressDialog.show();
                }
            }

            ApiInterface apiInterface = ApiClient.getClient(BASE_URL).create(ApiInterface.class);
            Call<String> callMethod = apiInterface.getRequest(url, tag);
            callMethod.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

              /*  try {
                    Log.e(tag + "_responsedata", String.valueOf(new JSONObject(response.body())));
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                    onCallBackSuccess(call, response);

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    // Log.e("_responsedata", t.getMessage() + call.request().header("tag"));
                    onCallBackFaild(call, t);
                }
            });
        }

    }


    // JSON BODY
    public void postRequestJson(String url, HashMap<String, String> params, String tag, boolean loader) {
        if (NetWorkChecker.check(context)) {
            if (loader) {
                loader();
                if (progressDialog != null) {
                    progressDialog.show();
                }
            }
            ApiInterface apiInterface = ApiClient.getClient(BASE_URL).create(ApiInterface.class);
            Call<String> callMethod = apiInterface.postRequestJson(url, params, tag);
            callMethod.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

              /*  try {
                    Log.e(tag + "_responsedata", String.valueOf(new JSONObject(response.body())));
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                    onCallBackSuccess(call, response);

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    //  Log.e("_responsedata", t.getMessage() + call.request().header("tag"));
                    onCallBackFaild(call, t);
                }
            });
        }

    }


    //FORM Data

    public void postRequestForm(String url, HashMap<String, String> params, String tag, boolean loader) {
        if (NetWorkChecker.check(context)) {
            if (loader) {
                loader();
                if (progressDialog != null) {
                    progressDialog.show();
                }
            }
            ApiInterface apiInterface = ApiClient.getClient(BASE_URL).create(ApiInterface.class);
            Call<String> callMethod = apiInterface.postRequestForm(url, params, tag);
            callMethod.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

              /*  try {
                    Log.e(tag + "_responsedata", String.valueOf(new JSONObject(response.body())));
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                    onCallBackSuccess(call, response);

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    //  Log.e("_responsedata", t.getMessage() + call.request().header("tag"));
                    onCallBackFaild(call, t);
                }
            });
        }

    }

    public void fileUploadForm(String url, @NonNull HashMap<String, String> param, @NonNull PART part, final String tag, boolean loader) {

        if (NetWorkChecker.check(context)) {
            if (loader) {
                loader();
                if (progressDialog != null) {
                    progressDialog.show();
                }
            }

            ApiInterface service = ApiClient.getClient(BASE_URL).create(ApiInterface.class);

            Call<String> stringCall = service.fileUploadForm(url, getParam(param), Params.createMultiPart(part), tag);
            stringCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                 /*   try {
                        Log.e(tag + "_responsedata", String.valueOf(new JSONObject(response.body())));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/

                    onCallBackSuccess(call, response);
                }

                @Override
                public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                    onCallBackFaild(call, t);

                }
            });
        }

    }


    public void onCallBackSuccess(Call<String> call, Response<String> response) {

        if (response.isSuccessful()) {

            if (progressDialog != null) {
                progressDialog.dismiss();
            }

            // Log.e("response_body" + call.request().header("tag"), response.body());

            try {
                listner.OnCallBackSuccess(call.request().header("tag"), new JSONObject(response.body()));
            } catch (JSONException e) {
                listner.OnCallBackError(call.request().header("tag"), e.getMessage(), -1);
                e.printStackTrace();
            }
        } else {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();

        }


    }


    public void onCallBackFaild(Call<String> call, Throwable t) {


        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            if (listner != null) {
                listner.OnCallBackError(call.request().header("tag"), t.getMessage(), -1);
            }


        } catch (Exception e) {

            listner.OnCallBackError(call.request().header("tag"), e.getMessage(), -1);


        }


    }


    private HashMap<String, RequestBody> getParam(HashMap<String, String> param) {
        HashMap<String, RequestBody> tempParam = new HashMap<>();
        for (String key : param.keySet()) {
            tempParam.put(key, toRequestBody(param.get(key)));
        }

        return tempParam;
    }


    private static RequestBody toRequestBody(String value) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, value);
    }
}

