package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Login
        db.execSQL("CREATE TABLE Login (ID INTEGER PRIMARY KEY AUTOINCREMENT, Email TEXT, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Login");
        onCreate(db);
    }

    // Thêm dữ liệu vào SQLite
    public boolean addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", email);
        contentValues.put("Password", password);

        long result = db.insert("Login", null, contentValues);
        return result != -1;
    }

    // Kiểm tra người dùng
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Login WHERE Email = ? AND Password = ?", new String[] {email, password});
        return cursor.getCount() > 0;
    }
}
