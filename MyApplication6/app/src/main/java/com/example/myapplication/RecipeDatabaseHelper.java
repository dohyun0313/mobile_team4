package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class RecipeDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recipes.db"; // 데이터베이스 파일명
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "RecipeDatabaseHelper";

    private final Context context;

    public RecipeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

        // DB 파일 복사
        copyDatabaseIfNeeded();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 외부 DB 사용, 테이블 생성 불필요
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // DB 업그레이드 로직
    }

    private void copyDatabaseIfNeeded() {
        try {
            String dbPath = context.getDatabasePath(DATABASE_NAME).getPath();
            File dbFile = new File(dbPath);

            if (!dbFile.exists()) { // DB가 없으면 복사
                InputStream inputStream = context.getAssets().open(DATABASE_NAME);
                File parentDir = dbFile.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }

                OutputStream outputStream = new FileOutputStream(dbPath);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();

                Log.d(TAG, "Database copied to " + dbPath);
            } else {
                Log.d(TAG, "Database already exists at " + dbPath);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error copying database", e);
        }
    }

    /**
     * RCP_TTL(레시피 제목)에서 키워드를 검색
     *
     * @param keyword 검색할 키워드
     * @return 검색된 레시피 리스트
     */
    public List<Recipe> searchRecipes(String keyword) {
        List<Recipe> recipes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Log.d(TAG, "쿼리 실행: SELECT * FROM recipes WHERE RCP_TTL LIKE '%" + keyword + "%'");

        Cursor cursor = db.rawQuery("SELECT * FROM recipes WHERE RCP_TTL LIKE ?", new String[]{"%" + keyword + "%"});

        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("RCP_TTL"))); // 레시피 제목
                recipe.setIngredients(cursor.getString(cursor.getColumnIndexOrThrow("CKG_MTRL_CN"))); // 재료 정보
                recipe.setMethod(cursor.getString(cursor.getColumnIndexOrThrow("CKG_MTH_ACTO_NM"))); // 요리 방법
                recipe.setTime(cursor.getString(cursor.getColumnIndexOrThrow("CKG_TIME_NM"))); // 소요 시간
                recipes.add(recipe);

                // 디버깅 로그
                Log.d(TAG, "검색된 레시피: " + recipe.getTitle());
            } while (cursor.moveToNext());
        } else {
            Log.d(TAG, "검색 결과 없음");
        }
        cursor.close();
        db.close();
        return recipes;
    }
}
