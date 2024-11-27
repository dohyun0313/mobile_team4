package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class daylimit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.daylimit); // XML 레이아웃 파일 연결

        // 홈 버튼 (액티비티 이동 포함)
        ImageButton btnHome = findViewById(R.id.btnNav3);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity로 이동
                Intent intent = new Intent(daylimit.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // 유통기한 버튼 (현재 액티비티 재확인)
        ImageButton btnDayLimit = findViewById(R.id.btnNav1);
        btnDayLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 액티비티에 머무르기
                // 다른 액션 필요 없음
            }
        });

        // 레시피 추천 버튼
        ImageButton btnSearch = findViewById(R.id.btnNav2);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Search 액티비티로 이동
                Intent intent = new Intent(daylimit.this, search.class);
                startActivity(intent);
            }
        });

        // 내 냉장고 버튼
        ImageButton btnMyFridge = findViewById(R.id.btnNav4);
        btnMyFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Page2 액티비티로 이동
                Intent intent = new Intent(daylimit.this, page2.class);
                startActivity(intent);
            }
        });
    }
}