package com.example.lich.view.samonline;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lich.Model.Events;
import com.example.lich.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyGridCalendarEvent extends ArrayAdapter {

    List<Date> dates;
    Calendar currentdate;
    List<Events> events;
    LayoutInflater inflater;



    public MyGridCalendarEvent(@NonNull Context context, List<Date> dates, Calendar currentdate, List<Events> events) {
        super(context, R.layout.item_event);
        this.dates= dates;
        this.currentdate = currentdate;
        this.events = events;
        inflater = LayoutInflater.from(context);


    }
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd",Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM",Locale.ENGLISH);
    SimpleDateFormat YearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Date month = dates.get(position);
        Calendar datecale = Calendar.getInstance();
        datecale.setTime(month);

        int dayno = datecale.get(Calendar.DAY_OF_MONTH);
        int displaymonth = datecale.get(Calendar.MONTH)+1;
        int displayyear = datecale.get(Calendar.YEAR);
        int curenMonth = currentdate.get(Calendar.MONTH)+1;
        int currenyear = currentdate.get(Calendar.YEAR);

        if (convertView==null)
        {
            convertView = inflater.inflate(R.layout.item_event,parent,false);
        }
        if (displaymonth==curenMonth && displayyear==currenyear)
        {
            convertView.setBackgroundColor(getContext().getResources().getColor(R.color.light_blue_900));
        }
        else
        {
            convertView.setBackgroundColor(Color.parseColor("#cccccc"));
        }
        TextView day =  convertView.findViewById(R.id.eventday);
        TextView number = convertView.findViewById(R.id.eventnumber);
        day.setText(String.valueOf(dayno));
        int cout = 0;
        String dayone = String.valueOf(dayno);
        String curyear = YearFormat.format(datecale.getTime());
        String curmonth = monthFormat.format(datecale.getTime());
        for (position=0;position<events.size();position++)
            if (events.get(position).getDATE().equals(dayone)&&events.get(position).getMONTH().equals(curmonth)&&events.get(position).getYEAR().equals(curyear))
        {
                cout++;
                String sukien = String.valueOf(cout);
                number.setText(sukien + "Sự kiện");
                number.setVisibility(View.VISIBLE);
                convertView.setBackgroundColor(getContext().getResources().getColor(R.color.teal_200));

        }

        return convertView;
    }



    @Override
    public int getCount() {
        return dates.size();
    }
    @Override
    public int getPosition(@Nullable Object item) {
        return dates.indexOf(item);
    }
    @Nullable
    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }




}
