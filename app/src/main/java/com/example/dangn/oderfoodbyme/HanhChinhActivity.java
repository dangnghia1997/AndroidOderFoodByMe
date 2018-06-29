package com.example.dangn.oderfoodbyme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
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

public class HanhChinhActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    GridView gvBan;
    ArrayList<Ban> arrayBan;
    BanAdapter banAdapter;
    String urlDsBan="http://192.168.200.137:81/android_oderfood/Doc_ban.php";
    String urlThemBan="http://192.168.200.137:81/android_oderfood/Them_ban.php";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanh_chinh);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView) findViewById(R.id.navigation_View);
        navigationView.setNavigationItemSelectedListener(this);

        AnhXa();

        GetDSBan(urlDsBan);

        banAdapter= new BanAdapter(this,R.layout.dong_ban,arrayBan);
        gvBan.setAdapter(banAdapter);


    }




    private void AnhXa() {
        gvBan =(GridView) findViewById(R.id.gridviewBan);
        arrayBan= new ArrayList<>();
        //Chuyển string thành int
//       String mDrawableName = "banan";
//      int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
//
//        arrayBan.add(new Ban(1,"Bàn 01",resID));
//        arrayBan.add(new Ban(2,"Bàn 02",resID));
//        arrayBan.add(new Ban(3,"Bàn 03",resID));
//        arrayBan.add(new Ban(4,"Bàn 04",resID));
//        arrayBan.add(new Ban(5,"Bàn 05",resID));
//        arrayBan.add(new Ban(6,"Bàn 06",resID));
//        arrayBan.add(new Ban(7,"Bàn 07",resID));

//        arrayBan.add(new Ban(2,"Bàn 02",R.drawable.ban1));
//        arrayBan.add(new Ban(3,"Bàn 03",R.drawable.ban1));
//        arrayBan.add(new Ban(4,"Bàn 04",R.drawable.ban1));
//        arrayBan.add(new Ban(5,"Bàn 05",R.drawable.ban1));
//        arrayBan.add(new Ban(6,"Bàn 06",R.drawable.ban1));
//        arrayBan.add(new Ban(7,"Bàn 07",R.drawable.ban1));
//        arrayBan.add(new Ban(8,"Bàn 08",R.drawable.ban1));




    }

    //Tạo menu của ICon thêm bàn

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manhinh_chinh,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        //Nút Thêm bàn
        if(item.getItemId() == R.id.themBan)
        {
            //Toast.makeText(this, "Thêm bàn", Toast.LENGTH_SHORT).show();
                DialogThemBan();
        }
        return super.onOptionsItemSelected(item);
    }

    //Dialog Thêm Bàn
    private void DialogThemBan()
    {
        final Dialog dialog   = new Dialog(this);
        dialog.setTitle("Thêm Bàn");
        dialog.setContentView(R.layout.custom_dialog_them_ban);

        //Ánh Xạ
        final EditText edtThemBan= (EditText) dialog.findViewById(R.id.editTextThemBan);
        Button btnThemBan= (Button) dialog.findViewById(R.id.butttonThemBan);
        Button btnHuyThemBan=(Button) dialog.findViewById(R.id.buttonHuyThemBan) ;

        btnHuyThemBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialog.cancel();
            }
        });

        btnThemBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten_ban=edtThemBan.getText().toString().trim();
                if(ten_ban.isEmpty())
                {
                    Toast.makeText(HanhChinhActivity.this, "Vui lòng nhập bàn!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Toast.makeText(HanhChinhActivity.this, ""+ten_ban, Toast.LENGTH_SHORT).show();

                    ThemBan(urlThemBan,ten_ban,"0");
                    startActivity(new Intent(HanhChinhActivity.this,HanhChinhActivity.class));
                }
            }
        });

        dialog.show();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.thuc_don:
                Toast.makeText(this, "Thực Đơn", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.dsban:
                Toast.makeText(this, "Danh sách bàn", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }


    //Lấy ds bàn từ server

    private void GetDSBan(String url)
    {
        final RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject object= response.getJSONObject(i);
                                if(object.getInt("trang_thai") == 1) {
                                    arrayBan.add(new Ban(object.getInt("ma_ban"),
                                            object.getString("ten_ban"), R.drawable.banantrue));
                                }
                                else
                                {
                                    arrayBan.add(new Ban(object.getInt("ma_ban"),
                                        object.getString("ten_ban"), R.drawable.banan));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HanhChinhActivity.this, "Lỗi kết nối server", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    //Thêm Bàn từ app lên SERVER
    private void ThemBan(String url, final String ten_ban_input, final String tt)
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(HanhChinhActivity.this, "Thêm Thành Công!1", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(HanhChinhActivity.this, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HanhChinhActivity.this, "Lỗi kết nối đến Server", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parrams = new HashMap<>();
                parrams.put("ten_ban",ten_ban_input);
                parrams.put("tt",tt);
                return parrams;
            }
        };
        requestQueue.add(stringRequest);
    }



}
