package com.example.lich.shared;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lich.Model.TKB;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataStoreSubject {

    private static final String PREFERENCE_NAME = "MyPrefs";
    private static final String KEY_DATA_APP = "dataApp";


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public DataStoreSubject(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveData(List<TKB> dataList) {
        Gson gson = new Gson();
        String json = gson.toJson(dataList);
        editor.putString(KEY_DATA_APP, json);
        editor.apply();
    }

    public void addData(TKB newData) {
        List<TKB> dataList = loadData();
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        dataList.add(newData);
        saveData(dataList);
    }

    public List<TKB> loadData() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_DATA_APP, null);
        Type type = new TypeToken<List<TKB>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public void clearData() {
        editor.remove(KEY_DATA_APP);
        editor.apply();
    }

}
