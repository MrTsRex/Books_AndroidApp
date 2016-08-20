package com.books.saumy.books;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class SplitPanel extends Fragment {

    private RelativeLayout generalLayout;
    protected int index;
    protected RelativeLayout layout;
    protected Button closeButton;
    protected EpubNavigator navigator;
    protected int screenWidth;
    protected int screenHeight;
    protected float weight = 0.5f; // weight of the generalLayout
    protected boolean created; // tells whether the fragment has been created

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navigator = ((MainActivity_epub) getActivity()).navigator;
        View v = inflater.inflate(R.layout.activity_split_panel, container,
                false);
        created = false;
        return v;
    }

    @Override
    public void onActivityCreated(Bundle saved) {
        created = true;
        super.onActivityCreated(saved);
        generalLayout = (RelativeLayout) getView().findViewById(
                R.id.GeneralLayout);
        layout = (RelativeLayout) getView().findViewById(R.id.Content);
        closeButton = (Button) getView().findViewById(R.id.CloseButton);

        // ----- get activity screen size
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        // -----

        changeWeight(weight);

        // ----- VIEW CLOSING
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeView();
            }
        });
    }

    protected void closeView() {
        navigator.closeView(index);
    }

    // change the weight of the general layout
    public void changeWeight(float value) {
        weight = value;
        if (created) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, value);
            generalLayout.setLayoutParams(params);
        }
    }

    public float getWeight() {
        return weight;
    }

    public void setKey(int value) {
        index = value;
    }

    public void errorMessage(String message) {
        ((MainActivity_epub) getActivity()).errorMessage(message);
    }

    public void saveState(SharedPreferences.Editor editor) {
        editor.putFloat("weight" + index, weight);
    }

    public void loadState(SharedPreferences preferences) {
        changeWeight(preferences.getFloat("weight" + index, 0.5f));
    }
}
