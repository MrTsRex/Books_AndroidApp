package com.books.saumy.books;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class settings extends ActionBarActivity {
    TextView textView;
    String str;
    String main = "This is the main activity of the application. You can search for a book online, go through the recommended books of every genre or search dynamically in the recommended books. You can also select the option to view your favorite books.";
    String snet="Here you can search for any book to get its information from the best and most trusted sites on the internet. Click on the search results to see the description and rating.";
    String pc = "You can see the price comparison of the book from the top book selling websites. You can touch on the price list to go to the buying link.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        textView=(TextView)findViewById(R.id.textView);
        Intent intent = getIntent();
        str=intent.getStringExtra("key");
        if(str.equals("MAIN")){
            textView.setText(main);
        }
        else if (str.equals("SNET")){
            textView.setText(snet);
        }
        else if (str.equals("PC")){
            textView.setText(pc);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
