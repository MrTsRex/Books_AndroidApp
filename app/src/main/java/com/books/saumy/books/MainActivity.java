package com.books.saumy.books;
import android.content.Intent;
import android.os.Handler;
import java.lang.Runnable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends ActionBarActivity {
    Button b1;
    private AtomicInteger mCounter = new AtomicInteger();
    private Handler handler = new Handler();
    private Runnable mRunnable = new Runnable(){
        @Override
        public void run(){
            mCounter = new AtomicInteger();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.view_fav);
        ImageView myImage = (ImageView) findViewById(R.id.imageView3);
        addClickToImage(myImage);
    }
    public void addClickToImage(ImageView image){
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(mRunnable);
                handler.postDelayed(mRunnable, 300);
                if (mCounter.incrementAndGet() == 5) {
                    Toast.makeText(getApplicationContext(),"Don't Press Back. This activity will automatically close in 10 seconds.",Toast.LENGTH_SHORT).show();//Display your dialog fragment
                    Intent intent=new Intent(getApplicationContext(),easter.class);
                    startActivity(intent);
                }
            }
        });}

    public void buttonClick(View view){
        String button_text;
        ProgressBar spinner = (ProgressBar)findViewById(R.id.progressBar1);
        button_text=((Button) view).getText().toString();
        if (button_text.equals("View Favorites")){
            Intent intent=new Intent(this,ViewFavorites.class);
            startActivity(intent);
        }
    }
    public void btnClick2(View view){
        ProgressBar spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        Intent intent=new Intent(this,MainParse.class);
        startActivity(intent);
        spinner.setVisibility(View.GONE);
    }
    public void btnClick(View view){
        ProgressBar spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        Intent intent=new Intent(this,MainActivity_epub.class);
        startActivity(intent);
        spinner.setVisibility(View.GONE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            intent.putExtra("key","MAIN");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
