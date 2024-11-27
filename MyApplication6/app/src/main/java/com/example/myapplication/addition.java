package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class addition extends AppCompatActivity {

    EditText foodName, day, memo;
    Button btnCancel, btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.addition); // XML 파일명에 맞게 설정

        // EditText 참조
        foodName = findViewById(R.id.foodName);
        day = findViewById(R.id.day);
        memo = findViewById(R.id.memo);

        // Button 참조
        btnCancel = findViewById(R.id.btnCancel);
        btnAdd = findViewById(R.id.btnAdd);

        // 취소 버튼 이벤트 리스너 설정
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 액티비티 종료
                finish();
            }
        });

        // 추가 버튼 이벤트 리스너 설정
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력 데이터 처리 로직 추가 예정
                // 예: 데이터베이스에 저장, SharedPreferences에 저장 등
                String food = foodName.getText().toString();
                String expiration = day.getText().toString();
                String note = memo.getText().toString();

                // 저장 로직은 추가적으로 필요한 곳에 구현
                // 예: saveData(food, expiration, note);

                // 액티비티 종료 및 결과 반환
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "Item Added");
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    // 추가적으로 필요한 데이터 저장 메소드 정의
    private void saveData(String food, String expiration, String note) {
        // 여기에 데이터 저장 로직 구현
        // 예: 데이터베이스, SharedPreferences 등
    }
}