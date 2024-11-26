package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        Spinner spinnerMode = findViewById(R.id.spinnerMode);

        // 배열 리소스와 연결된 어댑터 생성
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.mode_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 어댑터를 spinner에 설정
        spinnerMode.setAdapter(adapter);

        // Spinner의 기본값을 "냉장실"로 설정
        spinnerMode.setSelection(0);

        // 선택 이벤트 처리
        spinnerMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 선택된 아이템의 텍스트 가져오기
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, selectedItem + " 선택됨", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 선택된 항목이 없는 경우 처리
            }
        });
    }
}
