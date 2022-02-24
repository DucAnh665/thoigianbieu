package com.example.lich;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomCalendar extends LinearLayout {

    GridView grid;
    public  static final  int MAX_CALENDAR_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;
    TextView ngay;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM yyyy", Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM",Locale.ENGLISH);
    SimpleDateFormat YearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    MyGridAdapter da = null;
    String curdate = " ",curyear = " ";
    String curmont = " ";

    List<Date> dates = new ArrayList<>();
    List<TKB> tkb = new ArrayList<>();


    public CustomCalendar(Context context) {
        super(context);
    }

    public CustomCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        khoitao();
        caidatlich();
    }

    public CustomCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void khoitao()
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.lich,this);
        grid = view.findViewById(R.id.grid);
        ngay = view.findViewById(R.id.ngaythangnam);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Date day = dates.get(i);
                Calendar daycale = Calendar.getInstance();
                daycale.setTime(day);
                int dayno = daycale.get(Calendar.DAY_OF_MONTH);
                String dayone = String.valueOf(dayno);
                ngay.setText(dayone+"/"+curmont+"/"+curyear);
            }
        });


    }
    private void caidatlich()
    {

        curdate = dateFormat.format(calendar.getTime());
        curyear = YearFormat.format(calendar.getTime());
        curmont = monthFormat.format(calendar.getTime());
        ngay.setText(curmont+"/"+curyear);
        dates.clear();
        Calendar monthca = (Calendar) calendar.clone();
        monthca.set(Calendar.DAY_OF_MONTH,1);
        int firstdayofmonth = monthca.get(Calendar.DAY_OF_WEEK)-1;
        monthca.add(Calendar.DAY_OF_MONTH,-firstdayofmonth);
        while (dates.size()<MAX_CALENDAR_DAYS)
        {
            dates.add(monthca.getTime());
            monthca.add(Calendar.DAY_OF_MONTH,1);

        }
        da = new MyGridAdapter(context,dates,calendar,tkb);
        grid.setAdapter(da);



    }
}
