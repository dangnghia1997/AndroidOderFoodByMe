package com.example.dangn.oderfoodbyme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by dangn on 6/29/2018.
 */

public class LoaiThucUongAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<LoaiThucUong> loaiThucUongList;


    public LoaiThucUongAdapter(Context context, int layout, List<LoaiThucUong> loaiThucUongList) {
        this.context = context;
        this.layout = layout;
        this.loaiThucUongList = loaiThucUongList;
    }


    @Override
    public int getCount() {
        return loaiThucUongList.size();
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

        //Ánh Xạ View
        TextView textView=(TextView) convertView.findViewById(R.id.textViewLoaiThucUong);
        ImageView imgHinh= (ImageView) convertView.findViewById(R.id.imageViewLoaiThucUong);
        ConstraintLayout dong=(ConstraintLayout) convertView.findViewById(R.id.constraintDong);

        //Gán Giá trị
        final LoaiThucUong loaiThucUong= loaiThucUongList.get(position);
        textView.setText(loaiThucUong.getTenLoai());

        imgHinh.setImageResource(loaiThucUong.getImgHinh());


        final View finalConvertView = convertView;
        dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tx=(TextView) finalConvertView.getRootView().findViewById(R.id.textviewchuamaban);


              final int Ma_loai=loaiThucUong.getMa_loai();
              String Ten_Ban=loaiThucUong.getTenLoai();
              Intent intent=new Intent(context,ThucUongTheoMaLoaiActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("ma_loai",Ma_loai);
               bundle.putString("ten_loai",Ten_Ban);
                bundle.putString("ma_ban", (String) tx.getText());
              intent.putExtra("dulieucualoai",bundle);


              context.startActivity(intent);
            }
        });

        return convertView;
    }
}
