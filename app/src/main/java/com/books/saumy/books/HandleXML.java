package com.books.saumy.books;

import android.content.Context;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Saumy on 12-05-2015.
 */
public class HandleXML {
    private String title = "title";
    private String author = "author";
    private String description = "description";
    private  String imageurl="imageurl";
    private String Isbn13 = "Isbn";
    private String rating = "rating";
    public String er=" ";

    //public String TitielAr[]=new String[100];
    ArrayList TitielAr = new ArrayList();
    ArrayList AuthorAr = new ArrayList();
    ArrayList ImageAr = new ArrayList();
    ArrayList Avg_Rating = new ArrayList();
    private String urlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;
    public HandleXML(String url){
        this.urlString = url;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String  getIsbn13(){return  Isbn13;}
    public String getImageUrl(){return imageurl;}
    public String getAuthor(){return author;}
    public String getRating(){return rating;}


    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text=null;
        String text2=null;
        String text3=null;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        text2=myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(name.equals("title")){
                            title = text;
                            //TitielAr[i]=title;
                            TitielAr.add(title);
                        }

                        else if(name.equals("name")){
                            author = text2;
                            AuthorAr.add(author);
                        }
                        else if(name.equals("image_url")){
                            imageurl =text;
                            ImageAr.add(imageurl);
                        }
                        else if(name.equals("description")){
                            description =text2;
                        }
                        else if(name.equals("isbn13")){
                            Isbn13 =text;
                        }
                        else if(name.equals("average_rating")){
                            rating =text2;
                            Avg_Rating.add(rating);
                        }
                        break;
                }
                event = myParser.next();

            }
            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection)
                            url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
                            , false);
                    myparser.setInput(stream, null);
                    parseXMLAndStoreIt(myparser);
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();


    }
}
