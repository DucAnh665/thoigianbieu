package com.example.lich.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lich.Model.Events;
import com.example.lich.R;
import com.example.lich.even.Adaptereven;
import com.example.lich.viewmodel.DBOpen;
import com.example.lich.even.DBStruct;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class listvieweven extends AppCompatActivity {
    ListView lveven;
    TextView thongbao;
    ArrayList<Events> dulieu = new ArrayList<>();
      Adaptereven da;
      DBOpen db;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM yyyy", Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM",Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy",Locale.ENGLISH);
    SimpleDateFormat eventDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
    String ngay = " ",thang = " ",nam = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_newenvent_layout);
        CollectEventsPerMonth(monthFormat.format(calendar.getTime()),yearFormat.format(calendar.getTime()));
        lveven = findViewById(R.id.EventsRV);
        thongbao = findViewById(R.id.thongbaongay);
        nhandulieu();
        da = new Adaptereven(R.layout.show_events,listvieweven.this,dulieu,ngay,thang,nam);
        lveven.setAdapter(da);
        setupevent();
    }

    private  void CollectEventsPerMonth(String Month,String year)
    {   dulieu.clear();
        db = new DBOpen(listvieweven.this);
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = db.ReadEventsperMonth(Month,year,database);
        while (cursor.moveToNext())
        {
            String event = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.EVENT));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.TIME));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.DATE));
            String month = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.MONTH));
            String Year = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.YEAR));
            Events events = new Events(event,time,date,month,Year);
            dulieu.add(events);
        }
        cursor.close();
        db.close();
    }
    public ArrayList<Events> CollectEventByDate(String date,String thang)
    {
        ArrayList<Events> arrayList = new ArrayList<>();
        db = new DBOpen(listvieweven.this);
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = db.ReadEvents(date,thang,database);
        while (cursor.moveToNext()){
            String event = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.EVENT));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.TIME));
            String Date = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.DATE));
            String month = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.MONTH));
            String Year = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.YEAR));
            Events events = new Events(event,time,Date,month,Year);
            arrayList.add(events);
        }
        cursor.close();
        db.close();

        return  arrayList;

    }

    public void nhandulieu()
    {
        Bundle bundle = getIntent().getExtras();
        ngay = bundle.getString("t1");
        thang = bundle.getString("t2");
        nam = bundle.getString("t3");
        thongbao.setText(ngay+"/"+thang+"/"+nam);

    }
    public  void setupevent()
    {
        da = new Adaptereven(R.layout.show_events,listvieweven.this,CollectEventByDate(ngay,thang),ngay,thang,nam);
        lveven.setAdapter(da);

    }



}
