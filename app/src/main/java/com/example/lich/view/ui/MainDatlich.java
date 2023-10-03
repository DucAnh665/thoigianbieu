package com.example.lich.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.lich.R;
import com.example.lich.view.samonline.CustomCalendarEvent;

public class MainDatlich extends AppCompatActivity {
    CustomCalendarEvent customDatlich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_datlich);
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