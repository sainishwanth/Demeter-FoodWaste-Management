package com.example.foodwaste;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static String checkuser;
    public static String checkphno;
    public static String checkemail;
    public static String checkaddress;
    public static String checktype;
    public static String checkpass;
    public static final String DBNAME = "Login.db";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT, phno TEXT, email TEXT, address TEXT, type TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists users");
        onCreate(MyDB);
    }

    public Boolean insertData(String username, String password, String phno, String email, String address, String type){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("phno", phno);
        contentValues.put("email", email);
        contentValues.put("address", address);
        contentValues.put("type", type);
        long result = MyDB.insert("users", null, contentValues);
        return result != -1;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        return cursor.getCount() > 0;
    }
    public void getData(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        while(cursor.moveToNext()){
            checkpass = cursor.getString(1);
            checkphno = cursor.getString(2);
            checkemail = cursor.getString(3);
            checkaddress = cursor.getString(4);
            checktype = cursor.getString(5);
        }
        cursor.close();
    }
    public void changePass(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE users SET password = ? WHERE username = ?", new String[] {password, username});
        checkpass = password;
    }
    public void changeEmail(String username, String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE users SET email = ? WHERE username = ?", new String[]{email, username});
        checkemail = email;
    }
    public void changePhno(String username, String phno) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE users SET phno = ? WHERE username = ?", new String[]{phno, username});
        checkphno = phno;
    }
    public void changeAdd(String username, String address) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE users SET address = ? WHERE username = ?", new String[]{address, username});
        checkaddress = address;
    }
}