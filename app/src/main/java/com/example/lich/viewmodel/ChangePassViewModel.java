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
import com.example.lich.Model.UserResponse;
import com.example.lich.R;
import com.example.lich.shared.DataUserStorage;
import com.example.lich.view.dialog.DialogCustomer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangePassViewModel extends ViewModel {

    private MutableLiveData<Boolean> isChangeSuccessful = new MutableLiveData<>();

    private LinearLayout btnSend;
    private TextView txtMessage;
    private DataUserStorage dataUserStorage;


    public MutableLiveData<Boolean> getIsChangeSuccessful() {
        return isChangeSuccessful;
    }

    public void changePassWord(String codeStudent, String passWord, Context context) {
        dataUserStorage = new DataUserStorage(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WSConfig.Base_Log)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseDataService service = retrofit.create(BaseDataService.class);
        Call<StatusResponse> callApi = service.changePassWord(codeStudent, passWord);
        Dialog dialog = new Dialog(context);
        showDialog(dialog);
        callApi.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                StatusResponse statusResponse = response.body();
                if (response.isSuccessful()) {
                    Log.e("Trạng thái", statusResponse.getMassage());
                    isChangeSuccessful.setValue(true);
                    dataUserStorage.loadData().setPassWord(passWord);
                    dialog.dismiss();
                } else {
                    DialogCustomer.showDialog(context, R.layout.dialog_send_fail, new DialogCustomer.OnButtonClickListener() {
                        @Override
                        public void findId(Dialog dialog) {
                            btnSend = dialog.findViewById(R.id.btn_send_again);
                            txtMessage = dialog.findViewById(R.id.txt_dialog_message);
                        }

                        @Override
                        public void onListener(Dialog dialog) {
                            txtMessage.setText(response.message());
                            btnSend.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                        }
                    });
                    dialog.dismiss();
                    getIsChangeSuccessful().setValue(false);
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
