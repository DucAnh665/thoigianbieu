package com.example.lich.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lich.Model.UserResponse;
import com.example.lich.databinding.ActivityLoginBinding;
import com.example.lich.shared.DataUserStorage;
import com.example.lich.viewmodel.LoginViewModel;

import java.util.List;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    private DataUserStorage dataUserStorage;
    private String taikhoan = "";
    private String matkhau = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        dataUserStorage = new DataUserStorage(this);
        binding.loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taikhoan = binding.username.getText().toString();
                matkhau = binding.password.getText().toString();
                if (taikhoan.equals("") && matkhau.equals("")) {
                    Toast.makeText(Login.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show();
                } else {
                    //  dn.dangnhap(taikhoan,matkhau,url);
                    loginViewModel.login(taikhoan, matkhau, Login.this);
                }
            }
        });


        loginViewModel.getDataUser().observe(this, new Observer<List<UserResponse.User>>() {
            @Override
            public void onChanged(List<UserResponse.User> users) {
                Log.e("Thông tin sinh viên", users.get(0).getUserName());
                dataUserStorage.saveData(users.get(0).getUserName(),
                        users.get(0).getCodeStudent(),
                        users.get(0).getNameClass(),
                        users.get(0).getNameFaculty(),
                        users.get(0).getImage(),
                        users.get(0).getPassWord(),
                        users.get(0).getAddress(),
                        users.get(0).getBirthday()
                );


            }
        });
        loginViewModel.getIsLogin().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    startActivity(new Intent(Login.this, home.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Thông tin không chính xác", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.datlich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, MainDatlich.class));
            }
        });

        binding.btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, ForgotPassWord.class));
            }
        });
        getPermissionOverlay();
        binding.cbShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.cbShowPassword.isChecked()) {
                    binding.password.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    binding.password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

    }


    private void getPermissionOverlay() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 101);
            Log.e("Thông báo", "Quyền chưa được cấp");
        } else {
            Log.e("Thông báo", "Quyền đã được cấp");
            if (dataUserStorage.loadData() != null && !dataUserStorage.loadData().getUserName().equals("")) {
                taikhoan = dataUserStorage.loadData().getCodeStudent();
                matkhau = dataUserStorage.loadData().getPassWord();
                startActivity(new Intent(this, home.class));
            } else {

            }
        }
    }
}