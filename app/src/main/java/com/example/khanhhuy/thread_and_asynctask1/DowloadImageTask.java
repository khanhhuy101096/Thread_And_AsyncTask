package com.example.khanhhuy.thread_and_asynctask1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by yeu_thuong on 10/15/2017.
 */

public class DowloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView mImageView;

    public DowloadImageTask(ImageView imageView) {
        this.mImageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap mBitmap = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mBitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        mImageView.setImageBitmap(bitmap);
    }
}

