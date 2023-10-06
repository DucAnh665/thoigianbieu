package com.example.lich.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lich.databinding.ActivityChangePasswordBinding;
import com.example.lich.viewmodel.ChangePassViewModel;

public class ChangePassword extends AppCompatActivity {

    private ActivityChangePasswordBinding binding;
    private ChangePassViewModel changePassViewModel;
    private String taikhoan = "";
    private String matkhau = "";
    private String nhaplaimatkhau = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        changePassViewModel = new ViewModelProvider(this).get(ChangePassViewModel.class);
        initListeneṛ();
        changePassViewModel.getIsChangeSuccessful().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    finish();
                } else {
                    Toast.makeText(ChangePassword.this, "Đổi mật khẩu không thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void initListeneṛ() {
        binding.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matkhau = binding.password.getText().toString();
                nhaplaimatkhau = binding.passwordAgain.getText().toString();
                if (!matkhau.equals("") && !nhaplaimatkhau.equals("")) {
                    if (matkhau.equals(nhaplaimatkhau)) {
                        changePassViewModel.changePassWord(matkhau, ChangePassword.this);
                    } else {
                        Toast.makeText(ChangePassword.this, "Mật khẩu nhập không khớp", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ChangePassword.this, "Thông tin không được để trống", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}