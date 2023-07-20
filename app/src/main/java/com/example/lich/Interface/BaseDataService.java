package com.example.lich.Interface;

import com.example.lich.Config.WSConfig;
import com.example.lich.Model.UserRequest;
import com.example.lich.Model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BaseDataService {
    @POST(WSConfig.Api.LOGIN)
    Call<UserResponse> login(@Body UserRequest loginRequest);
}
