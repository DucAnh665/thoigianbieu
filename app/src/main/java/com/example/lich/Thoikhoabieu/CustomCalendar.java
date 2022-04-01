package com.example.lich.Thoikhoabieu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lich.Model.Events;
import com.example.lich.Model.TKB;
import com.example.lich.R;
import com.example.lich.view.listviewtkb;
import com.example.lich.viewmodel.getdulieulich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CustomCalendar extends LinearLayout {

    GridView grid;
    public  static final  int MAX_CALENDAR_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;
    TextView ngay;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM yyyy", Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM",Locale.ENGLISH);
    SimpleDateFormat YearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    MyGridAdapter da = null;
    String curdate = " ",curyear = " ";
    String curmont = " ";
    getdulieulich lichDAO = null;
    String url = "https://csdlapp.000webhostapp.com/getteam.php";
   static public ArrayList<TKB> dulieu = new ArrayList<>();
    ArrayList<Date> dates = new ArrayList<>();
    ImageButton tien,lui;


    Calendar daycale = Calendar.getInstance();
    int dayno;

    public CustomCalendar(Context context) {
        super(context);
    }


    public CustomCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void khoitao()
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.lich,this);
        grid = view.findViewById(R.id.grid);
        ngay = view.findViewById(R.id.ngaythangnam);
        tien = view.findViewById(R.id.tien);
        lui = view.findViewById(R.id.lui);


        lui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH,-1);
                caidatlich();
            }
        });

        tien.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH,+1);
                caidatlich();
            }
        });
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                setupngay(i);
                ngay.setText(String.valueOf(dayno)+"/"+curmont+"/"+curyear);

            }
        });


        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               setupngay(i);
                Intent intent = new Intent(context,listviewtkb.class);
                Bundle bd = new Bundle();
                bd.putString("t1",String.valueOf(dayno));
                bd.putString("t2",curmont);
                bd.putString("t3",curyear);
                intent.putExtras(bd);
                context.startActivity(intent);

               return  true;
            }
        });
    }




    public  void setupngay(int i)
    {
        Date day = dates.get(i);
        daycale.setTime(day);
         dayno = daycale.get(Calendar.DAY_OF_MONTH);

    }

    private void caidatlich()
    {

        curdate = dateFormat.format(calendar.getTime());
        curyear = YearFormat.format(calendar.getTime());
        curmont = monthFormat.format(calendar.getTime());
        ngay.setText(curmont+"/"+curyear);
        dates.clear();
        Calendar monthca = (Calendar) calendar.clone();
        monthca.set(Calendar.DAY_OF_MONTH,1);
        int firstdayofmonth = monthca.get(Calendar.DAY_OF_WEEK)-1;
        monthca.add(Calendar.DAY_OF_MONTH,-firstdayofmonth);
        while (dates.size()<MAX_CALENDAR_DAYS)
        {
            dates.add(monthca.getTime());
            monthca.add(Calendar.DAY_OF_MONTH,1);

        }

        da = new MyGridAdapter(context,dates,calendar,dulieu);
         lichDAO.getdata(url,dulieu,da,context);
        grid.setAdapter(da);


    }
    public CustomCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        lichDAO = new getdulieulich();
        khoitao();
        caidatlich();

    }
}
