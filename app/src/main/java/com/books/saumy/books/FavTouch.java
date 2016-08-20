package com.books.saumy.books;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class FavTouch extends ActionBarActivity {
    String book, author, genre,rev;
    TextView bname, aname, gname, review;
    //BookHelper bookHelper;
    FavHelper favHelper;
    SQLiteDatabase sqLiteDatabase;
    ImageView iv;
    byte[] data;
    Bitmap bmp;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_touch);
        bname = (TextView) findViewById(R.id.bname);
        aname = (TextView) findViewById(R.id.aname);
        gname = (TextView) findViewById(R.id.gname);
        review = (TextView) findViewById(R.id.review);
        iv=(ImageView)findViewById(R.id.pic);
        Intent intent = getIntent();
        int i = 0;
        i = intent.getIntExtra("foutre", i);
        String s = Integer.toString(intent.getIntExtra("foutre", i));
       // bookHelper = new BookHelper(getApplicationContext());
        //sqLiteDatabase = bookHelper.getReadableDatabase();
        //cursor = bookHelper.getDetails(sqLiteDatabase);
        favHelper = new FavHelper(getApplicationContext());
        sqLiteDatabase = favHelper.getReadableDatabase();
        cursor = favHelper.getDetails(sqLiteDatabase);
        cursor.moveToPosition(i);
        book = cursor.getString(0);
        author = cursor.getString(1);
        genre = cursor.getString(2);
        rev = cursor.getString(3);
        data=cursor.getBlob(4);
        bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        sqLiteDatabase.close();
        bname.setText(book);
        aname.setText(author);
        gname.setText(genre);
        review.setText(rev);
        iv.setImageBitmap(bmp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fav_touch,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_fav){
            favHelper = new FavHelper(getApplicationContext());
            sqLiteDatabase=favHelper.getWritableDatabase();
            favHelper.deleteRow(sqLiteDatabase,book);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}

