package com.books.saumy.books;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class DataView extends SplitPanel {

    protected WebView view;
    protected String data;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)	{
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.activity_data_view, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle saved) {
        super.onActivityCreated(saved);
        view = (WebView) getView().findViewById(R.id.Viewport);

        view.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    navigator.setBookPage(url, index);
                } catch (Exception e) {
                    errorMessage(getString(R.string.error_LoadPage));
                }
                return true;
            }
        });

        loadData(data);
    }

    public void loadData(String source)
    {
        data=source;

        if(created)
            view.loadData(data, getActivity().getApplicationContext().getResources().getString(R.string.textOrHTML), null);
    }

    @Override
    public void saveState(SharedPreferences.Editor editor)
    {
        super.saveState(editor);
        editor.putString("data"+index, data);
    }

    @Override
    public void loadState(SharedPreferences preferences)
    {
        super.loadState(preferences);
        loadData(preferences.getString("data"+index, ""));
    }
}
