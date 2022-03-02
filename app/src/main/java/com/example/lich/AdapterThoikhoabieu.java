package com.example.lich;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterThoikhoabieu extends BaseAdapter {

    int Layout;
    Context context;
    ArrayList<TKB> dulieu;

    public AdapterThoikhoabieu(int layout, Context context, ArrayList<TKB> dulieu) {
        Layout = layout;
        this.context = context;
        this.dulieu = dulieu;
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
    class viewhodel
    {
        TextView tenmon,thoigian;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewhodel hodel;
        if (view == null)
        {
            hodel = new viewhodel();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(Layout,null);
            hodel.tenmon = (TextView) view.findViewById(R.id.txttenmon);
            hodel.thoigian = (TextView) view.findViewById(R.id.txtthoigian);
            view.setTag(hodel);

        }
        else
        {
            hodel = (viewhodel) view.getTag();
        }
        TKB list = dulieu.get(i);
        hodel.tenmon.setText(list.getTenmon());
        hodel.thoigian.setText(list.getNgay()+"/"+list.getThang()+"/"+list.getNam());
        return view;
    }
}
