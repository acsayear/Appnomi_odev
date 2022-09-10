package com.example.appnomiodev;

public class ProductItem {
    String id,image,title,desc;
    int stock;
    double price,campprice;

    public ProductItem(String id, String image, String title, String desc, int stock, double price, double campprice) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.stock = stock;
        this.price = price;
        this.campprice = campprice;
    }
}
