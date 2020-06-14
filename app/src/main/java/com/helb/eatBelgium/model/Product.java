package com.helb.eatBelgium.model;

public class Product {
    private String idProduct;
    private String nameProduct;
    private String idCategory;
    private int price;
    private String image;

    public Product() {
    }

    public Product(String idProduct, String nameProduct, String idCategory, int price,String image) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.idCategory = idCategory;
        this.price = price;
        this.image = image;
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

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
