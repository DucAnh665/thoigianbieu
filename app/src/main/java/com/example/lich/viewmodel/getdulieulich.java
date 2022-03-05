package com.example.lich.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lich.Model.TKB;
import com.example.lich.Thoikhoabieu.AdapterThoikhoabieu;
import com.example.lich.Thoikhoabieu.MyGridAdapter;
import com.example.lich.even.MyGridDatLich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class getdulieulich  {
    public void getdata(String url, ArrayList<TKB>dulieu,MyGridAdapter da, Context context)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dulieu.clear();
                for (int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        dulieu.add(new TKB(object.getInt("id"),
                                object.getString("tenmon"),
                                object.getString("ngay"),
                                object.getString("thang"),
                                object.getString("nam"),
                                object.getString("khoa")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                da.notifyDataSetChanged();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

//    public void getdatalv(String url, ArrayList<TKB>dulieu,String ngay,String thang,String nam,AdapterThoikhoabieu da, Context context)
//    {
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response!=null)
//                {
//                    try{
//                        JSONArray jsonArray = new JSONArray(response);
//                        for (int i=0;i<response.length();i++)
//                        {
//                            JSONObject object = jsonArray.getJSONObject(i);
//                            dulieu.add(new TKB(object.getInt("id"),
//                                    object.getString("tenmon"),
//                                    object.getString("ngay"),
//                                    object.getString("thang"),
//                                    object.getString("nam"),
//                                    object.getString("khoa")
//                            ));
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
//            }
//        }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> param = new HashMap<>();
//                param.put("Ngay",ngay);
//                param.put("Thang",thang);
//                param.put("Nam",nam);
//                return param;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }


    public void getdatalv(String url, ArrayList<TKB>dulieu,AdapterThoikhoabieu da, Context context)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dulieu.clear();
                for (int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        dulieu.add(new TKB(object.getInt("id"),
                                object.getString("tenmon"),
                                object.getString("ngay"),
                                object.getString("thang"),
                                object.getString("nam"),
                                object.getString("khoa")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                da.notifyDataSetChanged();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(jsonArrayRequest);
    }



}
