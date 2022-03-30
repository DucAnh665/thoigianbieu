package com.example.lich.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lich.Model.Sinhvien;
import com.example.lich.R;
import com.example.lich.viewmodel.Dangnhap;

import java.util.ArrayList;

public class dangnhap extends AppCompatActivity {

    Button btndangnhap;
    TextView txtmasv,txtmatkhau;
    String url = "https://csdlapp.000webhostapp.com/login.php";
    Dangnhap dn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        dn = new Dangnhap();
        btndangnhap = findViewById(R.id.loginbutton);
        txtmasv = findViewById(R.id.username);
        txtmatkhau = findViewById(R.id.password);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String taikhoan =  txtmasv.getText().toString();
                String matkhau = txtmatkhau.getText().toString();
                if (taikhoan.equals("")&&matkhau.equals(""))
                {
                    startActivity(new Intent(dangnhap.this,home.class));                }
                else
                {
                   // dn.dangnhap(taikhoan,matkhau,url,dangnhap.this);


                }

            }
        });
    }
}