package com.rajendra.onlinedailygroceries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 9;

    //cart table
    private static final String CART_TABLE_NAME = "CART_TABLE";
    public static final String I_ID = "_IID";
    public static final String ITEM_NAME_KEY = "ITEM_NAME";
    public static final String PRICE =  "PRICE";
    public static final String QNTY =  "QNTY";
    private static final String CREATE_CART_TABLE =
            "CREATE TABLE " + CART_TABLE_NAME + " ( "+ I_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                    ITEM_NAME_KEY + " TEXT NOT NULL, " + PRICE + " TEXT NOT NULL, "+
                    QNTY +" INTEGER NOT NULL"+
                    ");" ;
    private static final String DROP_CART_TABLE = "DROP TABLE IF EXISTS " + CART_TABLE_NAME;
    private static final String SELECT_CART_TABLE = "SELECT * FROM "+ CART_TABLE_NAME;


    //Recently viewed items
    private static final String RV_TABLE_NAME = "RV_TABLE";
    public static final String RV_ID = "_RVID";
    public static final String RV_ITEM_NAME_KEY = "RV_ITEM_NAME";
    public static final String RV_ITEM_DESC_KEY = "RV_ITEM_DESC";
    public static final String RV_PRICE =  "RV_PRICE";
    public static final String RV_QNTY =  "RV_QNTY";
    public static final String RV_UNIT =  "RV_UNIT";
    public static final String RV_CARD =  "RV_CARD";
    public static final String RV_IMAGE =  "RV_IMGAE";
    private static final String CREATE_RV_TABLE =
            "CREATE TABLE " + RV_TABLE_NAME + " ( "+ RV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                    RV_ITEM_NAME_KEY + " TEXT NOT NULL, " +
                    RV_ITEM_DESC_KEY + " TEXT NOT NULL, " +
                    RV_PRICE + " TEXT NOT NULL, "+
                    RV_QNTY +" TEXT NOT NULL, "+
                    RV_UNIT +" TEXT NOT NULL, "+
                    RV_CARD +" INTEGER NOT NULL, "+
                    RV_IMAGE +" INTEGER NOT NULL"+
                    ");" ;
    private static final String DROP_RV_TABLE = "DROP TABLE IF EXISTS " + RV_TABLE_NAME;
    private static final String SELECT_RV_TABLE = "SELECT * FROM "+ RV_TABLE_NAME;


    public DbHelper(@Nullable Context context) {
        super(context, "Attendance.db", null , VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CART_TABLE);
        db.execSQL(CREATE_RV_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL(DROP_CART_TABLE);
            db.execSQL(DROP_RV_TABLE);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    long addInCart(String itemName , String p ,int q){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME_KEY , itemName);
        values.put(PRICE , p);
        values.put(QNTY , q);


        return database.insert(CART_TABLE_NAME , null,values);
    }

    Cursor getCartTable(){
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery(SELECT_CART_TABLE , null);
    }

    int deleteCartItems(long cid){
        SQLiteDatabase database = this.getReadableDatabase();
        return database.delete(CART_TABLE_NAME , I_ID+"=?" , new String[]{String.valueOf(cid)});
    }



    String getPricing(long id){
        String q ="";
        SQLiteDatabase database = this.getReadableDatabase();
        String whereClause = I_ID+"="+id;
        Cursor cursor = database.query(CART_TABLE_NAME , null ,whereClause , null , null ,null,null);
        if(cursor.moveToFirst())
            q = cursor.getString(cursor.getColumnIndexOrThrow(PRICE));
        return q;
    }

    int getQt(long id){
        int q =0;
        SQLiteDatabase database = this.getReadableDatabase();
        String whereClause = I_ID+"="+id;
        Cursor cursor = database.query(CART_TABLE_NAME , null ,whereClause , null , null ,null,null);
        if(cursor.moveToFirst())
            q = cursor.getInt(cursor.getColumnIndexOrThrow(QNTY));
        return q;
    }

    int getNumberOfItems(){

        int count=0;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(SELECT_CART_TABLE , null);
        count = cursor.getCount();
        return count;

    }

    long updateQnty(long sid ,int q){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QNTY , q);
        String whereClause = I_ID+"="+sid;
        return database.update(CART_TABLE_NAME ,values ,whereClause, null);
    }

    long updateRVId(long sid ,long id){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RV_ID , id);
        String whereClause = RV_ID+"="+sid;
        return database.update(CART_TABLE_NAME ,values ,whereClause, null);
    }

    long addToRV(String name , String desc , String price , String q , String unit , int card , int img){
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(RV_ITEM_NAME_KEY , name);
            values.put(RV_ITEM_DESC_KEY,desc);
            values.put(RV_PRICE , price);
            values.put(RV_QNTY , q);
            values.put(RV_UNIT , unit);
            values.put(RV_CARD , card);
            values.put(RV_IMAGE , img);

            return database.insert(RV_TABLE_NAME , null,values);
    }

    Cursor getRVTable(){
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery(SELECT_RV_TABLE , null);
    }

    int deleteRVItems(long cid){
        SQLiteDatabase database = this.getReadableDatabase();
        return database.delete(RV_TABLE_NAME , RV_ID+"=?" , new String[]{String.valueOf(cid)});
    }

    int getItemRvCount(){

        int count=0;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(SELECT_RV_TABLE , null);
        count = cursor.getCount();
        return count;

    }


    String getRVPricing(long id){
        String q ="";
        SQLiteDatabase database = this.getReadableDatabase();
        String whereClause = RV_ID+"="+id;
        Cursor cursor = database.query(RV_TABLE_NAME , null ,whereClause , null , null ,null,null);
        if(cursor.moveToFirst())
            q = cursor.getString(cursor.getColumnIndexOrThrow(RV_PRICE));
        return q;
    }

    String getRVQ(long id){
        String q ="";
        SQLiteDatabase database = this.getReadableDatabase();
        String whereClause = RV_ID+"="+id;
        Cursor cursor = database.query(RV_TABLE_NAME , null ,whereClause , null , null ,null,null);
        if(cursor.moveToFirst())
            q = cursor.getString(cursor.getColumnIndexOrThrow(RV_QNTY));
        return q;
    }

    String getRVName(long id){
        String q ="";
        SQLiteDatabase database = this.getReadableDatabase();
        String whereClause = RV_ID+"="+id;
        Cursor cursor = database.query(RV_TABLE_NAME , null ,whereClause , null , null ,null,null);
        if(cursor.moveToFirst())
            q = cursor.getString(cursor.getColumnIndexOrThrow(RV_ITEM_NAME_KEY));
        return q;
    }

    String getRvItemDescKey(long id){
        String q ="";
        SQLiteDatabase database = this.getReadableDatabase();
        String whereClause = RV_ID+"="+id;
        Cursor cursor = database.query(RV_TABLE_NAME , null ,whereClause , null , null ,null,null);
        if(cursor.moveToFirst())
            q = cursor.getString(cursor.getColumnIndexOrThrow(RV_ITEM_DESC_KEY));
        return q;
    }

    String getRvUnit(long id){
        String q ="";
        SQLiteDatabase database = this.getReadableDatabase();
        String whereClause = RV_ID+"="+id;
        Cursor cursor = database.query(RV_TABLE_NAME , null ,whereClause , null , null ,null,null);
        if(cursor.moveToFirst())
            q = cursor.getString(cursor.getColumnIndexOrThrow(RV_UNIT));
        return q;
    }

    int getRVCard(long id){
        int q =0;
        SQLiteDatabase database = this.getReadableDatabase();
        String whereClause = RV_ID+"="+id;
        Cursor cursor = database.query(RV_TABLE_NAME , null ,whereClause , null , null ,null,null);
        if(cursor.moveToFirst())
            q = cursor.getInt(cursor.getColumnIndexOrThrow(RV_CARD));
        return q;
    }

    int getRVimg(long id){
        int q =0;
        SQLiteDatabase database = this.getReadableDatabase();
        String whereClause = RV_ID+"="+id;
        Cursor cursor = database.query(RV_TABLE_NAME , null ,whereClause , null , null ,null,null);
        if(cursor.moveToFirst())
            q = cursor.getInt(cursor.getColumnIndexOrThrow(RV_IMAGE));
        return q;
    }
}
