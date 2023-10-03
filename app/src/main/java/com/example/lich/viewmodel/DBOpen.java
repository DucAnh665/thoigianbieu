package com.example.lich.viewmodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lich.shared.DataEventStore;


public class DBOpen extends SQLiteOpenHelper {


    private static final String CREATE_EVENTS_TABLE = "create table "+ DataEventStore.EVENT_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DataEventStore.EVENT+" TEXT, " + DataEventStore.TIME+" TEXT," + DataEventStore.DATE+" TEXT, "+ DataEventStore.MONTH+" TEXT, "+ DataEventStore.YEAR+" TEXT)";

    private static final String DROP_EVENTS_TABLE = "DROP TABLE IF EXISTS "+ DataEventStore.EVENT_TABLE_NAME;

    public DBOpen(@Nullable Context context) {
        super(context, DataEventStore.DB_NAME, null, DataEventStore.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_EVENTS_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void SaveEvent(String event, String time, String date, String month, String year, SQLiteDatabase database)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataEventStore.EVENT,event);
        contentValues.put(DataEventStore.TIME,time);
        contentValues.put(DataEventStore.DATE,date);
        contentValues.put(DataEventStore.MONTH,month);
        contentValues.put(DataEventStore.YEAR,year);
        database.insert(DataEventStore.EVENT_TABLE_NAME,null,contentValues);

    }
    public Cursor ReadEvents(String ngay,String thang, SQLiteDatabase database)
    {
        String [] Projections = {DataEventStore.EVENT, DataEventStore.TIME, DataEventStore.DATE, DataEventStore.MONTH, DataEventStore.YEAR};
        String Selection = DataEventStore.DATE+"=? and "+ DataEventStore.MONTH+"=?";

        String [] SelectionsArgs = {ngay,thang};

        return database.query(DataEventStore.EVENT_TABLE_NAME,Projections,Selection,SelectionsArgs,null,null,null);

    }
    public Cursor ReadEventsperMonth(String month,String year, SQLiteDatabase database)
    {
        String [] Projections = {DataEventStore.EVENT, DataEventStore.TIME, DataEventStore.DATE, DataEventStore.MONTH, DataEventStore.YEAR};
        String Selection = DataEventStore.MONTH+"=? and "+ DataEventStore.YEAR+"=?";
        String [] SelectionsArgs = {month,year};
        return database.query(DataEventStore.EVENT_TABLE_NAME,Projections,Selection,SelectionsArgs,null,null,null);

    }
    public void deleteEvent(String event,String date,String time,SQLiteDatabase database)
    {
        String selection = DataEventStore.EVENT+"=? and "+ DataEventStore.DATE +"=? and "+ DataEventStore.TIME+"=?";
        String[] selectionArg = {event,date,time};
        database.delete(DataEventStore.EVENT_TABLE_NAME,selection,selectionArg);

    }

}
