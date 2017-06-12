package com.example.akashjpro.countriesintheworld2809;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import static com.example.akashjpro.countriesintheworld2809.MainActivity.database;

/**
 * Created by Akashjpro on 9/28/2016.
 */

public class SQLite extends SQLiteOpenHelper {
    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql){
         database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public Cursor getWhereData( String ma){
        database = getReadableDatabase();
        String sql = "SELECT * FROM QuocGia WHERE id = ?";
        String[] whereArgs = {ma};
        return database.rawQuery(sql, whereArgs);
    }



    public void insertData(String ten, String thudo, String quocTich, Integer danSo, Integer dienTich, String khuVuc, String DVTien, Integer GDP, byte[] hinh){
        database = getWritableDatabase();
        String sql = "INSERT INTO QuocGia VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        SQLiteStatement statement = database.compileStatement(sql);// bien dich lai  theo cach cua minh
        statement.clearBindings();// xoa truoc khi lam

        statement.bindString(1, ten);// 1: index, ten la gia tri
        statement.bindString(2, thudo);
        statement.bindString(3, quocTich);
        statement.bindBlob(4, hinh);// bindBlog kieu byte
        statement.bindLong(5, dienTich);
        statement.bindLong(6, danSo);
        statement.bindString(7, khuVuc);
        statement.bindLong(8, GDP);
        statement.bindString(9,DVTien);

        statement.executeInsert();// insert vao database

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
