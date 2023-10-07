package com.example.lich.service;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;

import com.example.lich.Model.Events;
import com.example.lich.R;
import com.example.lich.shared.DataEventStore;
import com.example.lich.shared.DataStoreSubject;
import com.example.lich.view.ui.home;
import com.example.lich.view.widget.WidgetCalendar;
import com.example.lich.view.widget.WidgetManager;
import com.example.lich.viewmodel.DBOpen;
import com.tatv.baseapp.service.BaseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SamOnlineService extends BaseService implements TimeService.TimerListener {


    private List<Events> dataEvent = new ArrayList<>();
    private DBOpen dbOpenHelper;

    private Calendar calendar = Calendar.getInstance();
    private DataStoreSubject dataStoreSubject;
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy:HH:mm", Locale.getDefault());
    String timeCurrent = "";


    @Override
    protected void onReceiverBroadcast(Context context, Intent intent) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        TimeService timeService = new TimeService(context, this);
        createForegroundService("Không có lịch");
    }


    public void createForegroundService(String event) {
        createNotification(context, "SAMONLINE", "SamOnline", event, R.drawable.samonline, home.class, SamOnlineService.class, "actionClose");

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
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
        createForegroundService("SamOnline đang chạy");

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
        return new String[]{"your.intent.filter.action1", "your.intent.filter.action2"};
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

    @Override
    public void onTimeChanged(String time) {
        this.timeCurrent = time;
        WidgetManager.getInstance().init(context);
        WidgetManager.getInstance().updateTime(context, time);
        WidgetManager.getInstance().updateTimeEvent(context, time);
    }

    private void updateEvent(String day) {
        collectEventsPerMonth(monthFormat.format(calendar.getTime()), yearFormat.format(calendar.getTime()));
        WidgetManager.getInstance().init(context);
        String dayCurrent = "";
        long minSeconds = Long.MAX_VALUE;
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy");
        if (dataEvent.size() > 0) {
            for (int i = 0; i < dataEvent.size(); i++) {
                dayCurrent = dataEvent.get(i).getDATE() + "/" + dataEvent.get(i).getMONTH() + "/" + dataEvent.get(i).getYEAR();
                if (day.equals(dayCurrent)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        if (LocalTime.parse(timeCurrent).isBefore(LocalTime.parse(dataEvent.get(i).getTIME()))) {

                            LocalTime currentLocalTime = LocalTime.parse(timeCurrent);
                            LocalTime eventLocalTime = LocalTime.parse(dataEvent.get(i).getTIME());
                            Duration duration = Duration.between(currentLocalTime, eventLocalTime);
                            long seconds = Math.abs(duration.getSeconds());
                            // So sánh và cập nhật giá trị tối thiểu và thời gian tương ứng
                            if (seconds < minSeconds) {
                                minSeconds = seconds;
                                WidgetManager.getInstance().updateCalendarEvent(context, dataEvent.get(i).getEVENT(), dataEvent.get(i).getTIME());
                            }
                            Log.e("Kiểm tra", seconds + "");
                        }
                    }
                }
            }

        }
    }

    private void updateSchedule(String day) {
        dataStoreSubject = new DataStoreSubject(context);
        WidgetManager.getInstance().init(context);
        String dayCurrent = "";
        boolean isCheckTime = false;
        boolean isCheckDay = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy");
        if (dataStoreSubject.loadData() != null && dataStoreSubject.loadData().size() > 0) {
            for (int i = 0; i < dataStoreSubject.loadData().size(); i++) {
                dayCurrent = dataStoreSubject.loadData().get(i).getNgay() + "/" + dataStoreSubject.loadData().get(i).getThang() + "/" + dataStoreSubject.loadData().get(i).getNam();
                if (day.equals(dayCurrent)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        if (LocalTime.parse(timeCurrent).isBefore(LocalTime.parse(dataStoreSubject.loadData().get(i).getEndDate()))) {
                            if (LocalTime.parse(dataStoreSubject.loadData().get(i).getStartDate()).isBefore(LocalTime.parse(timeCurrent)) && LocalTime.parse(dataStoreSubject.loadData().get(i).getEndDate()).isAfter(LocalTime.parse(timeCurrent))) {
                                isCheckTime = false;
                            } else {
                                isCheckTime = true;
                                Log.e("Test",dataStoreSubject.loadData().get(i).getTenmon());
                                WidgetManager.getInstance().updateCalendar(context, dataStoreSubject.loadData().get(i).getGiangvien(),
                                        dataStoreSubject.loadData().get(i).getTenmon(),
                                        "Thời gian: " + dataStoreSubject.loadData().get(i).getStartDate() + " -- " + dataStoreSubject.loadData().get(i).getEndDate()
                                        , "Tên giảng viên: ");
                                break;
                            }
                        }
                    }
                }
                if (!isCheckTime) {
                    try {
                        if (dateFormat.parse(dayCurrent).after(dateFormat.parse(day))) {
                            long differenceInMilliseconds = dateFormat.parse(dayCurrent).getTime() - dateFormat.parse(day).getTime();
                            long differenceInDays = differenceInMilliseconds / (24 * 60 * 60 * 1000);
                            if (differenceInDays ==1) {
                                WidgetManager.getInstance().updateCalendar(context, dataStoreSubject.loadData().get(i).getGiangvien(), dataStoreSubject.loadData().get(i).getTenmon(), dayCurrent, "Giảng viên: ");
                                isCheckDay = true;
                            } else {
                            }
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }

    @Override
    public void onDayChanged(String day) {
        updateSchedule(day);
        updateEvent(day);
    }
}
