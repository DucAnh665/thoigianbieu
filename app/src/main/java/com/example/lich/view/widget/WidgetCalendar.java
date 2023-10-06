package com.example.lich.view.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Handler;
import android.widget.RemoteViews;

import com.example.lich.R;
import com.example.lich.shared.DataStoreSubject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WidgetCalendar extends AppWidgetProvider {
    private Handler handler = new Handler();
    private Runnable updateRunnable;
    private DataStoreSubject dataStoreSubject;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_calendar);
        views.setTextViewText(R.id.txt_time, new SimpleDateFormat("HH:mm").format(new Date()));
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        dataStoreSubject = new DataStoreSubject(context);
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
//        updateRunnable = new Runnable() {
//            @Override
//            public void run() {
//                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_calendar);
//                views.setTextViewText(R.id.txt_time, new SimpleDateFormat("HH:mm").format(new Date()));
//                views.setTextViewText(R.id.txtTimeCalendarBefore, "Tiết: " + dataStoreSubject.loadData().get(0).getId() + "");
//                views.setTextViewText(R.id.txtContentCalendarBefore, dataStoreSubject.loadData().get(0).getTenmon());
//                appWidgetManager.updateAppWidget(appWidgetIds, views);
//                handler.postDelayed(this, 1000);
//            }
//        };
//
//        handler.post(updateRunnable);
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
        // Remove the update runnable when the last widget is disabled

    }
}