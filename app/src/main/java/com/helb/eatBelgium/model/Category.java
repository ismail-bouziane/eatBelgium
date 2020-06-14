package com.helb.eatBelgium.model;

public class Category {
    private  String id;
    private String nomCategory;
    private String image;

    public Category() {
    }

    public Category(String id, String nomCategory,String image) {
        this.id = id;
        this.nomCategory = nomCategory;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", nomCategory='" + nomCategory + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
