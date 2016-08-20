package com.books.saumy.books;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddBookActivity extends ActionBarActivity {
    EditText e_book, e_author, e_genre;
    Context context = this;
    BookHelper bookhelper;
    SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        e_book = (EditText) findViewById(R.id.book_title);
        e_author = (EditText) findViewById(R.id.author_name);
        e_genre = (EditText) findViewById(R.id.genre);
    }

    public void addBook(View view) {
        String book = e_book.getText().toString();
        String author = e_author.getText().toString();
        String genre = e_genre.getText().toString();
        bookhelper = new BookHelper(context);
        sql = bookhelper.getWritableDatabase();
        bookhelper.addInfo(book, author, genre,"this book is awesome!", sql);
        Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_LONG).show();
        bookhelper.close();
    }
}


