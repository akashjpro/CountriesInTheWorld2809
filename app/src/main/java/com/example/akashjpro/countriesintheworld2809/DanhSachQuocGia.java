package com.example.akashjpro.countriesintheworld2809;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.akashjpro.adapter.QuocGiaAdapter;
import com.example.akashjpro.model.QuocGia;

import java.util.ArrayList;

public class DanhSachQuocGia extends AppCompatActivity {

    ArrayList<QuocGia> mangQuocGia;
    QuocGiaAdapter  quocGiaAdapter;
    ListView lvDSQuocGia;
    public static boolean isLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_quoc_gia);
        lvDSQuocGia = (ListView) findViewById(R.id.lvDSQuocGia);
        isLoad = false;



        mangQuocGia = new ArrayList<QuocGia>();
        Cursor cursorQuocGia = MainActivity.sqLite.getData("SELECT * FROM QuocGia");
        while(cursorQuocGia.moveToNext()){
            mangQuocGia.add(new QuocGia(
                    cursorQuocGia.getInt(0),
                    cursorQuocGia.getString(1),
                    cursorQuocGia.getString(2),
                    cursorQuocGia.getString(3),
                    cursorQuocGia.getBlob(4),
                    cursorQuocGia.getLong(5),
                    cursorQuocGia.getLong(6),
                    cursorQuocGia.getString(7),
                    cursorQuocGia.getString(8),
                    cursorQuocGia.getString(9)
            ));
          //  Toast.makeText(DanhSachQuocGia.this, "CauHoi: ", Toast.LENGTH_SHORT).show();
        }
        cursorQuocGia.close();

        quocGiaAdapter = new QuocGiaAdapter(DanhSachQuocGia.this, R.layout.dong_quoc_gia, mangQuocGia);
        lvDSQuocGia.setAdapter(quocGiaAdapter);

        lvDSQuocGia.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                xoaQuocGia(position);
                return false;
            }
        });

       lvDSQuocGia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent (DanhSachQuocGia.this, AboutQuocGia.class);
               String idQuocGia = mangQuocGia.get(position).getId().toString();
               intent.putExtra("id", idQuocGia);
               startActivity(intent);
               overridePendingTransition(R.anim.left_in, R.anim.normal);
           }
       });
    }

    private void xoaQuocGia(int position) {
        int id =  mangQuocGia.get(position).getId();
        MainActivity.sqLite.queryData("DELETE FROM QuocGia WHERE id = '"+ id +"'");
        mangQuocGia.remove(position);
        quocGiaAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Đã xóa quốc gia ", Toast.LENGTH_SHORT).show();
    }


}
