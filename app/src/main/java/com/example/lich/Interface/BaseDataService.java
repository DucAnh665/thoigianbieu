package com.example.lich.Interface;

import com.example.lich.Config.WSConfig;
import com.example.lich.Model.StatusResponse;
import com.example.lich.Model.UserRequest;
import com.example.lich.Model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseDataService {

    @FormUrlEncoded
    @POST(WSConfig.Api.LOGIN)
    Call<UserResponse> loginUser(
            @Field("codeStudent") String codeStudent,
            @Field("passWord") String passWord
    );
    @FormUrlEncoded
    @POST(WSConfig.Api.CHANGE_PASSWORD)
    Call<StatusResponse> changePassWord(@Field("codeStudent") String codeStudent,
                                        @Field("passWord") String passWord);


}
