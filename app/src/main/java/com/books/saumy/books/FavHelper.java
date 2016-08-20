package com.books.saumy.books;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Saumy on 30-04-2015.
 */
public class FavHelper extends SQLiteOpenHelper {
    //byte[] data;
    private Context context;
    private static final String DATABASE_NAME="FAVINFO.DB";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_QUERY = "CREATE TABLE "+ Favdb.NewFavInfo.TABLE_NAME+"("+ Favdb.NewFavInfo.BOOK_NAME+" TEXT UNIQUE,"+ Favdb.NewFavInfo.AUTHOR_NAME+" TEXT,"+ Favdb.NewFavInfo.RATING+" TEXT,"+Favdb.NewFavInfo.REVIEW+" TEXT,"+Favdb.NewFavInfo.IMAGE+" BLOB)";
    public FavHelper(Context current){
        super(current,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "FAVORITES CREATED/OPENED");
        this.context=current;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS","FAVORITES TABLE CREATED");
    }
    public long addInfo(String name,String author,String rating,String review,byte[] data,SQLiteDatabase db){
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(Favdb.NewFavInfo.BOOK_NAME,name);
        contentvalues.put(Favdb.NewFavInfo.AUTHOR_NAME,author);
        contentvalues.put(Favdb.NewFavInfo.RATING,rating);
        contentvalues.put(Favdb.NewFavInfo.REVIEW,review);
        contentvalues.put(Favdb.NewFavInfo.IMAGE,data);
        long a=db.insertWithOnConflict(Favdb.NewFavInfo.TABLE_NAME,null,contentvalues,4);
        Log.e("DATABASE OPERATIONS","FAVOURITE ROW INSERTED");
        return a;
    }
    public Cursor getInfo(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {Favdb.NewFavInfo.BOOK_NAME, Favdb.NewFavInfo.AUTHOR_NAME, Favdb.NewFavInfo.IMAGE};
        cursor=db.query(Favdb.NewFavInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }
    public Cursor getDetails(SQLiteDatabase db) {
        Cursor cursor;
        //String[] projections = {Bookdb.NewBookInfo.BOOK_NAME, Bookdb.NewBookInfo.AUTHOR_NAME, Bookdb.NewBookInfo.GENRE, Bookdb.NewBookInfo.REVIEW};
        //cursor=db.query(Bookdb.NewBookInfo.TABLE_NAME,projections,null,null,null,null,null);
        String selectQuery = "SELECT * FROM fav_info";
        cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
    public void deleteRow(SQLiteDatabase db, String element ){
        db.execSQL("Delete from fav_info where book_name like '"+element+"'");
    }
    public void deleteAll(SQLiteDatabase db){
        db.delete(Favdb.NewFavInfo.TABLE_NAME,null,null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
