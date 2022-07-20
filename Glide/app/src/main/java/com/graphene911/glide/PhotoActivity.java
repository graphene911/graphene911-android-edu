package com.graphene911.glide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.graphene911.glide.adapter.PhotoAdapter;

public class PhotoActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        String imgUrl = getIntent().getStringExtra("url");

        imageView = findViewById(R.id.imageView);

        // 이미지를 가져올땐 글라이드 라이브러리를 사용
        GlideUrl url = new GlideUrl(imgUrl, new LazyHeaders.Builder().addHeader("User-Agent","Android").build());
        Glide.with(PhotoActivity.this).load(url).placeholder(R.drawable.ic_default).into(imageView);
    }
}