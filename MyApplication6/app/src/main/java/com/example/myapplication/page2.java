package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        gridLayout.setColumnCount(5); // 한 행에 5개 배치

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            int imageResId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_IMAGE));
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)); // ID 가져오기

            // 그룹화된 레이아웃 생성 (이미지 + 텍스트)
            LinearLayout groupLayout = new LinearLayout(this);
            groupLayout.setOrientation(LinearLayout.VERTICAL); // 세로 방향
            groupLayout.setGravity(android.view.Gravity.CENTER); // 중앙 정렬

            // 그룹 레이아웃 여백 설정
            GridLayout.LayoutParams groupParams = new GridLayout.LayoutParams();
            groupParams.setMargins(16, 16, 16, 16);
            groupLayout.setLayoutParams(groupParams);

            // 이미지 생성
            ImageView itemImage = new ImageView(this);
            itemImage.setImageResource(imageResId);

            // 둥근 배경과 테두리 설정
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(30); // 둥근 모서리 반지름
            drawable.setColor(Color.WHITE); // 내부 배경색
            drawable.setStroke(4, Color.parseColor("#8AB4F8")); // 테두리 색상 및 두께
            itemImage.setBackground(drawable);
            itemImage.setClipToOutline(true); // 둥근 배경에 맞게 클리핑

            // 이미지 크기 설정
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(150, 150); // 크기 조정
            imageParams.setMargins(0, 0, 0, 8); // 이미지와 텍스트 간 간격
            itemImage.setLayoutParams(imageParams);

            // 그룹 레이아웃에 이미지 추가
            groupLayout.addView(itemImage);

            // 이름 텍스트 생성
            TextView itemName = new TextView(this);
            itemName.setText(name);
            itemName.setTextSize(14); // 텍스트 크기
            itemName.setGravity(android.view.Gravity.CENTER); // 텍스트 중앙 정렬

            // 텍스트 추가
            groupLayout.addView(itemName);

            // GridLayout에 그룹 추가
            gridLayout.addView(groupLayout);

            // **길게 눌러 삭제 이벤트 추가**
            groupLayout.setOnLongClickListener(v -> {
                showDeleteConfirmationDialog(itemId, gridLayout, groupLayout);
                return true;
            });
        }
        cursor.close();
    }

    private void showDeleteConfirmationDialog(int itemId, GridLayout gridLayout, LinearLayout groupLayout) {
        new AlertDialog.Builder(this)
                .setTitle("삭제 확인")
                .setMessage("이 항목을 삭제하시겠습니까?")
                .setPositiveButton("삭제", (dialog, which) -> {
                    // 데이터베이스에서 삭제
                    dbHelper.deleteIngredient(itemId);

                    // GridLayout에서 제거
                    gridLayout.removeView(groupLayout);

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
