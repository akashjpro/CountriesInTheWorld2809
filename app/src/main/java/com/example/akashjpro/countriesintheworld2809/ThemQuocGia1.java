package com.example.akashjpro.countriesintheworld2809;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ThemQuocGia1 extends AppCompatActivity {


    EditText  edtDanSo, edtKhuVu, edtDienTich, edtGDP, edtDVTien;
    Button  btnThem;
    byte[] hinh;
    String ten, thuDo, quocTich, khuVuc, DVTien;
    Integer dienTich, danSo, GDP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_quoc_gia1);

        addControls();

        Bundle bundle = getIntent().getBundleExtra("data");
        if(bundle != null){
             hinh     = bundle.getByteArray("hinhQuocKy");
             ten      = bundle.getString("ten");
             thuDo    = bundle.getString("thuDo");
             quocTich = bundle.getString("quocTich");
        }
        addEvents();
    }

    private void addEvents() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtDanSo.getText().toString().equals("") && edtDienTich.getText().toString().equals("") && edtGDP.getText().toString().equals("") ){
                    dienTich = 0;
                    danSo    = 0;
                    GDP      = 0;
                }
                else {
                    dienTich = Integer.parseInt(edtDienTich.getText().toString());
                    danSo    = Integer.parseInt(edtDanSo.getText().toString());
                    GDP      = Integer.parseInt(edtGDP.getText().toString());
                }

                khuVuc = edtKhuVu.getText().toString();
                DVTien = edtDVTien.getText().toString();

                MainActivity.sqLite.insertData(ten, thuDo, quocTich, danSo, dienTich, khuVuc, DVTien, GDP, hinh);
                Toast.makeText(ThemQuocGia1.this, "Đã thêm ", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void addControls() {
        edtDanSo        = (EditText) findViewById(R.id.editTextDanSo);
        edtDienTich     = (EditText) findViewById(R.id.editTextDienTich);
        edtGDP          = (EditText) findViewById(R.id.editTextGDP);
        edtDVTien       = (EditText) findViewById(R.id.edtDVTienTe);
        edtKhuVu        = (EditText) findViewById(R.id.editTextKhuVuc);

        btnThem         = (Button) findViewById(R.id.buttonThemQuocGia);
    }



}
