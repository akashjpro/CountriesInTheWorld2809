package com.example.akashjpro.countriesintheworld2809;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import static com.example.akashjpro.countriesintheworld2809.MainActivity.sqLite;

public class AboutQuocGia extends AppCompatActivity {

    ImageView imgFlag;
    TextView txtCountry, txtCapital, txtNationality, txtAreal, txtPopulation, txtRegions, txtGDP, txtCurrency;
    String mId;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_quoc_gia);
        addControls();

        Bundle bd = getIntent().getExtras();
        if(bd != null){
            String id = bd.getString("id", "1");
            Cursor cursorQuocGia = sqLite.getWhereData(id);
//            Cursor cursorQuocGia = sqLite.getData("SELECT * FROM QuocGia WHERE id = 1");
             while (cursorQuocGia.moveToNext()) {
                      String countruy    =   cursorQuocGia.getString(1);
                      String capital     =   cursorQuocGia.getString(2);
                      String nationality =   cursorQuocGia.getString(3);
                      byte[] flag        =   cursorQuocGia.getBlob(4);
                      Float areal        =   cursorQuocGia.getFloat(5);
                      Float population   =   cursorQuocGia.getFloat(6);
                      String region      =   cursorQuocGia.getString(7);
                      Float gdp          =   cursorQuocGia.getFloat(8);
                      String currency    =   cursorQuocGia.getString(9);

                 txtCountry.setText(countruy);
                 txtCapital.setText(capital);
                 txtNationality.setText(nationality);
                 Bitmap bitmap = BitmapFactory.decodeByteArray(flag, 0, flag.length);

                 imgFlag.setImageBitmap(bitmap);
                 txtAreal.setText(areal + "");
                 txtPopulation.setText(population + "");
                 txtRegions.setText(region);
                 txtGDP.setText(gdp + " $");
                 txtCurrency.setText(currency);


             }
             cursorQuocGia.close();

        }
    }
    //add controls from xml
    private void addControls() {
        scrollView     = (ScrollView) findViewById(R.id.layout);
        imgFlag        = (ImageView)findViewById(R.id.imageView);
        txtCountry     = (TextView) findViewById(R.id.textViewCountry);
        txtCapital     = (TextView) findViewById(R.id.textViewCaptital);
        txtNationality = (TextView) findViewById(R.id.textViewNationality);
        txtAreal       = (TextView) findViewById(R.id.textViewAreal);
        txtPopulation  = (TextView) findViewById(R.id.textViewpopulation);
        txtRegions     = (TextView) findViewById(R.id.textViewRegions);
        txtGDP         = (TextView) findViewById(R.id.textViewGDP);
        txtCurrency    = (TextView) findViewById(R.id.textViewCurrency);
    }


    @Override
    public void onBackPressed() {
        Animation animation =  AnimationUtils.loadAnimation(AboutQuocGia.this, R.anim.left_out);
        scrollView.startAnimation(animation);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                finish();
//            }
//        },500);
//        return;
        super.onBackPressed();
    }
}
