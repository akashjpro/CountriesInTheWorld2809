package com.example.akashjpro.countriesintheworld2809;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    Button btnDSQuocGia, btnThem;

    String DATABASE_NAME="dbQuocGia.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database=null;
    public static SQLite sqLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        xuLyChepCSDLVaoHeThong();

        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        sqLite = new SQLite(this, DATABASE_NAME, null, 1);

        addEvents();

//        Cursor cursor = sqLite.getData("SELECT * FROM QuocGia WHERE id = 1");
//        while (cursor.moveToNext()) {
//            float a = cursor.getFloat(8);
//            Toast.makeText(this, "GDB = " + a, Toast.LENGTH_SHORT).show();
//        }


    }

    private void addEvents() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThemQuocGia.class);
                startActivity(intent);
            }
        });

        btnDSQuocGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DanhSachQuocGia.class);
                startActivity(intent);
                overridePendingTransition(R.anim.scale_zoom_center, R.anim.normal);
            }
        });
    }

    private void addControls() {
        btnDSQuocGia = (Button) findViewById(R.id.buttonDSQuocGia);
        btnThem      = (Button) findViewById(R.id.buttonThem);
    }

    private void xuLyChepCSDLVaoHeThong() {

        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists())
        {
            try
            {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder",
                        Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDataBaseFromAsset() {
        try
        {
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);

            String outFileName = layDuongDanLuuTru();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0)
            {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception e)
        {
            Log.e("LOI_SAO_CHEP", e.toString());
        }
    }

    private String layDuongDanLuuTru(){
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }
}
