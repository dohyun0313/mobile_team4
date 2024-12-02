package com.example.myapplication;

public class Recipe {
    private String title;          // 레시피 제목 (RCP_TTL)
    private String ingredients;    // 재료 정보 (CKG_MTRL_CN)
    private String method;         // 요리 방법 (CKG_MTH_ACTO_NM)
    private String time;           // 소요 시간 (CKG_TIME_NM)

    // Getter & Setter
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}
