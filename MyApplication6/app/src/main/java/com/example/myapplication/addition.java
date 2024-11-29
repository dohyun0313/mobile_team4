package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addition extends AppCompatActivity {

    // UI 요소
    private EditText foodName, day, memo;
    private Button btnCancel, btnAdd;

    // 전달받는 카테고리 및 DatabaseHelper
    private String category;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addition);

        // DatabaseHelper 초기화
        dbHelper = new DatabaseHelper(this);

        // UI 요소 초기화
        foodName = findViewById(R.id.foodName);
        day = findViewById(R.id.day);
        memo = findViewById(R.id.memo);
        btnCancel = findViewById(R.id.btnCancel);
        btnAdd = findViewById(R.id.btnAdd);

        // Intent로 전달받은 데이터 처리
        category = getIntent().getStringExtra("category");
        if (category == null) {
            category = "기타"; // 기본값 설정
        }

        // 취소 버튼 클릭 이벤트
        btnCancel.setOnClickListener(v -> finish());

        // 추가 버튼 클릭 이벤트
        btnAdd.setOnClickListener(v -> handleAddIngredient());
    }

    /**
     * 재료 추가 로직 처리
     */
    private void handleAddIngredient() {
        // 입력 값 가져오기
        String name = foodName.getText().toString().trim();
        String expiration = day.getText().toString().trim();
        String note = memo.getText().toString().trim();

        // 필수 입력값 확인
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(expiration)) {
            Toast.makeText(this, "유통기한을 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 카테고리별 기본 이미지 리소스 선택
        int imageResource = getDefaultImageResourceForCategory(category);

        if (imageResource == 0) {
            Toast.makeText(this, "유효한 이미지 리소스를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 데이터베이스에 저장
        try {
            dbHelper.insertIngredient(name, expiration, note, category, imageResource);
            Toast.makeText(this, "재료가 추가되었습니다.", Toast.LENGTH_SHORT).show();
            finish(); // 액티비티 종료
        } catch (Exception e) {
            Toast.makeText(this, "저장 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 카테고리별 기본 이미지 리소스 반환
     *
     * @param category 재료 카테고리
     * @return 해당 카테고리의 기본 이미지 리소스 ID
     */
    private int getDefaultImageResourceForCategory(String category) {
        switch (category) {
            case "채소":
                return R.drawable.ic_carrot; // 채소 기본 이미지
            case "육류":
                return R.drawable.ic_meat; // 육류 기본 이미지
            case "유제품":
                return R.drawable.ic_you; // 유제품 기본 이미지
            default:
                return R.drawable.ic_carrot; // 기타 기본 이미지
        }
    }
}
