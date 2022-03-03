package com.example.lich.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lich.Model.Sinhvien;
import com.example.lich.R;

import java.util.ArrayList;

public class dangnhap extends AppCompatActivity {

    Button btndangnhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        ArrayList<Sinhvien> dulieu = new ArrayList<>();
        dulieu.add(new Sinhvien(1,"Nguyễn Đức Anh",
                "DTC1854802010010",
                "06/07/2000",
                "ducanh01",
                "anh123",
                "Khóa 17"));
        btndangnhap = findViewById(R.id.loginbutton);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<dulieu.size();i++)
                {
                    Intent intent = new Intent(dangnhap.this,home.class);
                    intent.putExtra("thongtin",dulieu.get(i));
                    startActivity(intent);

                                    }
            }
        });
    }
}