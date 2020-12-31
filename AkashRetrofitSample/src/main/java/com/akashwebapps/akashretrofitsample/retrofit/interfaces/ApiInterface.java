package com.akashwebapps.akashretrofitsample.retrofit.interfaces;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

public interface ApiInterface {

    // For posting from data

    @FormUrlEncoded
    @POST()
    Call<String> getPostByFormData (@Url String url, @FieldMap HashMap<String, String> params , @Header("tag") String tag);
    @Multipart
    @POST()
    Call<String> UploadWithFormData(@Url String url, @PartMap HashMap<String, RequestBody> param, @Part MultipartBody.Part file, @Header("tag") String tag);

    // For posting json data


    @Headers({"Content-Type: application/json"})
    @POST()
    Call<String> getPostByJsonBody(@Url String url, @Body HashMap<String, String> params, @Header("tag") String tag);

    @GET()
    Call<String> get(@Url String url, @Header("tag") String tag);

    @Multipart
    @POST()
    Call<String> UploadWithJsonBody(@Url String url, @PartMap HashMap<String, RequestBody> param, @Part MultipartBody.Part file, @Header("tag") String tag);






}
