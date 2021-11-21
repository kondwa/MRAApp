package com.example.mraapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService{
    String contentType = "Content-Type: application/json";
    String candidateId = "candidateid: kondwa@gmail.com";
    String apiKey = "apikey: 3fdb48c5-336b-47f9-87e4-ae73b8036a1c";
    @Headers({contentType,candidateId,apiKey })
    @POST("programming/challenge/webservice/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @Headers({contentType,candidateId,apiKey })
    @POST("programming/challenge/webservice/Taxpayers/edit")
    Call<Taxpayer> edit(@Body Taxpayer taxpayer, @Header("Authorization") String authorization);

    @Headers({contentType,candidateId,apiKey })
    @POST("programming/challenge/webservice/Taxpayers/add")
    Call<Taxpayer> add(@Body Taxpayer taxpayer, @Header("Authorization") String authorization);

    @Headers({contentType,candidateId,apiKey })
    @POST("programming/challenge/webservice/auth/logout")
    Call<LogoutResponse> logout(@Body LogoutRequest logoutRequest, @Header("Authorization") String authorization);

    @Headers({contentType,candidateId,apiKey })
    @POST("programming/challenge/webservice/Taxpayers/delete")
    Call<Boolean> delete(@Body DeleteRequest deleteRequest, @Header("Authorization") String authorization);

    @Headers({contentType,candidateId,apiKey })
    @GET("programming/challenge/webservice/Taxpayers/getAll")
    Call<List<Taxpayer>> getAll(@Header("Authorization") String authorization);
}