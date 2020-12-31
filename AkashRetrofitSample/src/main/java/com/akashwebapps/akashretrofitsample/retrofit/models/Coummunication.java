package com.akashwebapps.akashretrofitsample.retrofit.models;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

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

public class Coummunication {
    private Context context;
    private OnCallBackListner listner;
    public static final String BASE_URL = "";
   public ProgressDialog progressDialog;

    public Coummunication(Context context, OnCallBackListner listner) {
        this.context = context;
        this.listner = listner;

    }

    private void loader() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);


    }



  /*  // for credential
    public void callPOST(HashMap<String, String> params, String tag) {

        if (NetWorkChecker.check(context)) {

            loader();

            if (progressDialog != null) {
                progressDialog.show();
            }
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<String> callMethod = apiInterface.getPost(BASE_URL, params, tag);
            callMethod.enqueue(new Callback<String>() {



                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    try {
                        Log.e(tag + "_responsedata", String.valueOf(new JSONObject(response.body())));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    onCallBackSuccess(call, response);

                }


                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e("_responsedata", t.getMessage() + call.request().header("tag"));
                    onCallBackFaild(call, t);
                }


            });
        }

    }*/




    public void callGetRequest(String url,  String tag) {
        if (NetWorkChecker.check(context)) {
            loader();
            if (progressDialog != null) {
                progressDialog.show();
            }
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<String> callMethod = apiInterface.get(url, tag);
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
                    Log.e("_responsedata", t.getMessage() + call.request().header("tag"));
                    onCallBackFaild(call, t);
                }
            });
        }

    }



// JSON BODY
    public void callPOSTwithJsonBody(String url, HashMap<String, String> params, String tag) {
        if (NetWorkChecker.check(context)) {
            loader();
            if (progressDialog != null) {
                progressDialog.show();
            }
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<String> callMethod = apiInterface.getPostByJsonBody(url, params, tag);
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
                    Log.e("_responsedata", t.getMessage() + call.request().header("tag"));
                    onCallBackFaild(call, t);
                }
            });
        }

    }

    public void callFileUploadwithJsonBody(String url, @NonNull HashMap<String, String> param, @NonNull PART part, final String tag) {

        if (NetWorkChecker.check(context)) {
            loader();
            if (progressDialog != null) {
                progressDialog.show();
            }

            ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

            Call<String> stringCall = service.UploadWithJsonBody(url, getParam(param), Params.createMultiPart(part), tag);
            stringCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                    try {
                        Log.e(tag + "_responsedata", String.valueOf(new JSONObject(response.body())));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    onCallBackSuccess(call, response);
                }

                @Override
                public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                    onCallBackFaild(call, t);

                }
            });
        }

    }




    //FORM Data

    public void callPOSTwithFormData(String url, HashMap<String, String> params, String tag) {
        if (NetWorkChecker.check(context)) {
            loader();
            if (progressDialog != null) {
                progressDialog.show();
            }
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<String> callMethod = apiInterface.getPostByFormData(url, params, tag);
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
                    Log.e("_responsedata", t.getMessage() + call.request().header("tag"));
                    onCallBackFaild(call, t);
                }
            });
        }

    }

    public void callFileUploadwithFormData(String url, @NonNull HashMap<String, String> param, @NonNull PART part, final String tag) {

        if (NetWorkChecker.check(context)) {
            loader();
            if (progressDialog != null) {
                progressDialog.show();
            }

            ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

            Call<String> stringCall = service.UploadWithFormData(url, getParam(param), Params.createMultiPart(part), tag);
            stringCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                    try {
                        Log.e(tag + "_responsedata", String.valueOf(new JSONObject(response.body())));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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

        if (response.isSuccessful()){

            if (progressDialog != null) {
                progressDialog.dismiss();
            }

            Log.e("response_body" + call.request().header("tag"), response.body());

            try {
                listner.OnCallBackSuccess(call.request().header("tag"), new JSONObject(response.body()));
            } catch (JSONException e) {
                listner.OnCallBackError(call.request().header("tag"), "failed", -1);
                e.printStackTrace();
            }
        }

        else {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();

        }



    }


    public void onCallBackFaild(Call<String> call, Throwable t) {


        try
        {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            if (listner != null) {
                listner.OnCallBackError(call.request().header("tag"), t.getMessage(), -1);
            }


        }
        catch (Exception e){

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

