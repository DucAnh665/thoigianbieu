package com.example.lich.service;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.lich.Model.Events;
import com.example.lich.R;
import com.example.lich.shared.DataEventStore;
import com.example.lich.view.ui.home;
import com.example.lich.viewmodel.DBOpen;
import com.tatv.baseapp.service.BaseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SamOnlineService extends BaseService {


    private List<Events> dataEvent = new ArrayList<>();
    private DBOpen dbOpenHelper;

    private Calendar calendar = Calendar.getInstance();

    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy:HH:mm", Locale.getDefault());

    @Override
    protected void onReceiverBroadcast(Context context, Intent intent) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        createForegroundService("Không có lịch");
        collectEventsPerMonth(monthFormat.format(calendar.getTime()), yearFormat.format(calendar.getTime()));
    }


    public void createForegroundService(String event) {
        createNotification(context, "SAMONLINE", "SamOnline",
                event,
                R.drawable.samonline, home.class,
                SamOnlineService.class, "actionClose");
    }

    private void collectEventsPerMonth(String Month, String year) {
        dataEvent.clear();
        dbOpenHelper = new DBOpen(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.ReadEventsperMonth(Month, year, database);
        while (cursor.moveToNext()) {
            String event = cursor.getString(cursor.getColumnIndexOrThrow(DataEventStore.EVENT));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(DataEventStore.TIME));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DataEventStore.DATE));
            String month = cursor.getString(cursor.getColumnIndexOrThrow(DataEventStore.MONTH));
            String Year = cursor.getString(cursor.getColumnIndexOrThrow(DataEventStore.YEAR));
            Events events = new Events(event, time, date, month, Year);
            dataEvent.add(events);
        }
        cursor.close();
        dbOpenHelper.close();
        for (int i = 0; i < dataEvent.size(); i++)
            if (dataEvent.size() > 0) {
                String time = convertTime(dataEvent.get(i).getDATE(), dataEvent.get(i).getMONTH(), dataEvent.get(i).getYEAR(), dataEvent.get(i).getTIME());
                Log.e("TIME", time);
                if (calculateTimeDifference(time) < 1) {
                    createForegroundService(dataEvent.get(i).getEVENT());
                } else {
                    createForegroundService("Không có lịch");
                }
            }

    }

    public long calculateTimeDifference(String timeDate) {
        // Thời điểm ban đầu
        String endTimeStr = "20/09/2023:23:40";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm", Locale.getDefault());

        try {
            Date startTime = sdf.parse(timeDate);
            Date endTime = sdf.parse(endTimeStr);

            long diffMillis = endTime.getTime() - startTime.getTime();
            long diffHours = TimeUnit.MILLISECONDS.toHours(diffMillis);
            Log.e("tEST", diffHours + "");
            return diffHours;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String convertTime(String day, String month, String year, String time) {
        return day + "/" + month + "/" + year + ":" + time;
    }

    @Override
    protected String[] getIntentFilters() {
        return new String[]{
                "your.intent.filter.action1",
                "your.intent.filter.action2"
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
