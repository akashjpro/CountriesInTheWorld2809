package com.example.akashjpro.countriesintheworld2809;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class ThemQuocGia extends AppCompatActivity {

    ImageButton ibtnThemQuocKy;
    ImageView imgQuocKy;
    EditText edtTenNuoc, edtThuDo, edtQuocTich, edtDanSo;
    Button btnNext;
    int REQUEST_CODE_FOLDER = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_quoc_gia);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemQuocGia.this, ThemQuocGia1.class);
                Bundle bundle = new Bundle();
                bundle.putByteArray("hinhQuocKy", ImageView_To_Byte(imgQuocKy));
                bundle.putString("ten", edtTenNuoc.getText().toString());
                bundle.putString("thuDo", edtThuDo.getText().toString());
                bundle.putString("quocTich", edtQuocTich.getText().toString());

                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });

        ibtnThemQuocKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFolder = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentFolder, REQUEST_CODE_FOLDER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK &&  data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            imgQuocKy.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addControls() {
        ibtnThemQuocKy  = (ImageButton) findViewById(R.id.imageButtonThemQuocKy);
        imgQuocKy       = (ImageView) findViewById(R.id.imgThemQuocKy);

        edtTenNuoc      = (EditText) findViewById(R.id.editTextTen);
        edtThuDo        = (EditText) findViewById(R.id.editTextThuDo);
        edtQuocTich     = (EditText) findViewById(R.id.editTextQuocTich);


        btnNext         = (Button) findViewById(R.id.buttonNext);
    }
    public byte[] ImageView_To_Byte(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}
