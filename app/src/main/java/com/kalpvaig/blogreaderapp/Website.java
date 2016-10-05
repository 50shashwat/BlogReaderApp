package com.kalpvaig.blogreaderapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by akshay on 5/10/16.
 */

public class Website extends AppCompatActivity {

    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.website);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        webView = (WebView) findViewById(R.id.website);
        webView.loadUrl(url);

    }

}
