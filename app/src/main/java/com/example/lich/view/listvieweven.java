package com.example.lich.view;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lich.Model.Events;
import com.example.lich.R;
import com.example.lich.even.Adaptereven;


import java.util.ArrayList;

public class listvieweven extends AppCompatActivity {
    ListView lveven;
    ArrayList<Events> dulieu = new ArrayList<>();
   Adaptereven da;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_newenvent_layout);
        lveven = findViewById(R.id.EventsRV);
        da = new Adaptereven(R.layout.show_events,listvieweven.this,dulieu);
        lveven.setAdapter(da);
    }

}
