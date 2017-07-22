package com.inti.sam.todoapplication;

/**
 * Created by User on 22/7/2017.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBManager extends SQLiteOpenHelper {  //myDBMANAGER works alongside with database.

    private static final int DATABASE_VERSION = 1;  //DATABASE VERION will refer to Things.java
    private static final String DATABASE_NAME = "thingsDB.db"; //saving user information into a file
    public static final String TABLE_THINGS = "things"; //name of the table
    public static final String COLUMN_ID = "_id"; //Name of column
    public static final String COLUMN_THINGSNAME = "thingsName";

    //We need to pass database information along to superclass
    public MyDBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        //constructor to maintain the behind the scene stuff of SQL
        //Context & factory is backround information
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  // when program is run this will run first.
        //when table is dreated for the first time, onCreate will run first.
        String query = "CREATE TABLE " + TABLE_THINGS + "(" +  //this creates a new table
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_THINGSNAME + " TEXT " +
                ");";
        db.execSQL(query);  //database is executed and query is passed in in onCreate(SQLiteDatabase db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  //modifies/change database structure
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THINGS); //DROP means delete entire table in sql language
        onCreate(db); //calls method onCreate with db because it takes db as paramater.
    }

    //Add a new row to the database
    public void addThings(Things things){  //add Items into database
        ContentValues values = new ContentValues();  //cotent values set different value into different column and insert them into table
        values.put(COLUMN_THINGSNAME, things.get_thingsName());
        SQLiteDatabase db = getWritableDatabase();  //SQLiteDatabase is key to database.
        db.insert(TABLE_THINGS, null, values);  //insert takes 3 parameter, insert new things into table
        db.close();
    }

    //Delete a things from the database
    public void deleteThings(String thingsName){  //Delete product from database
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_THINGS + " WHERE " + COLUMN_THINGSNAME + "=\"" + thingsName + "\";");
    }

    public String dbToString(){  //1 means when every condition is met
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_THINGS + " WHERE 1";
        Cursor c = db.rawQuery(query, null); //cursoe point to a location in result
        c.moveToFirst();  //move to first row in result


        while (!c.isAfterLast()) {  //loop through the table and find the word og thingsName and add it to the new line
            if (c.getString(c.getColumnIndex("thingsName")) != null) {
                dbString += c.getString(c.getColumnIndex("thingsName"));
                dbString += "\n";  //move to new line
            }
            c.moveToNext();
        }
        db.close();
        return dbString;  //return database String
    }

}