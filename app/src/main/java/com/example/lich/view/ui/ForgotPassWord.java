package com.example.lich.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lich.databinding.ActivityChangePasswordBinding;
import com.example.lich.databinding.ActivityForgotPassWordBinding;
import com.example.lich.viewmodel.ChangePassViewModel;
import com.example.lich.viewmodel.ForgotPassViewModel;

public class ForgotPassWord extends AppCompatActivity implements View.OnClickListener {

    private ActivityForgotPassWordBinding binding;
    private ForgotPassViewModel forgotPassViewModel;

    private ChangePassViewModel changePassViewModel;
    private String codeStudent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPassWordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
        initListener();
    }

    public void initData() {
        changePassViewModel = new ViewModelProvider(this).get(ChangePassViewModel.class);
        forgotPassViewModel = new ViewModelProvider(this).get(ForgotPassViewModel.class);
        forgotPassViewModel.getIsCheckCodeStudent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.txtPassword.setVisibility(View.VISIBLE);
                    binding.txtPasswordAgain.setVisibility(View.VISIBLE);
                    binding.username.setVisibility(View.GONE);
                    binding.btnForgotPassword.setText("Đổi mật khẩu");
                    binding.btnForgotPassword.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String passWord = binding.txtPassword.getText().toString();
                            String passWordAgain = binding.txtPasswordAgain.getText().toString();
                            if (!passWordAgain.equals("") && !passWord.equals("")) {
                                if (passWordAgain.equals(passWord)) {
                                    changePassViewModel.changePassWord(passWord, ForgotPassWord.this);
                                } else {
                                    Toast.makeText(ForgotPassWord.this, "Mật khẩu không trùng", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(ForgotPassWord.this, "Không chính xác", Toast.LENGTH_LONG).show();
                }
            }
        });

        changePassViewModel.getIsChangeSuccessful().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    finish();
                } else {
                    Toast.makeText(ForgotPassWord.this, "Đổi mật khẩu không thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void initListener() {
        binding.btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeStudent = binding.username.getText().toString();
                if (!codeStudent.equals("")) {
                    forgotPassViewModel.checkCodeStudent(codeStudent, ForgotPassWord.this);
                } else {
                    Toast.makeText(ForgotPassWord.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {

    }
}