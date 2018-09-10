package org.skidang.question;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;

    BitmapDrawable bitmap;

    ScrollView scollView;
    ScrollView scollView2;
    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // imageView 가져오기
        imageView = (ImageView) findViewById(R.id.imageView2);
        imageView2 = (ImageView) findViewById(R.id.imageView3);

        // scollView 가져오기. 스크롤 활성화
        scollView = (ScrollView) findViewById(R.id.scollView1);
        scollView2 = (ScrollView) findViewById(R.id.scollView2);
        scollView.setHorizontalScrollBarEnabled(true);
        scollView2.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.dream01);

        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().height = bitmapHeight;
        imageView.getLayoutParams().width = bitmapWidth;

        imageView2.setImageDrawable(bitmap);
        imageView2.getLayoutParams().height = bitmapHeight;
        imageView2.getLayoutParams().width = bitmapWidth;

        imageView2.setVisibility(View.INVISIBLE);
    }

    public void onButton1Clicked(View v){
        imageIndex = 0;
        imageChange();
    }

    public void onButton2Clicked(View v){
        imageIndex = 1;
        imageChange();
    }

    public void imageChange(){
        if(imageIndex ==0){
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
        }else if(imageIndex ==1){
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
        }
    }
}
