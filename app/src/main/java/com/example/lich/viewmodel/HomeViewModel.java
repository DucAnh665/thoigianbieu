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
import com.example.lich.Model.StatusResponse;
import com.example.lich.Model.UserRequest;
import com.example.lich.Model.UserResponse;
import com.example.lich.R;
import com.example.lich.view.dialog.DialogCustomer;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<Boolean> isLogout = new MutableLiveData<>();

    private MutableLiveData<Boolean> isCheckStudent = new MutableLiveData<>();


    private LinearLayout btnSend;
    private TextView txtMessage;


    public MutableLiveData<Boolean> getIsLogout() {
        return isLogout;
    }

    public MutableLiveData<Boolean> getIsCheckStudent() {
        return isCheckStudent;
    }

    public void logOut(Context context, String codeStudentValue) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WSConfig.Base_Log)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseDataService service = retrofit.create(BaseDataService.class);
        Call<StatusResponse> callApi = service.logout(codeStudentValue);
        Log.e("Tag", callApi.request().url().toString());
        Dialog dialog = new Dialog(context);
        showDialog(dialog);
        callApi.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.isSuccessful()) {
                    StatusResponse userResponse = response.body();
                    getIsLogout().setValue(true);
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
                            txtMessage.setText("Thiết bị đã được đăng xuất");
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
                    getIsLogout().setValue(false);
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
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


    public void checkLogout(Context context, String codeStudentValue) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WSConfig.Base_Log)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseDataService service = retrofit.create(BaseDataService.class);
        Call<StatusResponse> callApi = service.checkLogin(codeStudentValue);
        Log.e("Tag", callApi.request().url().toString());
        Dialog dialog = new Dialog(context);
        showDialog(dialog);
        callApi.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.isSuccessful()) {
                    StatusResponse userResponse = response.body();
                    getIsCheckStudent().setValue(true);
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
                            txtMessage.setText("Thiết bị đã được đăng xuất");
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
                    getIsCheckStudent().setValue(false);
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
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
