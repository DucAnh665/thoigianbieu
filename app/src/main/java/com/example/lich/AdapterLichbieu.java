package com.example.lich;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull viewhodel holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewhodel extends RecyclerView.ViewHolder {
        public viewhodel(@NonNull View itemView) {
            super(itemView);
        }
    }
}
