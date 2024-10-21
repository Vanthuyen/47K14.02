package com.example.appstudy;

public class Product {
    String productName;
    float productPrice;
    int image;

    public Product(String productName, float productPrice, int image) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
