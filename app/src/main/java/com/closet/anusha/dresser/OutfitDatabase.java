package com.closet.anusha.dresser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class OutfitDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="finoutfitdb.db";
    public static final String TABLE_NAME="finoutfittable";
    public static final String col1="acc_id";
    public static final String col2="acc_id1";
    public static final String col3="acc_id2";
    public static final String col4="acc_id3";
    public static final String col5="acc_id4";
    public static final String col6="acc_id5";
    public static final String col7="acc_id6";
    public static final String col8="cloth_id1";
    public static final String col9="cloth_id2";
    public static final String col10="worn_on";
    private String[] columns= {col1,col2,col3,col4, col5,col6,col7,col8,col9,col10};

    public OutfitDatabase(Context context) {
        super(context, DATABASE_NAME, null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+
                "( "+col1+" INTEGER PRIMARY KEY AUTOINCREMENT,  "
                +col2+" integer,"+
                col3+" integer, "+
                col4+" number,"+
                col5+" integer,"+
                col6+" integer, "+
                col7+" integer,"+
                col8+" integer, "+
                col9+" integer,"+
                col10+" varchar2(10)"+
                ")" );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(int id,int arr[],String date){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        if(id==0){
            contentValues.put(col1,id);
        }
        contentValues.put(col2,arr[0]);
        contentValues.put(col3,arr[1]);
        contentValues.put(col4,arr[2]);
        contentValues.put(col5,arr[3]);
        contentValues.put(col6,arr[4]);
        contentValues.put(col7,arr[5]);
        contentValues.put(col8,arr[6]);
        contentValues.put(col9,arr[7]);
        contentValues.put(col10,date);
        long result=database.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }
        return true;
    }

    public List<Outfits> readAllOutfits(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Outfits> contacts = new ArrayList<Outfits>();
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Outfits contact = new Outfits();
            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setAccId1(Integer.parseInt(cursor.getString(1)));
            contact.setAccId2(Integer.parseInt(cursor.getString(2)));
            contact.setAccId3(Integer.parseInt(cursor.getString(3)));
            contact.setAccId4(Integer.parseInt(cursor.getString(5)));
            contact.setShoeId1(Integer.parseInt(cursor.getString(4)));
            contact.setShoeId2(Integer.parseInt(cursor.getString(6)));
            contact.setClothId1(Integer.parseInt(cursor.getString(7)));
            contact.setClothId2(Integer.parseInt(cursor.getString(8)));
            contacts.add(contact);
            cursor.moveToNext();
        }
        cursor.close();
        return contacts;
    }

    public boolean deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME, col1 + " = ?",  new String[] { String.valueOf(id) });
        db.close();
        if(i != 0){
            return true;
        }else{
            return false;
        }
    }
}
