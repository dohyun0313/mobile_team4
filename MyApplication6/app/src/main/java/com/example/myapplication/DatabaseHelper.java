package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageView;

public class DatabaseHelper extends SQLiteOpenHelper {

    // 데이터베이스 정보
    private static final String DATABASE_NAME = "FridgeDB.db";
    private static final int DATABASE_VERSION = 3; // 버전 증가

    // 테이블 및 컬럼 이름
    public static final String TABLE_NAME = "ingredients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EXPIRATION = "expiration";
    public static final String COLUMN_MEMO = "memo";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_IMAGE = "image"; // 리소스 ID 저장

    // 생성자
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 테이블 생성 SQL
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EXPIRATION + " TEXT, " +
                COLUMN_MEMO + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_IMAGE + " INTEGER)"; // INTEGER로 변경
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 기존 테이블 삭제 후 재생성
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // 데이터 삽입 메서드 (리소스 ID 사용)
    public void insertIngredientWithImage(String name, String expiration, String memo, String category, int imageResourceId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EXPIRATION, expiration);
        values.put(COLUMN_MEMO, memo);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_IMAGE, imageResourceId); // 리소스 ID 추가
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // 데이터 조회 메서드 (카테고리별 조회)
    public Cursor getIngredientWithImageByCategory(String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT " + COLUMN_NAME + ", " + COLUMN_IMAGE +
                        " FROM " + TABLE_NAME +
                        " WHERE " + COLUMN_CATEGORY + " = ?",
                new String[]{category}
        );
    }

    // 리소스 ID로 ImageView에 이미지 설정
    public void setImageFromResource(ImageView imageView, int resourceId) {
        imageView.setImageResource(resourceId);
    }

    // 예제 메서드: 특정 카테고리의 아이템 UI 설정
    public void displayIngredientsByCategory(String category, ImageView imageView) {
        Cursor cursor = getIngredientWithImageByCategory(category);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                int imageResourceId = cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE));

                // 이미지 설정
                setImageFromResource(imageView, imageResourceId);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
