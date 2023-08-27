package com.example.lich.shared;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lich.Model.UserResponse;

public class DataUserStorage {
    private static final String PREFERENCE_NAME = "MyPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_CODE_STUDENT = "codeStudent";
    private static final String KEY_NAME_CLASS = "nameClass";
    private static final String KEY_NAME_FACULTY = "nameFaulty";

    private static final String KEY_PASSWORD = "passWord";

    private static final String KEY_IMAGE = "image";

    private String passWord;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public DataUserStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveData(String userName, String codeStudent, String nameClass, String nameFaulty, String image, String passWord) {
        editor.putString(KEY_USERNAME, userName);
        editor.putString(KEY_CODE_STUDENT, codeStudent);
        editor.putString(KEY_NAME_CLASS, nameClass);
        editor.putString(KEY_NAME_FACULTY, nameFaulty);
        editor.putString(KEY_IMAGE, image);
        editor.putString(KEY_PASSWORD, passWord);
        editor.apply();
    }


    public UserResponse.User loadData() {
        String userName = sharedPreferences.getString(KEY_USERNAME, "");
        String codeStudent = sharedPreferences.getString(KEY_CODE_STUDENT, "");
        String nameClass = sharedPreferences.getString(KEY_NAME_CLASS, "");
        String nameFaculty = sharedPreferences.getString(KEY_NAME_FACULTY, "");
        String image = sharedPreferences.getString(KEY_IMAGE, "");
        String passWord = sharedPreferences.getString(KEY_PASSWORD, "");
        return new UserResponse.User(userName, codeStudent, nameClass, nameFaculty, image, passWord);
    }

    public void clearData() {
        editor.clear();
        editor.apply();
    }
}
