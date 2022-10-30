package com.example.sample2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT, usn TEXT primary key, branch TEXT, semester TEXT, Phone_Number TEXT, Parent_Phone_Number TEXT, Alternate_Phone_Number TEXT, DOB TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");

    }

    public Boolean insertuserdata(String name, String usn, String branch, String semester, String Phone_Number, String Parent_Phone_Number, String Alternate_Phone_number, String DOB) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("USN", usn);
        contentValues.put("Branch", branch);
        contentValues.put("Semester", semester);
        contentValues.put("Phone Number", Phone_Number);
        contentValues.put("Parent Phone Number", Parent_Phone_Number);
        contentValues.put("Alternate Phone Number", Alternate_Phone_number);
        contentValues.put("DOB", DOB);
        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean updateuserdata(String name, String usn, String branch, String semester, String Phone_Number, String Parent_Phone_Number, String Alternate_Phone_number, String DOB) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Branch", branch);
        contentValues.put("Semester", semester);
        contentValues.put("Phone Number", Phone_Number);
        contentValues.put("Parent Phone Number", Parent_Phone_Number);
        contentValues.put("Alternate Phone Number", Alternate_Phone_number);
        contentValues.put("DOB", DOB);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where usn = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "usn=?", new String[]{usn});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletedata(String usn, String usnTXT) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where usn = ?", new String[]{usn});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "usn=?", new String[]{usn});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where usn = ?", null);
        return cursor;
    }
}
