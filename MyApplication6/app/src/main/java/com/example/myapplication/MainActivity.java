package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // 로그를 사용하기 위한 import
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

        // 로그를 찍어 초기화가 제대로 되었는지 확인
        if (menu1 != null) Log.d("MainActivity", "menu1 initialized");
        if (menu2 != null) Log.d("MainActivity", "menu2 initialized");
        if (menu3 != null) Log.d("MainActivity", "menu3 initialized");
        if (menu4 != null) Log.d("MainActivity", "menu4 initialized");

        // 각 카드뷰에 클릭 리스너 추가
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Page2.java로 이동
                Log.d("MainActivity", "menu1 clicked: Navigating to page2");
                Intent intent = new Intent(MainActivity.this, page2.class);
                startActivity(intent);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Search.java로 이동
                Log.d("MainActivity", "menu2 clicked: Navigating to search");
                Intent intent = new Intent(MainActivity.this, search.class);
                startActivity(intent);
            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DayLimit.java로 이동
                Log.d("MainActivity", "menu3 clicked: Navigating to daylimit");
                Intent intent = new Intent(MainActivity.this, daylimit.class);
                startActivity(intent);
            }
        });

        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "menu4 clicked: Navigating to information");
                Intent intent = new Intent(MainActivity.this, information.class);
                startActivity(intent);
            }
        });


    }
}
