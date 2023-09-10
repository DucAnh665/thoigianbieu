package com.example.lich.Thoikhoabieu;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lich.Model.TKB;
import com.example.lich.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterThoikhoabieu extends BaseAdapter {

    int Layout;
    Context context;
    List<TKB> dulieu;
    String ngay, thang, nam;


    public AdapterThoikhoabieu(int layout, Context context, List<TKB> dulieu, String ngay, String thang, String nam) {
        Layout = layout;
        this.context = context;
        this.dulieu = dulieu;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    @Override
    public int getCount() {
        return dulieu.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class viewhodel {
        TextView tenmon, thoigian, sotinchi, index, nameTeacher;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewhodel hodel;

        if (view == null) {
            hodel = new viewhodel();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(Layout, null);
            hodel.tenmon = (TextView) view.findViewById(R.id.txttenmon);
            hodel.thoigian = (TextView) view.findViewById(R.id.txtthoigian);
            hodel.sotinchi = (TextView) view.findViewById(R.id.txt_number);
            hodel.index = (TextView) view.findViewById(R.id.txtindex);
            hodel.nameTeacher = (TextView) view.findViewById(R.id.txt_nameTeacher);
            view.setTag(hodel);
        } else {
            hodel = (viewhodel) view.getTag();
        }
        TKB list = dulieu.get(i);
        if (list.getNgay().equals(ngay) && list.getThang().equals(thang) && list.getNam().equals(nam)) {
            hodel.tenmon.setText(list.getTenmon());
            hodel.index.setText("Tiết: " + i + 1 + "");
            hodel.thoigian.setText(list.getNgay() + "/" + list.getThang() + "/" + list.getNam());
            hodel.sotinchi.setText("Số tín: " + list.getId());
            hodel.nameTeacher.setText("Giảng viên: " + list.getGiangvien());
        } else {
            hodel.tenmon.setVisibility(View.INVISIBLE);
            hodel.thoigian.setVisibility(View.INVISIBLE);
            hodel.index.setVisibility(View.INVISIBLE);
            hodel.sotinchi.setVisibility(View.INVISIBLE);
            hodel.nameTeacher.setVisibility(View.INVISIBLE);
        }


        return view;
    }
}
