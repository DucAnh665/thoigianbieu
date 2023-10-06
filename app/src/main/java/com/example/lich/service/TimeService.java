package com.example.lich.service;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.example.lich.Model.CurrentInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeService {
    private final String TAG = "TimerService";
    private final Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    private final SimpleDateFormat dayOfMonthFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
    private final SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.ENGLISH);
    private final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    private final SimpleDateFormat dayFormat = new SimpleDateFormat("d/MM/yyyy", Locale.ENGLISH);
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    private final SimpleDateFormat timeFullFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    private final SimpleDateFormat minuteFormat = new SimpleDateFormat("mm", Locale.ENGLISH);
    private final SimpleDateFormat secondFormat = new SimpleDateFormat("ss", Locale.ENGLISH);
    private final SimpleDateFormat dayOfWeekFormat = new SimpleDateFormat("EEE", Locale.ENGLISH);

    /**
     * Khởi tạo
     */
    private final Context context;
    private final CurrentInfo currentInfo;
    private final TimerListener listener;

    public TimeService(Context context, TimerListener listener) {
        this.context = context;
        this.listener = listener;
        currentInfo = new CurrentInfo();
        sendCallbackEventCalendar();
        startTimer();
    }

    /**
     * Gửi callback nhận data từ app calendar
     */
    public void sendCallbackEventCalendar() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent("vn.icar.DATA_CALENDAR");
            context.sendBroadcast(intent);
        }, 1000);

    }

    /**
     * Tạo luồng cập nhật thời gian cho màn hình bảo vệ
     */
    private void startTimer() {
        new Thread(() -> {
            while (true) {
                Date date = new Date(System.currentTimeMillis());
                dayOfWeekFormat.format(calendar.getTime());
                currentInfo.setTime(timeFormat.format(date));
                calendar.setTimeInMillis(System.currentTimeMillis());
                if (listener != null) {
                    listener.onTimeChanged(timeFormat.format(calendar.getTime()));
                    listener.onDayChanged(dayFormat.format(calendar.getTime()));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface TimerListener {
        void onTimeChanged(String time);

        void onDayChanged(String day);
    }
}
