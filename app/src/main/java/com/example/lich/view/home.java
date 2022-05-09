package com.example.lich.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lich.Model.Sinhvien;
import com.example.lich.databinding.HomeCalendarBinding;
import com.example.lich.Model.TKB;
import com.example.lich.R;

import java.util.ArrayList;

public class home extends AppCompatActivity {



    HomeCalendarBinding binding;

    static String tensv = " ", Ma = " ", khoa = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Anhxa();
        nhandulieu();
    }
    public void Anhxa()
    {

        binding.Thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,ThongtinActivity.class));
            }
        });
        binding.NutLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,MainActivity.class));
            }
        });

        binding.SuKien.setOnClickListener(new View.OnClickListener() {
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
        binding.txtTenSV.setText(tensv);
        binding.txtMaSV.setText(Ma);
        binding.txtKhoa.setText(khoa);
    }
}