package com.example.lich.even;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyGridDatLich extends ArrayAdapter {

    List<Date> dates;
    Calendar currentdate;
    List<Events> events;
    LayoutInflater inflater;



    public MyGridDatLich(@NonNull Context context, List<Date> dates, Calendar currentdate, List<Events> events) {
        super(context, R.layout.activity_datlich);

        this.dates= dates;
        this.currentdate = currentdate;
        this.events = events;
        inflater = LayoutInflater.from(context);


    }


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
            convertView = inflater.inflate(R.layout.ctevent,parent,false);
        }
        if (displaymonth==curenMonth && displayyear == currenyear)
        {
            convertView.setBackgroundColor(getContext().getResources().getColor(R.color.teal_200));
        }
        else
        {
            convertView.setBackgroundColor(Color.parseColor("#cccccc"));
        }

        TextView eventday = convertView.findViewById(R.id.eventday);
        TextView eventnumber = convertView.findViewById(R.id.eventnumber);
        eventday.setText(String.valueOf(dayno));

        Calendar eventcalendar = Calendar.getInstance();
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < events.size() ; i++)
        {
            eventcalendar.setTime(ConvertStringToDate(events.get(i).getDATE()));
            if(dayno == eventcalendar.get(Calendar.DAY_OF_MONTH) && displaymonth == eventcalendar.get(Calendar.MONTH)+1
            && displayyear == eventcalendar.get(Calendar.YEAR))
            {
                arrayList.add(events.get(i).getEVENT());
                eventnumber.setText(arrayList.size() + " Events");
            }
        }



        return convertView;
    }

    private Date ConvertStringToDate(String eventDate)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try{
            date = format.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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
