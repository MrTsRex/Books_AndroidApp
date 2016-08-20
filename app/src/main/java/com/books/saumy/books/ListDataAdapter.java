package com.books.saumy.books;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saumy on 23-04-2015.
 */
public class ListDataAdapter extends ArrayAdapter{// implements //View.OnClickListener {
    List list=new ArrayList();
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }
    static class LayoutHandler{
        TextView BOOK,AUTHOR;
        ImageView Poster;
    }
    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutHandler layoutHandler;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler=new LayoutHandler();
            layoutHandler.BOOK=(TextView)row.findViewById(R.id.bookname);
            //layoutHandler.BOOK.setOnClickListener(this);
            layoutHandler.AUTHOR=(TextView)row.findViewById(R.id.authorname);
            //layoutHandler.GENRE=(TextView)row.findViewById(R.id.genres);
            layoutHandler.Poster=(ImageView)row.findViewById(R.id.book_cover);
            row.setTag(layoutHandler);
        }
        else{
            layoutHandler=(LayoutHandler)row.getTag();
        }
        DataProvider dataProvider=(DataProvider)this.getItem(position);
        layoutHandler.Poster.setImageBitmap(dataProvider.getBmp());
        layoutHandler.BOOK.setText(dataProvider.getBook());
        layoutHandler.AUTHOR.setText(dataProvider.getAuthor());
        //layoutHandler.GENRE.setText(dataProvider.getGenre());
        return row;

    }

   //@Override
    //public void onClick(View v ) {
        //Intent intent =new Intent(v.getContext(),settings.class);
       //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //v.getContext().startActivity(intent);
    //}
}
