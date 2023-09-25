package com.example.lich.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.lich.R;
import com.example.lich.even.CustomDatlich;

public class MainDatlich extends AppCompatActivity {
    CustomDatlich customDatlich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_datlich);
        customDatlich = findViewById(R.id.maindatlich);

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter("calendar");
        registerReceiver(receiver, filter);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String day = intent.getStringExtra("day");
            String month = intent.getStringExtra("month");
            String year = intent.getStringExtra("year");
            Log.e("MainDatLich", day + "/" + month + "/" + year);
        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}