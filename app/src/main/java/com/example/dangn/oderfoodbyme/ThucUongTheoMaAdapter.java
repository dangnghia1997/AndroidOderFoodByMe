package com.example.dangn.oderfoodbyme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dangn on 6/29/2018.
 */


public class ThucUongTheoMaAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<ThucUongTheoMaLoai> thucuongtheomaList;

    private  String url="http://192.168.1.29:81/android_oderfood/Them_chon_mon.php";
    public ThucUongTheoMaAdapter(Context context, int layout, List<ThucUongTheoMaLoai> thucuongtheomaList) {
        this.context = context;
        this.layout = layout;
        this.thucuongtheomaList = thucuongtheomaList;
    }




    @Override
    public int getCount() {
        return thucuongtheomaList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=inflater.inflate(layout,null);

        //Ánh Xạ
        TextView txtTenThucUongTheoMa=(TextView)  convertView.findViewById(R.id.textViewTenThucUong);
        final TextView txtGia=(TextView) convertView.findViewById(R.id.textViewGiaThucUong);
        final EditText edtSoluong=(EditText) convertView.findViewById(R.id.editTextSoLuongThucUong);
        ImageView imgHinh=(ImageView) convertView.findViewById(R.id.imageViewThucUong);
        ImageView iconDatThucuong=(ImageView) convertView.findViewById(R.id.imageViewChonMon);
        final TextView txxt=(TextView)convertView.getRootView().findViewById(R.id.textViewthehienBan);
        //Gán giá trị

        final ThucUongTheoMaLoai thucuong=thucuongtheomaList.get(position);
        txtTenThucUongTheoMa.setText(thucuong.getTen_thuc_uong());
        final int ma_thuc_uong=thucuong.getMa_thuc_uong();
        //txtGia.setText(String.valueOf(thucuong.getGia()));  chút lấy giá bằng thucdon.getGia() để chuyển

        edtSoluong.setText(String.valueOf(1));
        imgHinh.setImageResource(thucuong.getHinh());

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        //String yourFormattedString = formatter.format(100000);
        txtGia.setText(formatter.format(thucuong.getGia()) + " VND");


        final View finalConvertView = convertView;
        iconDatThucuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView oo=(TextView) finalConvertView.getRootView().findViewById(R.id.textViewthehienBan);
                final String mmaban= (String) oo.getText();

                //Toast.makeText(context, "ma_ban :"+mmaban+" soluong: "+edtSoluong.getText()+"mathucuong: "+thucuong.getMa_thuc_uong(), Toast.LENGTH_SHORT).show();


                RequestQueue requestQueue= Volley.newRequestQueue(context);

                StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.trim().equals("success"))
                                {
                                    Toast.makeText(context, "Đã thêm", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(context, "Lỗi thêm", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, "Lỗi kết nối đến Server", Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params=new HashMap<>();
                        params.put("ma_ban",mmaban);
                        params.put("ma_thuc_uong",String.valueOf(ma_thuc_uong));
                        params.put("so_luong", String.valueOf(edtSoluong.getText()));
                        params.put("don_gia",String.valueOf(thucuong.getGia()));
                        return params;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });




        return convertView;
    }
}
