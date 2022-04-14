package com.example.lich.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lich.Model.Sinhvien;
import com.example.lich.Model.TKB;
import com.example.lich.R;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    ImageButton datlich,Sukien,Thongtin;
    TextView hoten,masv;
    TextView Khoasv;

    static String tensv = " ", Ma = " ", khoa = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_calendar);
        Anhxa();
        nhandulieu();
    }
    public void Anhxa()
    {
        datlich = findViewById(R.id.NutLich);
        Sukien = findViewById(R.id.SuKien);
        hoten = findViewById(R.id.txtTenSV);
        masv = findViewById(R.id.txtMaSV);
        Khoasv = findViewById(R.id.txtKhoa);
        Thongtin = findViewById(R.id.Thongtin);
        Thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,ThongtinActivity.class));
            }
        });
        datlich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,MainActivity.class));
            }
        });

        Sukien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this, MainDatlich.class));
            }
        });

    }
    public void nhandulieu()
    {
        Sinhvien sv = (Sinhvien) getIntent().getSerializableExtra("thongtin");
        tensv = sv.getTensv();
        Ma = sv.getMasv();
        khoa = sv.getKhoa();
        hoten.setText(tensv);
        masv.setText(Ma);
        Khoasv.setText(khoa);
    }
}