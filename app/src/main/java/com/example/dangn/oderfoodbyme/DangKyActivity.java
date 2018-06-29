package com.example.dangn.oderfoodbyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {

    EditText edtHoTen,edtTenDangNhap,edtPass;
    Button btnDangKy;

    String urlThemTaiKhoan="http://192.168.200.137:81/android_oderfood/them_user.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        //Ánh Xạ
        AnhXa();

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ho_ten=edtHoTen.getText().toString().trim();
                String ten_Dang_nhap=edtTenDangNhap.getText().toString().trim();
                String passw=edtPass.getText().toString().trim();

                //Toast.makeText(DangKyActivity.this, ""+ho_ten+ "" +ten_Dang_nhap+ "" + passw, Toast.LENGTH_SHORT).show();
                if(ho_ten.isEmpty() || ten_Dang_nhap.isEmpty() || passw.isEmpty())
                {
                    Toast.makeText(DangKyActivity.this, "Vui lòng điền đầy đủ thông tin!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    DangKyTaiKhoan(urlThemTaiKhoan, ten_Dang_nhap, passw, ho_ten);
                }
            }
        });
    }

    private void AnhXa() {
        edtHoTen = (EditText) findViewById(R.id.editTextHoTen);
        edtTenDangNhap= (EditText) findViewById(R.id.editTextTenDangNhapDK);
        edtPass= (EditText) findViewById(R.id.editTextPassWordDK);
        btnDangKy=(Button) findViewById(R.id.buttonDangKyDK);
    }

    private  void DangKyTaiKhoan(String url, final String tendn, final String pas, final String hoten)
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(DangKyActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DangKyActivity.this,MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(DangKyActivity.this, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DangKyActivity.this, "Lỗi kết nối Server", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parrams=new HashMap<>();
                parrams.put("ten_dang_nhap",tendn);
                parrams.put("password",pas);
                parrams.put("ho_ten",hoten);
                return parrams;
            }
        };
        requestQueue.add(stringRequest);
    }
}
