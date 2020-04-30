package com.helb.eatBelgium.model;

public class Product {
    private String nameProduct;
    private String idCategory;
    private int price;

    public Product() {
    }

    public Product(String nameProduct, String idCategory, int price) {
        this.nameProduct = nameProduct;
        this.idCategory = idCategory;
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
