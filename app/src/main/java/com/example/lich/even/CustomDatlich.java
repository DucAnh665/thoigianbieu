package com.example.lich.even;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.lich.Model.Events;
import com.example.lich.R;
import com.example.lich.view.listvieweven;
import com.example.lich.viewmodel.DBOpen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomDatlich extends LinearLayout {
    ImageButton Next,Pre;
    TextView Current;
    GridView gridView;
    private  static  final int MAX_CALENDAR_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;


    SimpleDateFormat dateFormat = new SimpleDateFormat("dd",Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM",Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy",Locale.ENGLISH);
   // SimpleDateFormat eventDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);

    String curdate = " ",curyear = " ";
    String curmont = " ";
    int dayno;

    MyGridDatLich myGridDatLich ;

    AlertDialog alertDialog;
    List<Date> dates = new ArrayList<>();
  public  static  ArrayList<Events> eventsList = new ArrayList<>();
    Calendar daycale = Calendar.getInstance();
    DBOpen dbOpenHelper;


    public CustomDatlich(Context context) {

        super(context);
    }

    public CustomDatlich(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        IntializeLayout();
        SetUpCalender();

        Pre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                 calendar.add(Calendar.MONTH,-1);
                 SetUpCalender();
            }
        });
        Next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH,1);
                SetUpCalender();
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_newevent_layout,null);
                EditText Eventname = addView.findViewById(R.id.eventnames);
                EditText Eventtime = addView.findViewById(R.id.eventtimes);
                Button Buttonthem = addView.findViewById(R.id.addsukien);



                String date = dateFormat.format(dates.get(i));
                String month = monthFormat.format(dates.get(i));
                String year = yearFormat.format(dates.get(i));

                Buttonthem.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SaveEvent(Eventname.getText().toString(),Eventtime.getText().toString(),date,month,year);
                        SetUpCalender();
                        alertDialog.dismiss();

                    }
                });
                builder.setView(addView);
                alertDialog = builder.create();
                alertDialog.show();


            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                setupngay(i);
                Intent intent = new Intent(context, listvieweven.class);
                Bundle bd = new Bundle();
                bd.putString("t1",String.valueOf(dayno));
                bd.putString("t2",curmont);
                bd.putString("t3",curyear);
                intent.putExtras(bd);
                context.startActivity(intent);
                return false;
            }

        });
    }


    public CustomDatlich(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    public void setupngay(int i)
    {
        Date day = dates.get(i);
        daycale.setTime(day);
        dayno = daycale.get(Calendar.DAY_OF_MONTH);


    }

    private void SaveEvent(String event,String time,String date,String month,String year)
    {
        dbOpenHelper = new DBOpen(context);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.SaveEvent(event,time,date,month,year,database);
        dbOpenHelper.close();
        Toast.makeText(context, "Events Saved", Toast.LENGTH_SHORT).show();
    }

    private void IntializeLayout()
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_datlich,this);
        Next = view.findViewById(R.id.next);
        Pre = view.findViewById(R.id.pre);
        Current = view.findViewById(R.id.current);
        gridView = view.findViewById(R.id.grid);
    }
    private void SetUpCalender()
    {

        curdate = dateFormat.format(calendar.getTime());
        curyear = yearFormat.format(calendar.getTime());
        curmont = monthFormat.format(calendar.getTime());
        Current.setText(curmont+"/"+curyear);

        dates.clear();
        Calendar monthca = (Calendar) calendar.clone();
        monthca.set(Calendar.DAY_OF_MONTH,1);
        int firstdayofmonth = monthca.get(Calendar.DAY_OF_WEEK)-1;
        monthca.add(Calendar.DAY_OF_MONTH,-firstdayofmonth);

        CollectEventsPerMonth(monthFormat.format(calendar.getTime()),yearFormat.format(calendar.getTime()));

        while (dates.size() < MAX_CALENDAR_DAYS)
        {
            dates.add(monthca.getTime());
            monthca.add(Calendar.DAY_OF_MONTH,1);

        }

        myGridDatLich = new MyGridDatLich(context,dates,calendar,eventsList);
        gridView.setAdapter(myGridDatLich);

    }
    private  void CollectEventsPerMonth(String Month,String year)
    {
        eventsList.clear();
        dbOpenHelper = new DBOpen(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.ReadEventsperMonth(Month,year,database);
        while (cursor.moveToNext())
        {
            String event = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.EVENT));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.TIME));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.DATE));
            String month = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.MONTH));
            String Year = cursor.getString(cursor.getColumnIndexOrThrow(DBStruct.YEAR));
            Events events = new Events(event,time,date,month,Year);
            eventsList.add(events);
        }
        cursor.close();
        dbOpenHelper.close();
    }


}
