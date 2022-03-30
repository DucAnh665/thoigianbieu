package com.example.lich.even;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lich.Model.Events;
import com.example.lich.Model.TKB;
import com.example.lich.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Adaptereven extends BaseAdapter {

    int Layout;
    Context context;
    ArrayList<Events> dulieu;
    DBOpen dbOpen ;

    public Adaptereven(int layout, Context context, ArrayList<Events> dulieu) {
        Layout = layout;
        this.context = context;
        this.dulieu = dulieu;
    }

    @Override
    public int getCount() {
        return dulieu.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class viewhodel
    {
        TextView even,time,date;
        Button btxoa;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewhodel hodel;


        if (view == null)
        {
            hodel = new viewhodel();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(Layout,null);
            hodel.even = view.findViewById(R.id.eventname);
            hodel.time =  view.findViewById(R.id.eventtime);
            hodel.date = view.findViewById(R.id.eventdate);
            hodel.btxoa = view.findViewById(R.id.delete);
            view.setTag(hodel);

        }
        else
        {
            hodel = (viewhodel) view.getTag();
        }
        Events list = dulieu.get(i);
        hodel.even.setText(list.getEVENT());
        hodel.date.setText(list.getDATE()+"/"+list.getMONTH()+"/"+list.getYEAR());
        hodel.time.setText(list.getTIME());
        hodel.btxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCalenderEvent(list.getEVENT(),list.getDATE(),list.getTIME());
                dulieu.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }
    private  void  deleteCalenderEvent(String event,String date, String time)
    {
        dbOpen = new DBOpen(context);
        SQLiteDatabase database = dbOpen.getWritableDatabase();
        dbOpen.deleteEvent(event,date,time,database);
        dbOpen.close();
    }

}
