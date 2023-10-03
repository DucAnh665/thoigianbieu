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
import com.example.lich.Model.User;
import com.example.lich.R;
import com.example.lich.shared.DataUserStorage;
import com.example.lich.view.dialog.DialogCustomer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateUserViewModel extends ViewModel {
    private MutableLiveData<User> data = new MutableLiveData<>();//livedata lắng nghe hanh động của view model

    private MutableLiveData<User.Data> dataUser = new MutableLiveData<>();


    private LinearLayout btnSend;
    private TextView txtMessage;


    private DataUserStorage dataUserStorage;

    public MutableLiveData<User> getData() {
        return data;
    }

    public MutableLiveData<User.Data> getDataUser() {
        return dataUser;
    }


    /// xét sữ liệu mặc đinh
    public void setDataUser(Context context) {
        dataUserStorage = new DataUserStorage(context);//khởi tạo bộ nhớ
        User.Data userdata = new User.Data();//khởi tạo data trong user
        userdata.setUserName(dataUserStorage.loadData().getUserName());//lấy tên tù bộ nhớ sett cho userdata
        userdata.setAddress(dataUserStorage.loadData().getAddress());
        userdata.setBirthday(dataUserStorage.loadData().getBirthday());
        userdata.setImage(dataUserStorage.loadData().getImage());//lấy ảnh từ bộ nhớ đt set cho userdata
        getDataUser().setValue(userdata);//lấy dl từ userdata set cho getdatauser

    }


    /// call api update thông tin sinh viên
    public void updateUser(Context context, String address, String birthday) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(WSConfig.Base_Log).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        BaseDataService service = retrofit.create(BaseDataService.class);
        Call<User> callUpdateUser = service.updateUser(dataUserStorage.loadData().getCodeStudent(), address, birthday);
        callUpdateUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    getData().setValue(user);
                    DataUserStorage storeUser = new DataUserStorage(context);
                    storeUser.saveData(user.getData().get(0).getUserName(), user.getData().get(0).getCodeStudent(), user.getData().get(0).getNameClass(),
                            user.getData().get(0).getNameFaculty(),
                            user.getData().get(0).getImage(),
                            user.getData().get(0).getPassWord(),
                            user.getData().get(0).getAddress(),
                            user.getData().get(0).getBirthday());
                    Log.e("Update User", user.getData().get(0).getUserName());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
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
