package com.books.saumy.books;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class ViewFavorites extends ActionBarActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    final Context context=this;
    ListView listView,visib;
    SQLiteDatabase sqLiteDatabase;
    FavHelper favHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    byte[] data;
    Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_favorites);
        visib=(ListView)findViewById(R.id.list_view);
        visib.setVisibility(View.VISIBLE);
        listDataAdapter=new ListDataAdapter(getApplicationContext(),R.layout.row_layout);
        visib.setAdapter(listDataAdapter);
        favHelper=new FavHelper(getApplicationContext());
        sqLiteDatabase = favHelper.getReadableDatabase();
        cursor=favHelper.getInfo(sqLiteDatabase);
        if(cursor.moveToFirst()){
            do {
                String book, author, genre;
                data=cursor.getBlob(2);
                bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                book=cursor.getString(0);
                author=cursor.getString(1);
                //genre=cursor.getString(2);
                DataProvider dataProvider=new DataProvider(bmp,book,author);
                listDataAdapter.add(dataProvider);
            }while (cursor.moveToNext());
            sqLiteDatabase.close();
        }
        visib.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(view.getContext(),FavTouch.class);
                intent.putExtra("foutre",position);
                startActivity(intent);
            }
        });
        sqLiteDatabase.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_favorites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            favHelper=new FavHelper(getApplicationContext());
            sqLiteDatabase=favHelper.getWritableDatabase();
            favHelper.deleteAll(sqLiteDatabase);
            favHelper.close();
            /*Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);*/
            visib.setVisibility(View.INVISIBLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
