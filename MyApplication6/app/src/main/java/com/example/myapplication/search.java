package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        // 첫 번째 추천 채널 (백종원)
        CardView recommend1 = findViewById(R.id.recommend1);
        recommend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTube("https://www.youtube.com/@paik_jongwon");
            }
        });

        // 두 번째 추천 채널 (자취요리신)
        CardView recommend2 = findViewById(R.id.recommend2);
        recommend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTube("https://www.youtube.com/@%EC%9E%90%EC%B7%A8%EC%9A%94%EB%A6%AC%EC%8B%A0simplecooking");
            }
        });

        // 세 번째 추천 채널 (수리키친)
        CardView recommend3 = findViewById(R.id.recommend3);
        recommend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTube("https://www.youtube.com/@suri4095");
            }
        });

        // 네 번째 추천 채널 (1분요리)
        CardView recommend4 = findViewById(R.id.recommend4);
        recommend4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTube("https://www.youtube.com/@1mincook");
            }
        });
    }

    /**
     * Helper method to open a YouTube URL.
     *
     * @param url The URL of the YouTube channel or video.
     */
    private void openYouTube(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}



