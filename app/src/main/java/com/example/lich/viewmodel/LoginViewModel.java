package com.example.lich.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lich.Config.WSConfig;
import com.example.lich.Interface.BaseDataService;
import com.example.lich.Model.UserRequest;
import com.example.lich.Model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<String> codeStudent = new MutableLiveData<>();
    private MutableLiveData<String> passWord = new MutableLiveData<>();

    private MutableLiveData<Boolean> isLogin = new MutableLiveData<>();


    public MutableLiveData<Boolean> getIsLogin() {
        return isLogin;
    }

    public void login(String codeStudentValue, String passWordValue) {
        codeStudentValue = codeStudent.getValue();
        passWordValue = passWord.getValue();
        Log.e("tài khoản",codeStudentValue);
        Log.e("mật khẩu",passWordValue);
        BaseDataService service = new Retrofit.Builder().baseUrl(WSConfig.Base_Log).
                addConverterFactory(GsonConverterFactory.create()).
                build().create(BaseDataService.class);
        UserRequest userRequest = new UserRequest(codeStudentValue, passWordValue);
        Call<UserResponse> callApi = service.login(userRequest);
        callApi.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if (userResponse != null) {
                    String userResponseString = userResponse.toString(); // Gọi phương thức toString() sau khi kiểm tra không phải null

                } else {
                   Log.e("Lỗi","UserReponse rỗng");
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("Lỗi", "Đăng nhập không thành công" + t.toString());
                getIsLogin().setValue(false);
            }
        });
    }
}
