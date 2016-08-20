package com.books.saumy.books;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class MainParse extends ActionBarActivity {
    private HandleXML obj;
    //  private String url1 = "https://www.goodreads.com/book/title.xml?&key=U3ypCPHbapcu0zav81MV6A&title=";
    private String url1="https://www.goodreads.com/search.xml?key=U3ypCPHbapcu0zav81MV6A&q=";
    ProgressDialog pDialog;
    Bitmap bitmap;
    ImageView img;

    private EditText book,title,description;
    String imageurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parse);
        final Button button=(Button)findViewById(R.id.button1);
        img = (ImageView)findViewById(R.id.imageView);
        book = (EditText)findViewById(R.id.editText1);
        ProgressDialog Dialog = new ProgressDialog(MainParse.this);
        Dialog.hide();

        book.addTextChangedListener(new TextWatcher() {
            @Override

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                final String s = book.getText().toString();
                if (s.toString().trim().length() == 0) {
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_parse, menu);
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
            Intent intent=new Intent(this,settings.class);
            intent.putExtra("key","SNET");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void open(View view){
       new AsyncTaskRunner3().execute();





    }
    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainParse.this);
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
                img.setImageBitmap(image);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();
                Toast.makeText(MainParse.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }
    private class AsyncTaskRunner3 extends AsyncTask<String, String, String> {
        ProgressDialog Dialog = new ProgressDialog(MainParse.this);
        private String resp;
        @Override
        protected String doInBackground(String... params) {

    /*String url = book.getText().toString();
                url = url.replaceAll(" ", "+");
                String finalUrl = url1 + url ;
                title.setText(finalUrl);
                obj = new HandleXML(finalUrl);
                obj.fetchXML();
                while(obj.parsingComplete);
                title.setText(obj.getTitle());
                description.setText(obj.getDescription());
        imageurl.setText(obj.getImageUrl());*/
            //ListView listView=(ListView)findViewById(R.id.listView);
            //Fetches XML
            int i;
            String url = book.getText().toString();
            url = url.replaceAll(" ", "+");
            String finalUrl = url1 + url ;
            //title.setText(finalUrl);
            obj = new HandleXML(finalUrl);
            obj.fetchXML();
            while(obj.parsingComplete);
            if(obj.getTitle().equals("title"))
            {

                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(MainParse.this);
                dlgAlert.setMessage("Enter valid book title");
                dlgAlert.setTitle("Book not found");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();


            }
            //RETURNS DESCRIPTION
        /*String finalUrl2 ="https://www.goodreads.com/book/title.xml?&key=U3ypCPHbapcu0zav81MV6A&title=Steve+Jobs+American+Genius";
        obj2 = new HandleXML2(finalUrl2);
        obj2.fetchXML();
        while(obj2.parsingComplete);
        e4.setText(obj2.getDescription());*/

            //WITHOUT HANDLEXML2
       /* String finalUrl2 ="https://www.goodreads.com/book/title.xml?&key=U3ypCPHbapcu0zav81MV6A&title=Steve+Jobs+American+Genius";
        obj = new HandleXML(finalUrl2);
        obj.fetchXML();
        while(obj.parsingComplete);
        e4.setText(obj.getDescription());*/

            // title.setText(obj.getTitle());
        /*Intent intent = new Intent(this, ListViewcolumn.class);
        startActivity(intent);*/
       /* ArrayList listTitle=new ArrayList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,obj.AuthorAr);
        listView.setAdapter(adapter);*/

            //GIVES THE IMAGE
       /* imageurl=obj.getImageUrl();
        new LoadImage().execute(imageurl);*/

            return " ";

        }

        @Override
        protected void onPostExecute(String result) {
            Dialog.dismiss();
            final Intent intent = new Intent(getApplicationContext(),ShowList.class);

            //RETURNS TITLE  AND AUTHOR IN LISTVIEW
            ArrayList<HashMap<String, String>> feedList= new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map = new HashMap<String, String>();
            ListView listView=(ListView)findViewById(R.id.listView);
            //Fetches XML
            int i;
            //TitielAr is an Arraylist
            for(i=0;i<obj.TitielAr.size();i++)
            {
                map = new HashMap<String, String>();
                map.put("Title", (String) obj.TitielAr.get(i));
                map.put("Author", (String) obj.AuthorAr.get(i));
                feedList.add(map);//This line adds the content in the list
                SimpleAdapter simpleAdapter = new SimpleAdapter(MainParse.this, feedList, R.layout.activity_list_viewcolumn, new String[]{"Title", "Author"}, new int[]{R.id.TextFirst,R.id.TextThird});
                listView.setAdapter(simpleAdapter);//Displays the list

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Dialog.show();
                        String Titles=(String)obj.TitielAr.get(i);
                        String AuthorName=(String) obj.AuthorAr.get(i);
                        String Image=(String) obj.ImageAr.get(i);
                        String Rating=(String) obj.Avg_Rating.get(i);
                        // String Isbn=(String) obj.Isbn.get(i+1);
                        intent.putExtra("Titles",Titles);
                        intent.putExtra("AuthorName",AuthorName);
                        intent.putExtra("Image",Image);
                        intent.putExtra("Rating",Rating);
                        startActivity(intent);

                        Dialog.hide();
                    }
                });


            }




        }
        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog
            Dialog.setMessage("Please wait...");
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(String... text) {
            // Things to be done while execution of long running operation is in
            // progress. For example updating ProgessDialog
        }
    }
    private class AsyncTaskRunner2 extends AsyncTask<String, String, String> {
        ProgressDialog Dialog = new ProgressDialog(MainParse.this);
        final Intent intent = new Intent(getApplicationContext(),ShowList.class);
        int i;
        private String resp;
        @Override
        protected String doInBackground(String... params) {

            // String Isbn=(String) obj.Isbn.get(i+1);

            return " ";

        }

        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            //spinner.setVisibility(View.GONE);
            Dialog.dismiss();
        }
        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog
            Dialog.setMessage("Please wait...");
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(String... text) {
            // Things to be done while execution of long running operation is in
            // progress. For example updating ProgessDialog
        }
    }
}
