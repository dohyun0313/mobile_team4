package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class page2 extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 액션바 숨기기
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.page2);

        dbHelper = new DatabaseHelper(this);

        // 채소 추가 버튼
        ImageButton addVegetable = findViewById(R.id.imageButton);
        addVegetable.setOnClickListener(v -> openAdditionPage("채소"));

        // 육류 추가 버튼
        ImageButton addMeat = findViewById(R.id.imageButton2);
        addMeat.setOnClickListener(v -> openAdditionPage("육류"));

        // 유제품 추가 버튼
        ImageButton addDairy = findViewById(R.id.imageButton3);
        addDairy.setOnClickListener(v -> openAdditionPage("유제품"));

        // 카테고리별 재료 표시
        GridLayout vegetableGrid = findViewById(R.id.vegItemsGrid);
        displayCategoryItems("채소", vegetableGrid);

        GridLayout meatGrid = findViewById(R.id.meatItemsGrid);
        displayCategoryItems("육류", meatGrid);

        GridLayout dairyGrid = findViewById(R.id.dairyItemsGrid);
        displayCategoryItems("유제품", dairyGrid);

        // 하단 네비게이션 버튼 설정
        setupNavigationButtons();
    }

    private void openAdditionPage(String category) {
        Intent intent = new Intent(this, addition.class);
        intent.putExtra("category", category); // 카테고리 전달
        startActivity(intent);
    }

    private void displayCategoryItems(String category, GridLayout gridLayout) {
        Cursor cursor = dbHelper.getIngredientWithImageByCategory(category);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            int imageResId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_IMAGE)); // 리소스 ID 가져오기

            // 이미지 아이콘 동적 생성
            ImageView itemImage = new ImageView(this);
            itemImage.setImageResource(imageResId); // 리소스 ID로 이미지 설정

            // 이미지 크기 조정 (100dp x 100dp)
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    getResources().getDimensionPixelSize(R.dimen.grid_image_size),
                    getResources().getDimensionPixelSize(R.dimen.grid_image_size)
            );
            layoutParams.setMargins(8, 8, 8, 8); // 여백 설정
            itemImage.setLayoutParams(layoutParams);

            // GridLayout에 이미지 추가
            gridLayout.addView(itemImage);

            // 이름 텍스트 동적 생성
            TextView itemName = new TextView(this);
            itemName.setText(name);

            // 텍스트 스타일 조정
            GridLayout.LayoutParams textLayoutParams = new GridLayout.LayoutParams();
            textLayoutParams.setMargins(8, 8, 8, 8); // 여백 설정
            itemName.setLayoutParams(textLayoutParams);

            // GridLayout에 텍스트 추가
            gridLayout.addView(itemName);
        }
        cursor.close();
    }

    private void setupNavigationButtons() {
        ImageButton btnHome = findViewById(R.id.btnNav3);
        ImageButton btnDayLimit = findViewById(R.id.btnNav1);
        ImageButton btnSearch = findViewById(R.id.btnNav2);
        ImageButton btnMyFridge = findViewById(R.id.btnNav4);

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(page2.this, MainActivity.class);
            startActivity(intent);
        });

        btnDayLimit.setOnClickListener(v -> {
            Intent intent = new Intent(page2.this, daylimit.class);
            startActivity(intent);
        });

        btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(page2.this, search.class);
            startActivity(intent);
        });

        btnMyFridge.setOnClickListener(v -> {
            Intent intent = new Intent(page2.this, page2.class);
            startActivity(intent);
        });
    }
}
