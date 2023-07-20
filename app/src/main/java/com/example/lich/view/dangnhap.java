package com.example.lich.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lich.databinding.ActivityDangnhapBinding;
import com.example.lich.viewmodel.LoginViewModel;
import com.example.lich.viewmodel.getdulieulich;

public class dangnhap extends AppCompatActivity {
    private ActivityDangnhapBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangnhapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.login("DTC1854802010010", "anh01");

        binding.loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String taikhoan = binding.username.getText().toString();
                String matkhau = binding.password.getText().toString();
                if (taikhoan.equals("") && matkhau.equals("")) {
                    Toast.makeText(dangnhap.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show();


                } else {
                    //  dn.dangnhap(taikhoan,matkhau,url);
                    loginViewModel.login(taikhoan, matkhau);
                }

            }
        });
        loginViewModel.getIsLogin().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.e("Trạng thái đăng nhập", String.valueOf(aBoolean));
            }
        });

        binding.datlich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dangnhap.this, MainDatlich.class));
            }
        });
    }
}