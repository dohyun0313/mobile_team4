package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class page2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.page2);

        // 첫 번째 플러스 버튼에 대한 참조를 가져옴
        ImageButton imageButton1 = findViewById(R.id.imageButton);
        ImageButton imageButton2 = findViewById(R.id.imageButton2);
        ImageButton imageButton3 = findViewById(R.id.imageButton3);





        // 첫 번째 플러스 버튼에 클릭 리스너 설정
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // addition.class 액티비티로 이동
                Intent intent = new Intent(page2.this, addition.class);
                startActivity(intent);
            }
        });

        // 두 번째 플러스 버튼에 클릭 리스너 설정
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // addition.class 액티비티로 이동
                Intent intent = new Intent(page2.this, addition.class);
                startActivity(intent);
            }
        });

        // 세 번째 플러스 버튼에 클릭 리스너 설정
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // addition.class 액티비티로 이동
                Intent intent = new Intent(page2.this, addition.class);
                startActivity(intent);
            }
        });

        ImageButton btnHome = findViewById(R.id.btnNav3);
        ImageButton btnDayLimit = findViewById(R.id.btnNav1);
        ImageButton btnSearch = findViewById(R.id.btnNav2);
        ImageButton btnMyFridge = findViewById(R.id.btnNav4);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 홈 버튼 클릭 시 MainActivity로 이동
                Intent intent = new Intent(page2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDayLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 유통기한 버튼 클릭 시 DayLimit 액티비티로 이동
                Intent intent = new Intent(page2.this, daylimit.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 레시피 추천 버튼 클릭 시 Search 액티비티로 이동
                Intent intent = new Intent(page2.this, search.class);
                startActivity(intent);
            }
        });

        btnMyFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 내 냉장고 버튼 클릭 시 Page2 액티비티로 이동
                Intent intent = new Intent(page2.this, page2.class);
                startActivity(intent);
            }
        });
    }

}