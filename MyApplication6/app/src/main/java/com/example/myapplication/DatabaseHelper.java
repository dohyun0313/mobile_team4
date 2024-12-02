package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // 데이터베이스 정보
    private static final String DATABASE_NAME = "FridgeDB.db";
    private static final int DATABASE_VERSION = 3; // 버전 증가

    // 테이블 및 컬럼 이름
    public static final String TABLE_NAME = "ingredients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EXPIRATION = "expiration";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_IMAGE = "image"; // 이미지 리소스 ID 저장

    // 생성자
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 테이블 생성 SQL
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_EXPIRATION + " TEXT NOT NULL, " +
                COLUMN_CATEGORY + " TEXT NOT NULL, " +
                COLUMN_IMAGE + " INTEGER NOT NULL)"; // INTEGER로 이미지 리소스 ID 저장
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 테이블 초기화
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // 데이터 삽입 메서드 (이미지 리소스 ID 포함)
    public void insertIngredient(String name, String expiration, String category, int imageResId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EXPIRATION, expiration);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_IMAGE, imageResId);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // 특정 카테고리별 데이터 조회
    public Cursor getIngredientsByCategory(String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY + " = ?", new String[]{category});
    }

    // 모든 데이터 조회
    public Cursor getAllIngredients() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // 데이터 초기화 (모든 데이터 삭제)
    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }

    // 데이터 삭제 (특정 재료 ID로 삭제)
    public void deleteIngredient(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }


    // 특정 재료 업데이트
    public void updateIngredient(int id, String name, String expiration, String memo, String category, int imageResId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EXPIRATION, expiration);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_IMAGE, imageResId);
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
