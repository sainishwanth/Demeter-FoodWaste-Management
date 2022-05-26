package com.example.foodwaste;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBForm extends SQLiteOpenHelper {
    //public static String checkuser;
    //public static String checkphno;
    //public static String checkemail;
    //public static String checkaddress;
    //public static String checktype;
    //public static String checkpass;
    //public static final String DBNAME = "Login.db";
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

    public void getData(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        while (cursor.moveToNext()) {
            //checkpass = cursor.getString(1);
            //checkphno = cursor.getString(2);
            //checkemail = cursor.getString(3);
            //checkaddress = cursor.getString(4);
            //checktype = cursor.getString(5);
        }
        cursor.close();
    }
}