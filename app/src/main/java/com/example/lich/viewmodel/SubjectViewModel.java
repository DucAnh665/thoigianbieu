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
import com.example.lich.Model.SubjectReponse;
import com.example.lich.R;
import com.example.lich.view.dialog.DialogCustomer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubjectViewModel extends ViewModel {
    private MutableLiveData<Boolean> isCheckGetSubject = new MutableLiveData<>();


    private MutableLiveData<SubjectReponse> liveData = new MutableLiveData<>();

    private LinearLayout btnSend;
    private TextView txtMessage;


    public MutableLiveData<SubjectReponse> getLiveData() {
        return liveData;
    }

    public void setLiveData(MutableLiveData<SubjectReponse> liveData) {
        this.liveData = liveData;
    }

    public MutableLiveData<Boolean> getIsCheckGetSubject() {
        return isCheckGetSubject;
    }

    public void setIsCheckGetSubject(MutableLiveData<Boolean> isCheckGetSubject) {
        this.isCheckGetSubject = isCheckGetSubject;
    }

    public void getSubject(Context context, String codeStudent) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WSConfig.Base_Log)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Dialog dialog = new Dialog(context);
        showDialog(dialog);
        BaseDataService service = retrofit.create(BaseDataService.class);
        Call<SubjectReponse> callApi = service.getSubject(codeStudent);
        callApi.enqueue(new Callback<SubjectReponse>() {
            @Override
            public void onResponse(Call<SubjectReponse> call, Response<SubjectReponse> response) {
                SubjectReponse subjectReponse = response.body();
                if (response.isSuccessful()) {
                    Log.e("Trạng thái", subjectReponse.getMessage());
                    isCheckGetSubject.setValue(true);
                    liveData.setValue(subjectReponse);
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
                    isCheckGetSubject.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<SubjectReponse> call, Throwable t) {
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
