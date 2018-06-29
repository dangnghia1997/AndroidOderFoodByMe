package com.example.dangn.oderfoodbyme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dangn on 6/28/2018.
 */


public class BanAdapter extends BaseAdapter{

    private Context context;
    private  int layout;
    private List<Ban> BanList;
    public String urlXoaBan="http://192.168.200.137:81/android_oderfood/Xoa_ban.php";

    public BanAdapter(Context context, int layout, List<Ban> banList) {
        this.context = context;
        this.layout = layout;
        BanList = banList;
    }

    @Override
    public int getCount() {
        return BanList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        //Ánh xạ view
        TextView txtTenBan= (TextView) view.findViewById(R.id.textviewTenBan);
        ImageView imghinh=(ImageView) view.findViewById(R.id.imgViewHinhBan);

        //gán giá trị
        final Ban ban=BanList.get(i);
        txtTenBan.setText(ban.getTenBan());
        imghinh.setImageResource(ban.getHinh());

        imghinh.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Thông báo Dialog
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(v.getRootView().getContext());
                alertDialog.setTitle("Thông Báo!");
                alertDialog.setMessage("Bạn thật sự muốn xóa dữ liệu này? Việc này sẽ làm mất dữ liệu của bạn? Bạn chắc chứ?");
                final View finalView = v;
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(finalView.getRootView().getContext(), "Có", Toast.LENGTH_SHORT).show();
                        String MABAN= Integer.toString(ban.getIdBan());
                        //Toast.makeText(finalView.getRootView().getContext(), "ma_ban"+MABAN, Toast.LENGTH_SHORT).show();

                        XoaBan(urlXoaBan,finalView.getRootView().getContext(),MABAN);
                        context.startActivity(new Intent(context,HanhChinhActivity.class));
                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.show();


                return false;
            }
        });



        return view;
    }



    public void XoaBan(String url, final Context ct, final String Ma_ban) {
        RequestQueue requestQueue = Volley.newRequestQueue(ct);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success"))
                {
                    Toast.makeText(ct, "Xóa Thành Công!", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(ct, "Xóa Thất Bại!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ct, "Lỗi kết nối Server", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parrams=new HashMap<>();
                parrams.put("ma_ban",Ma_ban);
                return parrams;
            }
        };

        requestQueue.add(stringRequest);
    }
}






