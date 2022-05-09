package com.example.lich.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lich.Model.TKB;
import com.example.lich.R;
import com.example.lich.Thoikhoabieu.AdapterThoikhoabieu;
import com.example.lich.Thoikhoabieu.CustomCalendar;
import com.example.lich.databinding.ActivityListviewtkbBinding;
import com.example.lich.viewmodel.getdulieulich;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class listviewtkb extends AppCompatActivity {

    ActivityListviewtkbBinding binding;

    AdapterThoikhoabieu da;
    String ngay = " ",thang = " ",nam = " ";

    getdulieulich dto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListviewtkbBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        nhandulieu();
        setuptkb();
       // dto.getdatalv(url,CustomCalendar.dulieu,listviewtkb.this);
        binding.trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(listviewtkb.this,MainActivity.class));
            }
        });
        binding.lvtkb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(listviewtkb.this,ngay,Toast.LENGTH_LONG).show();
            }
        });




    }
    public void nhandulieu()
    {
        Bundle bundle = getIntent().getExtras();
        ngay = bundle.getString("t1");
        thang = bundle.getString("t2");
        nam = bundle.getString("t3");
        binding.txtngay.setText(ngay + "/"+thang+"/"+nam);
    }

    public  void setuptkb()
    {

       da = new AdapterThoikhoabieu(R.layout.cttkb,listviewtkb.this,CustomCalendar.dulieu,ngay,thang,nam);
       for (int i=0;i<CustomCalendar.dulieu.size();i++)
       if (CustomCalendar.dulieu.get(i).getNgay().equals(ngay)&&CustomCalendar.dulieu.get(i).getThang().equals(thang)&&CustomCalendar.dulieu.get(i).getNam().equals(nam))
       {

       }
       else
       {
           CustomCalendar.dulieu.remove(i);
           da.notifyDataSetChanged();
       }
        binding.lvtkb.setAdapter(da);






    }

}