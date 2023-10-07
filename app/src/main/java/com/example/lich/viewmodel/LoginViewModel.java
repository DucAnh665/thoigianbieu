package com.example.lich.viewmodel;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lich.Config.WSConfig;
import com.example.lich.Interface.BaseDataService;
import com.example.lich.Model.UserRequest;
import com.example.lich.Model.UserResponse;
import com.example.lich.R;
import com.example.lich.view.dialog.DialogCustomer;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Boolean> isLogin = new MutableLiveData<>();

    private MutableLiveData<List<UserResponse.User>> dataUser = new MutableLiveData<>();

    private LinearLayout btnSend;
    private TextView txtMessage;


    public MutableLiveData<List<UserResponse.User>> getDataUser() {
        return dataUser;
    }

    public MutableLiveData<Boolean> getIsLogin() {
        return isLogin;
    }

    public void login(String codeStudentValue, String passWordValue, Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WSConfig.Base_Log)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseDataService service = retrofit.create(BaseDataService.class);
        UserRequest userRequest = new UserRequest();
        userRequest.setCodeStudent(codeStudentValue);
        userRequest.setPassWord(passWordValue);
        Call<UserResponse> callApi = service.loginUser(codeStudentValue, passWordValue);
        Log.e("Tag", callApi.request().url().toString());
        Dialog dialog = new Dialog(context);
        showDialog(dialog);
        callApi.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if (response.isSuccessful()) {
                    getIsLogin().setValue(true);
                    getDataUser().setValue(userResponse.getData());
                    dialog.dismiss();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("Tag", "Error body: " + errorBody); // Logging the error body if available
                    } catch (IOException e) {
                        Log.e("Tag", "Error parsing error body: " + e.getMessage());
                    }
                    DialogCustomer.showDialog(context, R.layout.dialog_send_fail, new DialogCustomer.OnButtonClickListener() {
                        @Override
                        public void findId(Dialog dialog) {
                            btnSend = dialog.findViewById(R.id.btn_send_again);
                            txtMessage = dialog.findViewById(R.id.txt_dialog_message);
                            txtMessage.setText("Ứng dụng đã được đăng nhập ở thiết bị khác");
                        }

                        @Override
                        public void onListener(Dialog dialog) {
                            btnSend.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                        }
                    });
                    dialog.dismiss();
                    getIsLogin().setValue(false);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("Lỗi", "Đăng nhập không thành công" + t.toString());
                DialogCustomer.showDialog(context, R.layout.dialog_send_fail, new DialogCustomer.OnButtonClickListener() {
                    @Override
                    public void findId(Dialog dialog) {
                        btnSend = dialog.findViewById(R.id.btn_send_again);
                        txtMessage = dialog.findViewById(R.id.txt_dialog_message);
                    }

                    @Override
                    public void onListener(Dialog dialog) {

                        txtMessage.setText("Kiểm tra lại mạng");
                        btnSend.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                    }
                });
                dialog.dismiss();
            }
        });
    }


    public void showDialog(Dialog dialog) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_loading_indicator);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity = Gravity.CENTER;
        window.setAttributes(windowAttribute);
        if (Gravity.BOTTOM == Gravity.CENTER) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        dialog.show();
    }
}
