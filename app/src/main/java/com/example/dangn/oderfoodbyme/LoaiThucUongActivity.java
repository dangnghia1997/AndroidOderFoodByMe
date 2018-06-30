package com.example.dangn.oderfoodbyme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoaiThucUongActivity extends AppCompatActivity {

    ListView LvLoaiThucUong;
    ArrayList<LoaiThucUong> arrayLoaiThucUong;
    LoaiThucUongAdapter adapter;
    TextView txtTHBan;
    TextView txtchuamaban;
    String ma_ban;
    String urlDocLoaiThucUong="http://192.168.1.29:81//android_oderfood/Doc_loai_thuc_uong.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_thuc_uong);


        AnhXa();

        //Nhận dữ liệu từ BanAdapter
        Intent intent= getIntent();
        Bundle bundle = intent.getBundleExtra("dulieucuaban");
         ma_ban=bundle.getString("ma_ban");
        String TenBan=bundle.getString("ten_ban");

        txtTHBan.setText(TenBan);
        txtchuamaban.setText(ma_ban);



        GetDSLoaiThucUong(urlDocLoaiThucUong);

        adapter=new LoaiThucUongAdapter(LoaiThucUongActivity.this,R.layout.dong_loai_thuc_uong,arrayLoaiThucUong);
        LvLoaiThucUong.setAdapter(adapter);
    }

    private void AnhXa() {
        LvLoaiThucUong=(ListView) findViewById(R.id.ListViewLoaiThucUong);
        txtTHBan =(TextView) findViewById(R.id.textViewTheHienBan);
        txtchuamaban=(TextView) findViewById(R.id.textviewchuamaban) ;
        arrayLoaiThucUong=new ArrayList<>();




    }




    private void GetDSLoaiThucUong(String url)
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject object=response.getJSONObject(i);
                                String mDrawableName = object.getString("hinh");
                                int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());

                                arrayLoaiThucUong.add(new LoaiThucUong(object.getInt("ma_loai"),object.getString("ten_loai"),resID));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoaiThucUongActivity.this, "Lỗi kết nối đến Server", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

}

