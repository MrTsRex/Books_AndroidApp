package com.books.saumy.books;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;


public class ShowList extends ActionBarActivity {
    final Context context=this;
    int i;
    private HandleXML2 obj;
    ProgressDialog pDialog;
    Bitmap bitmap,setbmp;
    ImageView img;
    public static  String title,AuthorName,imgurl,description,Isbn,rating,setAname;
    static String isbn13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        EditText e1=(EditText)findViewById(R.id.editText);
        EditText e2=(EditText)findViewById(R.id.editText2);
        EditText e3=(EditText)findViewById(R.id.editText3);
        EditText e4=(EditText)findViewById(R.id.editText4);
        e3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.getId() == R.id.editText3)
                {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (motionEvent.getAction() & MotionEvent.ACTION_MASK)
                    {
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });

        //TextView t3=(TextView)findViewById(R.id.textView);
        img = (ImageView)findViewById(R.id.imageView2);
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            title = extras.getString("Titles");
            e1.setText(title);
            AuthorName = extras.getString("AuthorName");
            setAname=AuthorName;
            e2.setText(AuthorName);
            imgurl=extras.getString("Image");
            new LoadImage().execute(imgurl);
            rating = extras.getString("Rating");
            e4.setText(rating+"/5");
            String urltitle=title.replace(" ","+");
            AuthorName=AuthorName.replace(" ","+");

            String finalUrl2 ="https://www.goodreads.com/book/title.xml?&key=U3ypCPHbapcu0zav81MV6A&title="+urltitle;
            obj = new HandleXML2(finalUrl2);
            obj.fetchXML();
            while(obj.parsingComplete);
            description=obj.getDescription();
            isbn13=obj.getIsbn13();
            description=description.replace("<br>"," ");
            description=description.replace("<em>"," ");
            description=description.replace("<strong>"," ");
            description=description.replace("</em>"," ");
            description=description.replace("</strong>"," ");
            description=description.replace("<p>"," ");
            e3.setText(description);
           /* finalUrl2 ="https://www.goodreads.com/search.xml?key=U3ypCPHbapcu0zav81MV6A&q="+title;
            obj = new HandleXML(finalUrl2);
            obj.fetchXML();
            while(obj.parsingComplete);
            e4.setText(obj.getRating()+"/5");*/

            /*String finalUrl ="https//www.goodreads.com/book/title.xml?&key=U3ypCPHbapcu0zav81MV6A&title="+title;
            obj2 = new HandleXML2(finalUrl);
            obj.fetchXML();
            while(obj.parsingComplete);
            e3.setText(obj.getDescription().toString());*/


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_fav) {
            byte[] data;
            AddFavorite addFavorite=new AddFavorite(context);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            setbmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            data=bos.toByteArray();
            long a=addFavorite.AddFav(title,setAname,description,rating,data);
            if(a==-1)
                Toast.makeText(getBaseContext(), "Already Added to favorites", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getBaseContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ShowList.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                setbmp=image;
                img.setImageBitmap(image);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();
                Toast.makeText(ShowList.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }
    public void Price(View view)
    {
        Intent intent = new Intent (this,PriceCompare.class);
        startActivity(intent);
    }
    public  String getISBN()
    {
        return isbn13;
    }
}
