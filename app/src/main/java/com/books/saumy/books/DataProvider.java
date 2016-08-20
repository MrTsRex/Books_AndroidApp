package com.books.saumy.books;

import android.graphics.Bitmap;

/**
 * Created by Saumy on 23-04-2015.
 */
public class DataProvider {
    private String book;
    private String author;

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    private Bitmap bmp;

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

   /* public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }*/

    public DataProvider(Bitmap bmp,String book, String author){
        this.book=book;
        this.author=author;
        this.bmp=bmp;
        //this.genre=genre;

    }
}
