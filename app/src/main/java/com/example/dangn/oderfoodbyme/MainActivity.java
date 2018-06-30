package com.example.dangn.oderfoodbyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edtTenDangNhap,edtPassWord;
    Button btnDangNhap,btnDangKy;



    String UrlUser="http://192.168.1.29:81/android_oderfood/check_dang_nhap.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AnhXa();


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ten_dang_nhap= edtTenDangNhap.getText().toString().trim();
                String pass= edtPassWord.getText().toString().trim();
                if(ten_dang_nhap.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();

                }
                {
                    CheckTaiKhoan(UrlUser,ten_dang_nhap,pass);
                }

            }
        });//end Click btnDangNhap

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DangKyActivity.class));
            }
        });//end click btnDangKy





    }

    private void AnhXa() {
        edtTenDangNhap = (EditText) findViewById(R.id.editTextTenDangNhap);
        edtPassWord = (EditText) findViewById(R.id.editTextPassWord);
        btnDangNhap = (Button) findViewById(R.id.buttonDangNhap);
        btnDangKy = (Button) findViewById(R.id.buttonDangKy);

        //click

    }

    private  void CheckTaiKhoan(String url, final String tendn, final String passw) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            //Toast.makeText(MainActivity.this, "Đăng Nhập thành công!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,HanhChinhActivity.class));
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parrams = new HashMap<>();
                parrams.put("ten_dang_nhap",tendn);
                parrams.put("password",passw);

                return parrams;
            }
        };
        requestQueue.add(stringRequest);
    }

}
