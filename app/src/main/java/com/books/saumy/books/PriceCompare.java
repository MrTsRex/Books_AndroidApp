package com.books.saumy.books;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class PriceCompare extends ActionBarActivity {
    private  HandleJSON obj;
    int i;
    private  ShowList n;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_compare);
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();

        String url1 = "http://api.dataweave.in/v1/book_search/searchByIsbn/?api_key=71e1c9e20764f02fca032ae08a287e9b5dc5f5dc&isbn=";

        String url = ShowList.isbn13;
        String finalUrl = url1 + url ;

        final HandleJSON obj = new HandleJSON(finalUrl);
        obj.fetchJSON();
        while(obj.parsingComplete);
        ListView listView=(ListView)findViewById(R.id.listView2);
        ArrayList<HashMap<String, String>> feedList= new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        String b="shit";

        for(int i=0;i<obj.source.size();i++)
        {
            map = new HashMap<String, String>();
            map.put("source", (String) obj.source.get(i));
            map.put("price", (String) obj.price.get(i));
            b=(String)obj.url.get(i);

            feedList.add(map);//This line adds the content in the list
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, feedList, R.layout.activity_list_viewcolumn, new String[]{"source", "price"}, new int[]{R.id.TextFirst,R.id.TextThird});

            listView.setAdapter(simpleAdapter);//Displays the list

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url=(String)obj.url.get(i);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_price_compare, menu);
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
            intent.putExtra("key","PC");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
