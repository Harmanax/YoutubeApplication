package com.example.rod.youtubeapplication.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    private YTResult ytr;
    private ImageView img;

    public ImageLoadTask(YTResult pYtr) {
        ytr = pYtr;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        return download_Image(ytr.getUrl());
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) ytr.setThumb(result);

    }

    private Bitmap download_Image(String url) {
        Bitmap bmp = null;
        try {
            URL u = new URL(url);
            URLConnection con = u.openConnection();
            InputStream is = con.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            bmp = null;
        }
        return bmp;
    }
}