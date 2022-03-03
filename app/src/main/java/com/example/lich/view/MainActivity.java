package com.example.lich.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lich.Thoikhoabieu.CustomCalendar;
import com.example.lich.R;

public class MainActivity extends AppCompatActivity {

    CustomCalendar ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ct = (CustomCalendar) findViewById(R.id.custom);
    }
}