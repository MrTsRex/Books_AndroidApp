package com.books.saumy.books;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;

/**
 * Created by Saumy on 30-04-2015.
 */
public class AddFavorite {
    Context context;
    FavHelper favHelper;
    SQLiteDatabase sqLiteDatabase, favDatabase;
   // BookHelper bookHelper;
    Cursor cursor;

    public AddFavorite(Context context) {
        this.context = context;
    }

    /*public long AddFav(int i) {
        bookHelper = new BookHelper(context);
        sqLiteDatabase = bookHelper.getReadableDatabase();
        favHelper = new FavHelper(context);
        favDatabase = favHelper.getWritableDatabase();
        cursor = bookHelper.getDetails(sqLiteDatabase);
        cursor.moveToPosition(i);
        return favHelper.addInfo(cursor.getString(0), cursor.getString(1), cursor.getString(5), cursor.getString(3), cursor.getBlob(4), favDatabase);
    }*/

    /*public long AddFav(int i, String search, String criteria) {
        bookHelper = new BookHelper(context);
        sqLiteDatabase = bookHelper.getReadableDatabase();
        favHelper = new FavHelper(context);
        favDatabase = favHelper.getWritableDatabase();
        cursor = bookHelper.getDetails(sqLiteDatabase);
        int count = 0;
        search = search.toUpperCase();
        if (cursor.moveToFirst()) {
            do {
                String test;
                switch (criteria) {
                    case "book_name":
                        test = cursor.getString(0);
                        break;
                    case "author_name":
                        test = cursor.getString(1);
                        break;
                    case "genre":
                        test = cursor.getString(2);
                        break;
                    default:
                        test = cursor.getString(0);
                }
                test = test.toUpperCase();
                if (test.startsWith(search)) {
                    count++;
                }
                if (count == i + 1) {
                   return favHelper.addInfo(cursor.getString(0), cursor.getString(1), cursor.getString(5), cursor.getString(3), cursor.getBlob(4), favDatabase);
                }
            } while (cursor.moveToNext());
        }
        return -1;
    }*/
    public long AddFav(String book, String author, String review, String rating, byte[] data){
        favHelper = new FavHelper(context);
        favDatabase = favHelper.getWritableDatabase();
        return favHelper.addInfo(book,author,rating,review,data,favDatabase);
    }
}