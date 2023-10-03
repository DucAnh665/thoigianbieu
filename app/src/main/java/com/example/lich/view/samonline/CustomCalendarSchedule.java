package com.example.lich.view.samonline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.lich.Model.Schedule;
import com.example.lich.R;
import com.example.lich.shared.DataStoreSubject;
import com.example.lich.view.ui.listviewtkb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomCalendarSchedule extends LinearLayout {
    final static String TAG = "CustomCalendar";
    GridView grid;
    public static final int MAX_CALENDAR_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;
    TextView ngay;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM yyyy", Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.ENGLISH);
    SimpleDateFormat YearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    MyGridAdapterSchedule da = null;
    String curdate = " ", curyear = " ";
    String curmont = " ";

    String url = "https://csdlapp.000webhostapp.com/getteam.php";
    static public List<Schedule> dulieu = new ArrayList<>();

    public DataStoreSubject dataStoreSubject;
    ArrayList<Date> dates = new ArrayList<>();
    ImageButton tien, lui;


    Calendar daycale = Calendar.getInstance();
    int dayno;

    public CustomCalendarSchedule(Context context) {
        super(context);
    }

    public CustomCalendarSchedule(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void khoitao() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main_calendar_schedule, this);
        grid = view.findViewById(R.id.grid);
        ngay = view.findViewById(R.id.ngaythangnam);
        tien = view.findViewById(R.id.tien);
        lui = view.findViewById(R.id.lui);


        lui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH, -1);
                caidatlich();
            }
        });

        tien.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH, +1);
                caidatlich();
            }
        });
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                setupngay(i);
                ngay.setText(String.valueOf(dayno) + "/" + curmont + "/" + curyear);

            }
        });


        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                setupngay(i);
                Intent intent = new Intent(context, listviewtkb.class);
                Bundle bd = new Bundle();
                bd.putString("t1", String.valueOf(dayno));
                bd.putString("t2", curmont);
                bd.putString("t3", curyear);
                intent.putExtras(bd);
                context.startActivity(intent);

                return true;
            }
        });
    }


    public void setupngay(int i) {
        Date day = dates.get(i);
        daycale.setTime(day);
        dayno = daycale.get(Calendar.DAY_OF_MONTH);

    }

    private void caidatlich() {
        curdate = dateFormat.format(calendar.getTime());
        curyear = YearFormat.format(calendar.getTime());
        curmont = monthFormat.format(calendar.getTime());
        ngay.setText(curmont + "/" + curyear);
        dates.clear();
        Calendar monthca = (Calendar) calendar.clone();
        monthca.set(Calendar.DAY_OF_MONTH, 1);
        int firstdayofmonth = monthca.get(Calendar.DAY_OF_WEEK) - 1;
        monthca.add(Calendar.DAY_OF_MONTH, -firstdayofmonth);
        while (dates.size() < MAX_CALENDAR_DAYS) {
            dates.add(monthca.getTime());
            monthca.add(Calendar.DAY_OF_MONTH, 1);

        }
        dulieu = dataStoreSubject.loadData();
        da = new MyGridAdapterSchedule(context, dates, calendar, dataStoreSubject.loadData());
        grid.setAdapter(da);
    }

    public CustomCalendarSchedule(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        dataStoreSubject = new DataStoreSubject(context);
        khoitao();
        caidatlich();
    }
}
