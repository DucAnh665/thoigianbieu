package com.example.lich.Thoikhoabieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lich.Model.TKB;
import com.example.lich.R;

import java.util.ArrayList;

public class AdapterLichbieu extends RecyclerView.Adapter<AdapterLichbieu.viewhodel> {
    Context context;
    ArrayList<TKB> dulieu;
    String ngay,thang,nam;

    public AdapterLichbieu(Context context, ArrayList<TKB> dulieu, String ngay, String thang, String nam) {
        this.context = context;
        this.dulieu = dulieu;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    @NonNull
    @Override
    public viewhodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ctlich,parent,false);
        return new viewhodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewhodel holder, int position) {
        if (dulieu.get(position).getNgay().equals(ngay)&&dulieu.get(position).getThang().equals(thang)&&dulieu.get(position).getNam().equals(nam))
        {
            holder.date.setText(dulieu.get(position).getNgay());
            holder.somon.setText(dulieu.get(position).getTenmon());

        }

    }

    @Override
    public int getItemCount() {
        return dulieu.size();
    }


    public class viewhodel extends RecyclerView.ViewHolder {
        TextView date,somon;
        public viewhodel(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.day);
            somon = itemView.findViewById(R.id.number);

        }
    }
}
