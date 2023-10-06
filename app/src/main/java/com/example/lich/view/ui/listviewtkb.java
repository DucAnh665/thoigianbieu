package com.example.lich.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.lich.R;
import com.example.lich.adapter.AdapterSchedule;
import com.example.lich.databinding.ActivityDetailScheduleBinding;
import com.example.lich.view.samonline.CustomCalendarSchedule;
import com.example.lich.shared.DataStoreSubject;

public class listviewtkb extends AppCompatActivity {

    ActivityDetailScheduleBinding binding;

    AdapterSchedule da;
    String ngay = " ", thang = " ", nam = " ";

    DataStoreSubject dataStoreSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataStoreSubject = new DataStoreSubject(this);
        nhandulieu();
        setuptkb();
        binding.trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.lvtkb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });


    }

    public void nhandulieu() {
        Bundle bundle = getIntent().getExtras();
        ngay = bundle.getString("t1");
        thang = bundle.getString("t2");
        nam = bundle.getString("t3");
        binding.txtngay.setText(ngay + "/" + thang + "/" + nam);
    }

    public void setuptkb() {
        if (dataStoreSubject.loadData() != null && dataStoreSubject.loadData().size() > 0) {
            da = new AdapterSchedule(R.layout.item_detail_schedule, listviewtkb.this, dataStoreSubject.loadData(), ngay, thang, nam);
            for (int i = 0; i < dataStoreSubject.loadData().size(); i++)
                if (dataStoreSubject.loadData().get(i).getNgay().equals(ngay) && dataStoreSubject.loadData().get(i).getThang().equals(thang) && dataStoreSubject.loadData().get(i).getNam().equals(nam)) {

                } else {
                    dataStoreSubject.loadData().remove(i);
                    da.notifyDataSetChanged();
                }
            binding.lvtkb.setAdapter(da);
        }
    }

}