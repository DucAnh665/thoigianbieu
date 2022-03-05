package com.example.lich.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lich.R;
import com.example.lich.even.CustomDatlich;

public class MainDatlich extends AppCompatActivity {
    CustomDatlich customDatlich ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_datlich);

        customDatlich = findViewById(R.id.maindatlich);

    }
}