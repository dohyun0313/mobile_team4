package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

public class information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 액션바 숨기기
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.information);

        setupNavigationButtons();
    }

    private void setupNavigationButtons() {
        ImageButton btnHome = findViewById(R.id.btnNav3);
        ImageButton btnDayLimit = findViewById(R.id.btnNav1);
        ImageButton btnSearch = findViewById(R.id.btnNav2);
        ImageButton btnMyFridge = findViewById(R.id.btnNav4);

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(information.this, MainActivity.class);
            startActivity(intent);
        });

        btnDayLimit.setOnClickListener(v -> {
            Intent intent = new Intent(information.this, daylimit.class);
            startActivity(intent);
        });

        btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(information.this, search.class);
            startActivity(intent);
        });

        btnMyFridge.setOnClickListener(v -> {
            Intent intent = new Intent(information.this, page2.class);
            startActivity(intent);
        });
    }
}
