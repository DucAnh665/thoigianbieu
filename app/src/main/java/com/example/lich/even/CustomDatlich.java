package com.example.lich.even;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
    private  static  final int MAX_CALENDER_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM yyyy",Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM",Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy",Locale.ENGLISH);

    MyGridDatLich myGridDatLich = null;

    String ngay = " ",thang = " ";
    String nam = " ";
    DBOpen dbOpenHelper;
    AlertDialog alertDialog;
    List<Date> dates = new ArrayList<>();
    List<Events> eventsList = new ArrayList<>();

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
                EditText Eventname = addView.findViewById(R.id.eventname);
                EditText Eventtime = addView.findViewById(R.id.eventtime);
                Button Buttonthem = addView.findViewById(R.id.addsukien);

                final String date = dateFormat.format(dates.get(i));
                final String month = monthFormat.format(dates.get(i));
                final String year = yearFormat.format(dates.get(i));

                Buttonthem.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SaveEvent(Eventname.getText().toString(),Eventtime.getText().toString(),date,month,year);

                    }
                });
                builder.setView(addView);
                alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

    public CustomDatlich(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void SaveEvent(String events,String time,String date,String month,String year)
    {
        dbOpenHelper = new DBOpen(context);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.SaveEvent(events,time,date,month,year,database);
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
        //String ngay = dateFormat.format(calenda r.getTime());
        String thang = monthFormat.format(calendar.getTime());
        String nam = yearFormat.format(calendar.getTime());
        Current.setText(thang+"/"+nam);
        dates.clear();
        Calendar monthca = (Calendar) calendar.clone();
        monthca.set(Calendar.DAY_OF_MONTH,1);
        int firstdayofmonth = monthca.get(Calendar.DAY_OF_WEEK)-1;
        monthca.add(Calendar.DAY_OF_MONTH,-firstdayofmonth);
        while (dates.size()<MAX_CALENDER_DAYS)
        {
            dates.add(monthca.getTime());
            monthca.add(Calendar.DAY_OF_MONTH,1);

        }

        myGridDatLich = new MyGridDatLich(context,dates,calendar,eventsList);
        gridView.setAdapter(myGridDatLich);

    }


}
