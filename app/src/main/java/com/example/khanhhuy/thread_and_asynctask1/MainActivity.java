package com.example.khanhhuy.thread_and_asynctask1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageView;
    private TextView textTest;
    private Handler handler;
    private Button buttonStart;

    private int i = 0;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.image_view);
        textTest = (TextView) findViewById(R.id.text_test);
        buttonStart = (Button) findViewById(R.id.button_start);
        handler = new Handler();
    }

    public void startAsyncTask(View view) {
        new DowloadImageTask((ImageView) findViewById(R.id.image_view)).
                execute("http://file.vforum.vn/hinh/2016/08/nhung-anh-dep-nhat-ve-con-ga-3.jpg");
    }

    /*
     * Ví dụ dưới đây là một số mã cho trình lắng nghe nhấp chuột tải một hình ảnh từ một
        thread riêng biệt và hiển thị nó trong một ImageView:
     **/

    public void startThread(View view) {
        new Thread(new Runnable() {
            public void run() {
                final Bitmap bitmap = loadImageFromNetwork("http://1.bp.blogspot.com/-B9Pm3fjiD-A/U0n6nmk5WAI/AAAAAAAABl4/bKXvv1umYRU/s1600/anh+dep+avatar+10.jpg");
                mImageView.post(new Runnable() {
                    public void run() {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();
    }

    private Bitmap loadImageFromNetwork(String url) {
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

    /*
    * Thay doi text sau 5s
    * */

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textTest.setText("Hello World");
            }
        }, 5000);
    }

    public void runThread(final View view) {
        new Thread() {
            public void run() {
                while (i++ < 1000) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonStart.setText("Số "+i);
                            }
                        });
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
