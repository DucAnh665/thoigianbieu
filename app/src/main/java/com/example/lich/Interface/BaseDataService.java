package com.example.lich.Interface;

import com.example.lich.Config.WSConfig;
import com.example.lich.Model.StatusResponse;
import com.example.lich.Model.SubjectReponse;
import com.example.lich.Model.User;
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

    @FormUrlEncoded
    @POST(WSConfig.Api.CHECK_CODE_STUDENT)
    Call<StatusResponse> checkCodeStudent(@Field("codeStudent") String codeStudent);


    @FormUrlEncoded
    @POST(WSConfig.Api.GET_SUBJECT)
    Call<SubjectReponse> getSubject(@Field("codeStudent") String codeStudent);


    @FormUrlEncoded
    @POST(WSConfig.Api.UPDATE_USER)
    Call<User> updateUser(@Field("codeStudent") String codeStudent, @Field("address") String address, @Field("birthday") String birthday);


}
