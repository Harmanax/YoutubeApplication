package com.example.rod.youtubeapplication.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.rod.youtubeapplication.R;
import com.google.gson.Gson;

import android.net.Uri;

import org.json.JSONObject;

import java.util.HashMap;


public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        String json = getIntent().getStringExtra("yt");

        WebView webView = (WebView) findViewById(R.id.webView);
        try {
            Gson gson = new Gson();
            HashMap map = gson.fromJson(json, HashMap.class);

            ((TextView) findViewById(R.id.vTitle)).setText((String)map.get("title"));
            ((TextView) findViewById(R.id.vPubli)).setText((String)map.get("publi"));
            ((TextView) findViewById(R.id.vAuthor)).setText((String)map.get("author"));
            ((TextView) findViewById(R.id.vDescr)).setText((String)map.get("descr"));
            String video = (String) map.get("video");
            String html = "<html><body><iframe width='320' height='200' "
                    + "src='https://www.youtube.com/embed/" + video +
                    "' frameborder='0' allowfullscreen></iframe></body></html>";

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.loadData(html, "text/html", "utf-8");
        } catch (Exception ex) {

        }
    }


}
