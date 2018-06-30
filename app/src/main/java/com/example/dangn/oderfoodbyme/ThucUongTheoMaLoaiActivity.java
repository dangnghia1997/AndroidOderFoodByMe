package com.example.dangn.oderfoodbyme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThucUongTheoMaLoaiActivity extends AppCompatActivity {

    GridView gvThucUongTheoMaLoai;
    ArrayList<ThucUongTheoMaLoai> arrayThucUongTheoMa;
    ThucUongTheoMaAdapter adapter;
    TextView txtMaban;
    String urlDSthucuongtheoma="http://192.168.1.29:81/android_oderfood/Doc_thuc_uong_theo_ma_loai.php";

    String Ma_BAN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_uong_theo_ma_loai);

        AnhXa();
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("dulieucualoai");
        int ma_loai=bundle.getInt("ma_loai");
        Ma_BAN=bundle.getString("ma_ban");
        txtMaban.setText(Ma_BAN);




        GetDsThucUongTheoMaLoai(urlDSthucuongtheoma,ma_loai);

        adapter = new ThucUongTheoMaAdapter(this, R.layout.dong_thuc_uong, arrayThucUongTheoMa);

        gvThucUongTheoMaLoai.setAdapter(adapter);



    }

    private void AnhXa() {
       gvThucUongTheoMaLoai = (GridView) findViewById(R.id.gridviewThucUong);
        arrayThucUongTheoMa=new ArrayList<>();
         txtMaban=(TextView) findViewById(R.id.textViewthehienBan);


    }



    private void GetDsThucUongTheoMaLoai(String url, final int ma_loai)
    {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(ThucUongTheoMaLoaiActivity.this, ""+response.length(), Toast.LENGTH_SHORT).show();
                        try {
                            JSONArray jsonArray=new JSONArray(response);

                            for (int i=0;i<jsonArray.length();i++)
                            {

                                JSONObject object= (JSONObject) jsonArray.get(i);
                                String mDrawableName =object.getString("hinh");
                                int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
                                arrayThucUongTheoMa.add(new ThucUongTheoMaLoai(object.getInt("ma_thuc_uong"),
                                        object.getString("ten_thuc_uong"),
                                        object.getDouble("gia"),
                                        1,
                                        resID
                                        ));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ThucUongTheoMaLoaiActivity.this, "Lỗi kết nối server", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parrams=new HashMap<>();
                parrams.put("ma_loai",String.valueOf(ma_loai));
                return parrams;
            }
        };
        requestQueue.add(stringRequest);

    }
}
