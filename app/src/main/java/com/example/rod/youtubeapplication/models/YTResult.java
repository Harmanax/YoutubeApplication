package com.example.rod.youtubeapplication.models;

import android.graphics.Bitmap;
import android.util.ArrayMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Array;
import java.util.HashMap;


public class YTResult {
    private String title;
    private String author;
    private String descr;
    private String publi;
    private String url;
    private Bitmap thumb;
    private String video;

    public YTResult(String pTitle, String pAuthor, String pDescr, String pPubli, String pUrl, String pVideo) {
        title = pTitle;
        author = pAuthor;
        descr = pDescr;
        publi = pPubli;
        url = pUrl;
        video = pVideo;
    }

    public String toJson() {
        HashMap map = new HashMap();
        map.put("title", title);
        map.put("video", video);
        map.put("author", author);
        map.put("descr", descr);
        map.put("publi", publi);
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescr() {
        return descr;
    }

    public String getVideo() {
        return video;
    }

    public String getPubli() {
        return publi;
    }


    public String getUrl() {
        return url;
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
    }


}
