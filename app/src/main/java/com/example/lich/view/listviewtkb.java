package com.example.lich.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lich.Model.TKB;
import com.example.lich.R;
import com.example.lich.Thoikhoabieu.AdapterThoikhoabieu;
import com.example.lich.Thoikhoabieu.CustomCalendar;
import com.example.lich.viewmodel.getdulieulich;

import java.util.ArrayList;

public class listviewtkb extends AppCompatActivity {

    ListView lvtkb;
    TextView date,thongbao;
    AdapterThoikhoabieu da;
    String ngay = " ",thang = " ",nam = " ";
    Button trove;
    //String url = "https://csdlapp.000webhostapp.com/getteam.php";
//    getdulieulich LICHDAO;
//    ArrayList<TKB> dulieu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewtkb);
        lvtkb = findViewById(R.id.lvtkb);
        date = findViewById(R.id.txtngay);
        trove = findViewById(R.id.trove);
        thongbao = findViewById(R.id.textthongbao);

        nhandulieu();
        setuptkb();
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(listviewtkb.this,MainActivity.class));
            }
        });



    }
    public void nhandulieu()
    {
        Bundle bundle = getIntent().getExtras();
        ngay = bundle.getString("t1");
        thang = bundle.getString("t2");
        nam = bundle.getString("t3");
        date.setText(ngay + "/"+thang+"/"+nam);
    }
    public  void setuptkb()
    {
//        LICHDAO = new getdulieulich();
//        dulieu= new ArrayList<>();
        da = new AdapterThoikhoabieu(R.layout.cttkb,listviewtkb.this,CustomCalendar.dulieu,ngay,thang,nam);
//        dulieu.add(new TKB(1,"Lập trình","16","03","2022","K17"));
//        dulieu.add(new TKB(1,"Lập trình","17","03","2022","K17"));
//        dulieu.add(new TKB(1,"Lập trình Dotnet","17","03","2022","K17"));
//        dulieu.add(new TKB(1,"Lập trình Dotnet","20","03","2022","K17"));

       //  LICHDAO.getdatalv(url,dulieu,da,listviewtkb.this);

        for (int i = 0;i<CustomCalendar.dulieu.size();i++)
            if (CustomCalendar.dulieu.get(i).getNgay().equals(ngay)==true&&CustomCalendar.dulieu.get(i).getThang().equals(thang)==true&&CustomCalendar.dulieu.get(i).getNam().equals(nam)==true)
            {
                Toast.makeText(listviewtkb.this,CustomCalendar.dulieu.get(i).getTenmon(),Toast.LENGTH_LONG).show();

            }
       else {
                CustomCalendar.dulieu.remove(i);
                da.notifyDataSetChanged();
            }

        lvtkb.setAdapter(da);

    }
}