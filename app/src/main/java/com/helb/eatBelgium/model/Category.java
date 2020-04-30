package com.helb.eatBelgium.model;

public class Category {
    private  String id;
    private String nomCategory;

    public Category() {
    }

    public Category(String id, String nomCategory) {
        this.id = id;
        this.nomCategory = nomCategory;
    }

    public Category(String id) {
    }

    public String getNomCategory() {
        return nomCategory;
    }

    public void setNomCategory(String nomCategory) {
        this.nomCategory = nomCategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
