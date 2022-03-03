package com.example.lich.even;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

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
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM yyyy",Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("MM yyyy",Locale.ENGLISH);


    String ngay = " ",thang = " ";
    String nam = " ";
    List<Date> dates = new ArrayList<>();
    List<Events> eventsList = new ArrayList<>();

    public CustomDatlich(Context context) {

        super(context);
    }

    public CustomDatlich(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        IntializeLayout();
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
    }

    public CustomDatlich(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

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
        ngay = dateFormat.format(calendar.getTime());
        thang = monthFormat.format(calendar.getTime());
        nam = yearFormat.format(calendar.getTime());
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



    }
}
