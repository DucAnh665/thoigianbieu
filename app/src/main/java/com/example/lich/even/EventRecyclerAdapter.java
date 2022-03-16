package com.example.lich.even;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lich.Model.Events;
import com.example.lich.R;

import java.util.ArrayList;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<Events> arrayList ;
    DBOpen dbOpenHelper;

    public EventRecyclerAdapter(Context context, ArrayList<Events> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Events events= arrayList.get(i);
        holder.Events.setText(events.getEVENT());
        holder.Events.setText(events.getDATE());
        holder.Events.setText(events.getTIME());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCalendarEvent(events.getEVENT(),events.getDATE(), events.getTIME());
                arrayList.remove (i);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView DateTxt,Events,Time;
        Button delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            DateTxt = itemView.findViewById(R.id.eventdate);
            Events = itemView.findViewById(R.id.eventname);
            Time = itemView.findViewById(R.id.eventtime);
            delete = itemView.findViewById(R.id.delete);
        }


    }
    private  void deleteCalendarEvent(String event,String date,String time)
    {
        dbOpenHelper = new DBOpen(context);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.deleteEvent(event,date,time,database);
        dbOpenHelper.close();

    }


}
