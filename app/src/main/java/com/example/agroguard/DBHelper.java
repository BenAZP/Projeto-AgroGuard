package com.example.agroguard;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "login;db";
    public DBHelper( Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (username TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");

    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null, values);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername (String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    @SuppressLint("Range")
    public String getUsername() {
        SQLiteDatabase db = this.getReadableDatabase();
        String username = "";

        Cursor cursor = db.rawQuery("SELECT username FROM users LIMIT 1", null);
        if (cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndex("username"));
        }

        cursor.close();
        db.close();

        return username;
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users", null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
                String userData = "Username: " + username + ", Password: " + password;
                userList.add(userData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return userList;
    }



}
