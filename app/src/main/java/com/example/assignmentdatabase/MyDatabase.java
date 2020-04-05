package com.example.assignmentdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="PERISHABLES.db";
    public static final String TABLE_NAME="FRUITS";
    public static final String COLUMN1="FRUIT_NAME";
    public static final String COLUMN2="quantity";
    public static final String COLUMN3="PRICE";
    public MyDatabase(@Nullable Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_NAME+" ("+COLUMN1+" TEXT,"+COLUMN2+" INT,"+COLUMN3+" INT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insert(String fname,int quantity,int price)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN1,fname);
        cv.put(COLUMN2,quantity);
        cv.put(COLUMN3,price);
        if(db.insert(TABLE_NAME,null,cv)==-1)
            return false;
        return true;
    }
    public ArrayList<Fruit> getValues()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
        cursor.moveToFirst();
        ArrayList<Fruit> list=new ArrayList<Fruit>();
        while(cursor.moveToNext())
        {
            String fname=cursor.getString(cursor.getColumnIndex(COLUMN1));
            int quantity=cursor.getInt(cursor.getColumnIndex(COLUMN2));
            int price=cursor.getInt(cursor.getColumnIndex(COLUMN3));
            list.add(new Fruit(fname,quantity,price));
        }
        return list;
    }
    public void updatePrice(String fname,int price)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("price",price);
        db.update(TABLE_NAME,cv,"fname = ? ",new String[]{fname});
    }
    public void updateQuantity(String fname,int quantity)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("quantity",quantity);
        db.update(TABLE_NAME,cv,COLUMN1+" =? ",new String[]{fname});
    }
    public void delete(String fname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN1+" =? ",new String[]{fname});
    }

}
