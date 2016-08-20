package com.books.saumy.books;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Saumy on 26-04-2015.
 */
public class ListTouch extends ActionBarActivity {
    String book, author, genre,rev;
    TextView bname, aname, gname, review;
   // BookHelper bookHelper;
    FavHelper favHelper;
    SQLiteDatabase sqLiteDatabase;
    ImageView iv;
    byte[] data;
    Bitmap bmp;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_touch);;
        bname = (TextView) findViewById(R.id.bname);
        aname = (TextView) findViewById(R.id.aname);
        gname = (TextView) findViewById(R.id.gname);
        review = (TextView) findViewById(R.id.review);
        iv=(ImageView)findViewById(R.id.pic);
        Intent intent = getIntent();
        int i = 0;
        i = intent.getIntExtra("foutre", i);
        String s = Integer.toString(intent.getIntExtra("foutre", i));
        String string = intent.getStringExtra("fuck");
        String database=intent.getStringExtra("foder");
        /*if (database.equals("Book")) {
            bookHelper = new BookHelper(getApplicationContext());
            sqLiteDatabase = bookHelper.getReadableDatabase();
            cursor = bookHelper.getDetails(sqLiteDatabase);
        }*/
            favHelper = new FavHelper(getApplicationContext());
            sqLiteDatabase = favHelper.getReadableDatabase();
            cursor = favHelper.getDetails(sqLiteDatabase);
        if (string.equals("Default")) {
            cursor.moveToPosition(i);
            book = cursor.getString(0);
            author = cursor.getString(1);
            genre = cursor.getString(2);
            rev = cursor.getString(3);
            data=cursor.getBlob(4);
            bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
            sqLiteDatabase.close();
        }
        else {
            String criteria=intent.getStringExtra("joder");
            int count = 0;
            string=string.toUpperCase();
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
                    test=test.toUpperCase();
                    if (test.startsWith(string)) {
                        count++;
                    }
                    if (count == i+1 ) {
                        book = cursor.getString(0);
                        author = cursor.getString(1);
                        genre = cursor.getString(2);
                        rev=cursor.getString(3);
                        data=cursor.getBlob(4);
                        bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        break;
                    }
                } while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
        }
        bname.setText(book);
        aname.setText(author);
        gname.setText(genre);
        review.setText(rev);
        iv.setImageBitmap(bmp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_touch,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        long a=0;
        if (id == R.id.action_fav) {
            favHelper=new FavHelper(getApplicationContext());
            sqLiteDatabase=favHelper.getWritableDatabase();
            a=favHelper.addInfo(book,author,genre,rev,data,sqLiteDatabase);
            if(a==-1)
                Toast.makeText(getBaseContext(), "Already Added to favorites", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getBaseContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
            favHelper.close();
        }
        return true;
    }
}