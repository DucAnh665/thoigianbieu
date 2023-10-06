package com.example.lich.view.widget;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.RemoteViews;

import com.example.lich.R;
import com.example.lich.shared.DataStoreSubject;
import com.example.lich.shared.DataUserStorage;
import com.tatv.baseapp.utils.log.LogUtils;
import com.tatv.baseapp.utils.tpms.TireUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WidgetManager {

    private static final String TAG = "WidgetManager";

    /**
     * Singleton
     */
    @SuppressLint("StaticFieldLeak")
    private static WidgetManager instance;

    private AppWidgetManager appWidgetManager;

    private DataStoreSubject dataStoreSubject;

    private RemoteViews views;
    private RemoteViews viewsEvent;


    public static WidgetManager getInstance() {
        if (instance == null) {
            instance = new WidgetManager();
        }
        return instance;
    }

    public void init(Context context) {
        appWidgetManager = AppWidgetManager.getInstance(context);
        dataStoreSubject = new DataStoreSubject(context);
        views = new RemoteViews(context.getPackageName(), R.layout.widget_calendar);
        viewsEvent = new RemoteViews(context.getPackageName(), R.layout.widget_calendar_event);
    }


    public void updateCalendarEvent(Context context, String nameEvent, String date) {
        for (int widgetId : appWidgetManager.getAppWidgetIds(new ComponentName(context, WidgetCalendarEvent.class))) {
            viewsEvent.setTextViewText(R.id.txtTimeCalendarBefore, "Tên sự kiện: " + nameEvent);
            viewsEvent.setTextViewText(R.id.txtContentCalendarBefore, "------------");
            viewsEvent.setTextViewText(R.id.txtDate, date);
            appWidgetManager.updateAppWidget(widgetId, viewsEvent);
        }
    }

    public void updateCalendar(Context context, String nameTeacher, String nameSchedule, String date, String lable) {
        if (dataStoreSubject.loadData() != null && dataStoreSubject.loadData().size() > 0) {
            for (int widgetId : appWidgetManager.getAppWidgetIds(new ComponentName(context, WidgetCalendar.class))) {
                views.setTextViewText(R.id.txtTimeCalendarBefore, lable.equals("") ? "Tên giảng viên: " : lable + nameTeacher + "");
                views.setTextViewText(R.id.txtContentCalendarBefore, nameSchedule);
                views.setTextViewText(R.id.txtDate, date);
                appWidgetManager.updateAppWidget(widgetId, views);
            }
        }
    }

    public void updateTime(Context context, String time) {
        for (int widgetId : appWidgetManager.getAppWidgetIds(new ComponentName(context, WidgetCalendar.class))) {
            views.setTextViewText(R.id.txt_time, time);
            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }


    public void updateTimeEvent(Context context, String time) {
        for (int widgetId : appWidgetManager.getAppWidgetIds(new ComponentName(context, WidgetCalendarEvent.class))) {
            viewsEvent.setTextViewText(R.id.txt_time, time);
            appWidgetManager.updateAppWidget(widgetId, viewsEvent);
        }
    }


}
