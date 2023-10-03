package com.example.lich.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lich.R;
import com.example.lich.view.samonline.CustomCalendarEvent;

public class MainDatlich extends AppCompatActivity {
    CustomCalendarEvent customDatlich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calendar_event);
        customDatlich = findViewById(R.id.maindatlich);

    }

    @Override
    protected void onResume() {
        super.onResume();
        customDatlich.SetUpCalender();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}