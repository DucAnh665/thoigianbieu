package com.example.lich.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lich.Model.Sinhvien;
import com.example.lich.view.home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dangnhap {
    ArrayList<Sinhvien> dulieu = new ArrayList<>();
    public void dangnhap(String taikhoan, String matkhau, String url, Context context)
    {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                String Masv = " ";
                String Tensv = " ";
                String Matkhau = " ";
                String khoa = " ";
                if (response !=null)
                {
                    try
                    {
                        dulieu.clear();
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            id = object.getInt("id");
                            Tensv = object.getString("Tensv");
                            Masv = object.getString("Masv");
                            Matkhau = object.getString("Matkhau");
                            khoa = object.getString("Khoa");
                            dulieu.add(new Sinhvien(id,Tensv,Masv,Matkhau,khoa));
                            Toast.makeText(context,Tensv,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(context, home.class);
                            intent.putExtra("thongtin",dulieu.get(i));
                            context.startActivity(intent);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (response.equals("thatbai"))
                {
                    Toast.makeText(context,"Đăng nhập thất bại",Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("Masv",taikhoan);
                param.put("Matkhau",matkhau);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}
