package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class search extends AppCompatActivity {

    private RecipeDatabaseHelper dbHelper;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.search);

        // RecipeDatabaseHelper 초기화
        dbHelper = new RecipeDatabaseHelper(this);

        // RecyclerView 초기화
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecipeAdapter(null);
        recyclerView.setAdapter(adapter);

        // 검색 EditText 초기화
        EditText searchInput = findViewById(R.id.foodName);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchRecipes(s.toString()); // 검색 수행
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        // 네비게이션 버튼 설정
        setupNavigationBar();
    }

    /**
     * 레시피를 검색하고 RecyclerView를 업데이트.
     *
     * @param query 검색어
     */
    private void searchRecipes(String query) {
        // DB에서 검색 수행
        List<Recipe> recipes = dbHelper.searchRecipes(query);
        if (recipes.isEmpty()) {
            Toast.makeText(this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setRecipes(recipes); // 검색 결과 RecyclerView에 표시
        }
    }

    /**
     * 추천 채널 카드뷰 초기화.
     */


    /**
     * YouTube 링크 열기.
     *
     * @param url YouTube URL
     */
    private void openYouTube(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    /**
     * 네비게이션 버튼 설정.
     */
    private void setupNavigationBar() {
        // 홈 버튼
        ImageButton btnHome = findViewById(R.id.btnNav3);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(search.this, MainActivity.class);
            startActivity(intent);
        });

        // 유통기한 버튼
        ImageButton btnDayLimit = findViewById(R.id.btnNav1);
        btnDayLimit.setOnClickListener(v -> {
            Intent intent = new Intent(search.this, daylimit.class);
            startActivity(intent);
        });

        // 레시피 추천 버튼 (현재 활동)
        ImageButton btnSearch = findViewById(R.id.btnNav2);
        btnSearch.setOnClickListener(v -> {
            // 현재 활동에 머물기
        });

        // 내 냉장고 버튼
        ImageButton btnMyFridge = findViewById(R.id.btnNav4);
        btnMyFridge.setOnClickListener(v -> {
            Intent intent = new Intent(search.this, page2.class);
            startActivity(intent);
        });
    }
}
