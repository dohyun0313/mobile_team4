package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.widget.LinearLayout;

public class daylimit extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.daylimit); // XML 레이아웃 파일 연결

        dbHelper = new DatabaseHelper(this);
        gridLayout = findViewById(R.id.gridLayout); // XML에서 GridLayout ID 연결

        // 데이터 표시
        displayIngredients();

        // 네비게이션 버튼 설정
        setupNavigationButtons();
    }

    private void displayIngredients() {
        // DB에서 데이터를 가져옴
        Cursor cursor = dbHelper.getAllIngredients();

        // 기존 아이템 제거 (중복 방지)
        gridLayout.removeAllViews();

        while (cursor.moveToNext()) {
            // DB 데이터 읽기
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            String expiration = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXPIRATION));
            int imageResId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_IMAGE));

            // 1. 카드뷰 생성
            CardView cardView = new CardView(this);
            cardView.setRadius(15); // 모서리 둥글게 처리
            cardView.setCardElevation(8); // 그림자 효과 추가

            GridLayout.LayoutParams cardParams = new GridLayout.LayoutParams();
            cardParams.width = 400; // 카드뷰 가로 크기
            cardParams.height = 400; // 카드뷰 세로 크기
            cardParams.setMargins(16, 16, 16, 16); // 카드뷰 마진
            cardParams.setGravity(Gravity.CENTER); // 중앙 정렬
            cardView.setLayoutParams(cardParams);

            // 카드뷰 내부 레이아웃
            LinearLayout cardLayout = new LinearLayout(this);
            cardLayout.setOrientation(LinearLayout.VERTICAL);
            cardLayout.setPadding(16, 16, 16, 16);
            cardLayout.setBackgroundColor(getResources().getColor(android.R.color.white)); // 배경색 설정

            // 2. 이미지 생성
            ImageView itemImage = new ImageView(this);
            itemImage.setImageResource(imageResId);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    200
            );
            imageParams.setMargins(0, 0, 0, 16); // 여백 설정
            itemImage.setLayoutParams(imageParams);

            // 3. 이름 텍스트 생성
            TextView itemName = new TextView(this);
            itemName.setText(name);
            itemName.setTextSize(18);
            itemName.setTextColor(getResources().getColor(android.R.color.black));
            itemName.setGravity(Gravity.CENTER); // 텍스트 중앙 정렬

            // 4. 유통기한 텍스트 생성
            TextView expirationDate = new TextView(this);
            expirationDate.setText(expiration);
            expirationDate.setTextSize(14);
            expirationDate.setTextColor(getResources().getColor(android.R.color.black));
            expirationDate.setGravity(Gravity.CENTER); // 텍스트 중앙 정렬

            // 레이아웃에 추가
            cardLayout.addView(itemImage);
            cardLayout.addView(itemName);
            cardLayout.addView(expirationDate);

            // 카드뷰에 레이아웃 추가
            cardView.addView(cardLayout);

            // GridLayout에 카드뷰 추가
            gridLayout.addView(cardView);
        }

        cursor.close();
    }

    private void setupNavigationButtons() {
        ImageButton btnHome = findViewById(R.id.btnNav3);
        ImageButton btnDayLimit = findViewById(R.id.btnNav1);
        ImageButton btnSearch = findViewById(R.id.btnNav2);
        ImageButton btnMyFridge = findViewById(R.id.btnNav4);

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(daylimit.this, MainActivity.class);
            startActivity(intent);
        });

        btnDayLimit.setOnClickListener(v -> {
            Intent intent = new Intent(daylimit.this, daylimit.class);
            startActivity(intent);
        });

        btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(daylimit.this, search.class);
            startActivity(intent);
        });

        btnMyFridge.setOnClickListener(v -> {
            Intent intent = new Intent(daylimit.this, page2.class);
            startActivity(intent);
        });
    }
}
