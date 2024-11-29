package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class page2 extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private GridLayout vegetableGrid, meatGrid, dairyGrid;

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

        // GridLayout 초기화
        vegetableGrid = findViewById(R.id.vegItemsGrid);
        meatGrid = findViewById(R.id.meatItemsGrid);
        dairyGrid = findViewById(R.id.dairyItemsGrid);

        // 하단 네비게이션 버튼 설정
        setupNavigationButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 페이지로 돌아올 때 GridLayout을 갱신
        refreshCategoryItems();
    }

    private void openAdditionPage(String category) {
        Intent intent = new Intent(this, addition.class);
        intent.putExtra("category", category); // 카테고리 전달
        startActivity(intent);
    }

    private void refreshCategoryItems() {
        // 기존 GridLayout 초기화
        vegetableGrid.removeAllViews();
        meatGrid.removeAllViews();
        dairyGrid.removeAllViews();

        // 카테고리별 데이터 다시 로드
        displayCategoryItems("채소", vegetableGrid);
        displayCategoryItems("육류", meatGrid);
        displayCategoryItems("유제품", dairyGrid);
    }

    private void displayCategoryItems(String category, GridLayout gridLayout) {
        Cursor cursor = dbHelper.getIngredientsByCategory(category);

        gridLayout.setColumnCount(4); // 한 행에 4개 배치


        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            int imageResId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_IMAGE));
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)); // ID 가져오기

            // 이미지 생성
            ImageView itemImage = new ImageView(this);
            itemImage.setImageResource(imageResId);

            // 이미지 크기 및 여백 설정
            GridLayout.LayoutParams imageParams = new GridLayout.LayoutParams();
            imageParams.width = 150; // 고정 크기
            imageParams.height = 150; // 고정 크기
            imageParams.setMargins(16, 16, 16, 16);
            itemImage.setLayoutParams(imageParams);

            itemImage.setBackgroundColor(Color.parseColor("#D3D3D3")); // 밝은 회색 배경 예시
            itemImage.setPadding(10, 10, 10, 10); // 패딩 추가 (이미지가 배경에서 분리되어 보이도록)

            // GridLayout에 이미지 추가
            gridLayout.addView(itemImage);

            // 이름 텍스트 생성
            TextView itemName = new TextView(this);
            itemName.setText(name);
            itemName.setTextSize(14); // 텍스트 크기
            itemName.setGravity(android.view.Gravity.CENTER);

            // 텍스트 레이아웃 설정
            GridLayout.LayoutParams textParams = new GridLayout.LayoutParams();
            textParams.setMargins(16, 4, 16, 16);
            itemName.setLayoutParams(textParams);

            // GridLayout에 텍스트 추가
            gridLayout.addView(itemName);

            // **길게 눌러 삭제 이벤트 추가**
            itemImage.setOnLongClickListener(v -> {
                showDeleteConfirmationDialog(itemId, gridLayout, itemImage, itemName);
                return true;
            });
        }
        cursor.close();
    }

    private void showDeleteConfirmationDialog(int itemId, GridLayout gridLayout, ImageView itemImage, TextView itemName) {
        new AlertDialog.Builder(this)
                .setTitle("삭제 확인")
                .setMessage("이 항목을 삭제하시겠습니까?")
                .setPositiveButton("삭제", (dialog, which) -> {
                    // 데이터베이스에서 삭제
                    dbHelper.deleteIngredient(itemId);

                    // GridLayout에서 제거
                    gridLayout.removeView(itemImage);
                    gridLayout.removeView(itemName);

                    Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("취소", null)
                .show();
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
