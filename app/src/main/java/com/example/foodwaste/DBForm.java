package com.example.foodwaste;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DBForm extends SQLiteOpenHelper {
    public DBForm(Context context) {
        super(context, "Form.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table forms(item TEXT, purchasedDate TEXT, expDate TEXT, Address TEXT,phno TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists forms");
        onCreate(MyDB);
    }

    public Boolean insertData(String item, String purchaseDate, String expDate, String address, String phno) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item", item);
        contentValues.put("purchasedDate", purchaseDate);
        contentValues.put("expDate", expDate);
        contentValues.put("Address", address);
        contentValues.put("phno", phno);
        long result = MyDB.insert("forms", null, contentValues);
        return result != -1;
    }

    public int getCount(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from forms", null);
        int count = cursor.getCount();
        return count;
    }

    public List<String> getItem() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        List<String> list = new ArrayList<String>();
        Cursor cursor = MyDB.rawQuery("select * from forms", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(0);
                list.add(name);
                cursor.moveToNext();
            }
        }
        return list;
    }
    public List<String> get_purchaseDate() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        List<String> list = new ArrayList<String>();
        Cursor cursor = MyDB.rawQuery("select * from forms", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(1);
                list.add(name);
                cursor.moveToNext();
            }
        }
        return list;
    }
    public List<String> get_expDate() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        List<String> list = new ArrayList<String>();
        Cursor cursor = MyDB.rawQuery("select * from forms", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(2);
                list.add(name);
                cursor.moveToNext();
            }
        }
        return list;
    }
    public List<String> get_address() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        List<String> list = new ArrayList<String>();
        Cursor cursor = MyDB.rawQuery("select * from forms", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(3);
                list.add(name);
                cursor.moveToNext();
            }
        }
        return list;
    }
    public List<String> get_phno() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        List<String> list = new ArrayList<String>();
        Cursor cursor = MyDB.rawQuery("select * from forms", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(4);
                list.add(name);
                cursor.moveToNext();
            }
        }
        return list;
    }
}