package com.books.saumy.books;

import android.annotation.SuppressLint;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Saumy on 12-05-2015.
 */
public class HandleJSON {
    ArrayList source = new ArrayList();
    //String[] source1 = new String[10];
    ArrayList price = new ArrayList();
    ArrayList url = new ArrayList();
    /*private String title = "title";
    private String author = "author";
    private String publisher = "publisher";
    private String description = "description";*/
    private String urlString = null;
    public  HandleJSON(){}
    public volatile boolean parsingComplete = true;
    public HandleJSON(String url){
        this.urlString = url;
    }
   /* public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getPublisher(){
        return publisher;
    }
    public String getDescription(){
        return description;
    }*/
    //public String getSource(int i) { return source.get(i);}

    @SuppressLint("NewApi")
    public void readAndParseJSON(String in) {
        try {
            JSONObject jsonObj = new JSONObject(in);
            String str=jsonObj.getString("data");
            JSONArray jsonarray = new JSONArray(str);


            for(int i=0; i<jsonarray.length(); i++) {
                JSONObject obj = jsonarray.getJSONObject(i);
                //source1[i]=obj.getString("source");
                source.add(obj.getString("source"));
                price.add(obj.getString("price"));
                url.add(obj.getString("url"));

//            title = jsonObj.getString("status_code");
//            author = jsonObj.getString("status_text");
//            publisher = jsonObj.getString("count");
                //description = jsonObj.getString("description");
            }
           /* title = source1[0];
            author = price.get(1).toString();
            publisher = source.get(1).toString();
            description = price.get(1).toString();*/
            parsingComplete = false;



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void fetchJSON(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(70000 /* milliseconds */);
                    conn.setConnectTimeout(90000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    String data = convertStreamToString(stream);

                    readAndParseJSON(data);
                    stream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
