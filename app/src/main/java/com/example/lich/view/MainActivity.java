package com.example.lich.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lich.Model.SubjectReponse;
import com.example.lich.Model.TKB;
import com.example.lich.Thoikhoabieu.CustomCalendar;
import com.example.lich.R;
import com.example.lich.databinding.ActivityForgotPassWordBinding;
import com.example.lich.databinding.ActivityMainBinding;
import com.example.lich.shared.DataStoreSubject;
import com.example.lich.shared.DataUserStorage;
import com.example.lich.viewmodel.SubjectViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CustomCalendar ct;
    private ActivityMainBinding binding;

    private SubjectViewModel subjectViewModel;
    private DataStoreSubject dataStoreSubject;

    private DataUserStorage dataUserStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ct = (CustomCalendar) findViewById(R.id.custom);
        initData();
    }


    private void initData() {
        dataUserStorage = new DataUserStorage(this);
        dataStoreSubject = new DataStoreSubject(this);
        subjectViewModel = new ViewModelProvider(this).get(SubjectViewModel.class);
        if (dataStoreSubject.loadData() != null && dataStoreSubject.loadData().size() > 0) {
            dataStoreSubject.clearData();
        }
        subjectViewModel.getIsCheckGetSubject().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(MainActivity.this, "Hiển thị dữ liệu thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        subjectViewModel.getSubject(this, dataUserStorage.loadData().getCodeStudent());
        subjectViewModel.getLiveData().observe(this, new Observer<SubjectReponse>() {
            @Override
            public void onChanged(SubjectReponse subjectReponse) {
                for (int i = 0; i < subjectReponse.getData().size(); i++) {
                    Log.e("MainActivity", subjectReponse.getData().get(i).getNameSubject());
                    Log.e("MainActivity", subjectReponse.getData().get(i).getDateTime());
                    String[] dateParts = extractDate(subjectReponse.getData().get(i).getDateTime());

                    if (dateParts != null) {
                        String day = dateParts[0];
                        String month = dateParts[1];
                        String year = dateParts[2];
                        dataStoreSubject.addData(new TKB(subjectReponse.getData().get(i).getNumber(), subjectReponse.getData().get(i).getNameSubject(), day, month, year, dataUserStorage.loadData().nameFaculty, subjectReponse.getData().get(i).getNameTeacher()));
                        Log.e("tag", dataStoreSubject.loadData().size() + "");
                    } else {
                        System.out.println("Định dạng ngày không hợp lệ.");
                    }
                }
            }
        });
    }

    public static String[] extractDate(String text) {
        // Chia chuỗi bằng dấu "/"
        String[] dateParts = text.split("/");

        // Kiểm tra xem có đủ 3 phần tử không
        if (dateParts.length == 3) {
            return dateParts;
        } else {
            return null; // Định dạng ngày không hợp lệ
        }
    }
}