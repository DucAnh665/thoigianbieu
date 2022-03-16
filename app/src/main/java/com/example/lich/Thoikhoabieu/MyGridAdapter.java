package com.example.lich.Thoikhoabieu;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lich.Model.TKB;
import com.example.lich.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyGridAdapter extends ArrayAdapter {
    List<Date> dates;
    Calendar currentdate;
    List<TKB> events;
    LayoutInflater inflater;

    public MyGridAdapter(@NonNull Context context,List<Date> dates,Calendar currentdate,List<TKB>events) {
        super(context, R.layout.ctlich);
        this.dates = dates;
        this.currentdate = currentdate;
        this.events = events;
        inflater = LayoutInflater.from(context);

    }
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM",Locale.ENGLISH);
    SimpleDateFormat YearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    @NonNull
    @Override
    public View getView(int i, @Nullable View view, @NonNull ViewGroup parent)
    {

        Date month = dates.get(i);
        Calendar datecale = Calendar.getInstance();
        datecale.setTime(month);
        int dayno = datecale.get(Calendar.DAY_OF_MONTH);
        int displaymonth = datecale.get(Calendar.MONTH)+1;
        int displayyear = datecale.get(Calendar.YEAR);
        int curenMonth = currentdate.get(Calendar.MONTH)+1;
        int currenyear = currentdate.get(Calendar.YEAR);

        if (view==null)
        {
            view = inflater.inflate(R.layout.ctlich,parent,false);
        }
        if (displaymonth==curenMonth && displayyear == currenyear)
        {
            view.setBackgroundColor(getContext().getResources().getColor(R.color.teal_200));
        }
        else
        {
            view.setBackgroundColor(Color.parseColor("#cccccc"));
        }
        TextView day = view.findViewById(R.id.day);
        TextView number = view.findViewById(R.id.number);
        day.setText(String.valueOf(dayno));
        int cout = 0;
        String dayone = String.valueOf(dayno);
        String curyear = YearFormat.format(datecale.getTime());
        String curmonth = monthFormat.format(datecale.getTime());
        for (i=0;i<events.size();i++) {
            if (events.get(i).getNgay().equals(dayone) && events.get(i).getThang().equals(curmonth)&&events.get(i).getNam().equals(curyear)) {
                cout++;
                String sukien = String.valueOf(cout);
                number.setText(sukien + "Môn");
                number.setVisibility(View.VISIBLE);
                view.setBackgroundColor(Color.parseColor("#f11cfe"));
            }
        }

        return view;
    }

    @Override
    public int getCount()
    {
        return dates.size();
    }
    @Override
    public int getPosition(@Nullable Object item)
    {
        return  dates.indexOf(item);
    }
    @Nullable
    @Override
    public Object getItem(int i)
    {
        return dates.get(i);
    }
}

