package com.example.lich;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    List<Date> dates = new ArrayList<>();
    ArrayList<TKB> dulieu = new ArrayList<>();


    AdapterThoikhoabieu datkb;


    Button tien,lui;
    AlertDialog alertDialog;

    public CustomCalendar(Context context) {
        super(context);
    }

    public CustomCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        dulieu.add(new TKB(1,"Lập trình","12","02","2022","cntt"));
        dulieu.add(new TKB(1,"Thuật toán","13","03","2022","cntt"));
        khoitao();
        caidatlich();
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
                Date day = dates.get(i);
                Calendar daycale = Calendar.getInstance();
                daycale.setTime(day);
                int dayno = daycale.get(Calendar.DAY_OF_MONTH);
                String dayone = String.valueOf(dayno);
                ngay.setText(dayone+"/"+curmont+"/"+curyear);

            }
        });


        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                view = LayoutInflater.from(adapterView.getContext()).inflate(R.layout.activity_listivewtkb,null);
                ListView lvtkb = view.findViewById(R.id.lvtkb);
                datkb = new AdapterThoikhoabieu(R.layout.cttkb,context,dulieu);
                lvtkb.setAdapter(datkb);
                builder.setView(view);
                alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
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
        grid.setAdapter(da);


    }
}
