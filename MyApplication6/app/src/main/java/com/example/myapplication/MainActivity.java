package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private CardView menu1, menu2, menu3, menu4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        // 카드뷰 연결
        menu1 = findViewById(R.id.menu1);
        menu2 = findViewById(R.id.menu2);
        menu3 = findViewById(R.id.menu3);
        menu4 = findViewById(R.id.menu4);

        // 각 카드뷰에 클릭 리스너 추가
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Page2.java로 이동
                Intent intent = new Intent(MainActivity.this, page2.class);
                startActivity(intent);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Search.java로 이동
                Intent intent = new Intent(MainActivity.this, search.class);
                startActivity(intent);
            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DayLimit.java로 이동
                Intent intent = new Intent(MainActivity.this, daylimit.class);
                startActivity(intent);
            }
        });

        // 설정 버튼의 클릭 리스너를 제거
        menu4.setOnClickListener(null);
    }
}