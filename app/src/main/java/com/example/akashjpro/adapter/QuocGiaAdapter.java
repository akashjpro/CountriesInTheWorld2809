package com.example.akashjpro.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.akashjpro.countriesintheworld2809.DanhSachQuocGia;
import com.example.akashjpro.countriesintheworld2809.R;
import com.example.akashjpro.model.QuocGia;

import java.util.List;

/**
 * Created by Akashjpro on 9/28/2016.
 */

public class QuocGiaAdapter extends BaseAdapter {

    Activity context;
    int     layout;
    List<QuocGia> arrayQuocGia;

    public QuocGiaAdapter(Activity context, int layout, List<QuocGia> arrayQuocGia) {
        this.context = context;
        this.layout = layout;
        this.arrayQuocGia = arrayQuocGia;
    }

    @Override
    public int getCount() {
        return arrayQuocGia.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayQuocGia.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHoler{
        TextView ten;
        TextView  thuDo;
        ImageView hinh;
        TextView  quocTich;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = this.context.getLayoutInflater();
        //LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = convertView;
        ViewHoler viewHoler = new ViewHoler();

        if (rowView == null){
            rowView = inflater.inflate(this.layout, null);
            viewHoler.ten       = (TextView) rowView.findViewById(R.id.textViewTen);
            viewHoler.thuDo     = (TextView) rowView.findViewById(R.id.textViewThudo);
            viewHoler.quocTich  = (TextView) rowView.findViewById(R.id.textViewQuocTich);
            viewHoler.hinh      = (ImageView) rowView.findViewById(R.id.imageViewQuocKy);
            rowView.setTag(viewHoler);
        }
        else {
            viewHoler = (ViewHoler) rowView.getTag();
        }

        viewHoler.ten.setText(arrayQuocGia.get(position).getTen());
        viewHoler.thuDo.setText(arrayQuocGia.get(position).getThuDo());
        viewHoler.quocTich.setText(arrayQuocGia.get(position).getQuocTich());

        byte[] bytesHinh = arrayQuocGia.get(position).getQuocKy();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytesHinh, 0, bytesHinh.length);
        viewHoler.hinh.setImageBitmap(bitmap);

        if(!DanhSachQuocGia.isLoad) {
            Animation animation = AnimationUtils.loadAnimation(this.context, R.anim.hieu_ung_scale);
            rowView.startAnimation(animation);
        }
        if(position == (arrayQuocGia.size()-1)){
            DanhSachQuocGia.isLoad = true;
        }
        return rowView;
    }
}
