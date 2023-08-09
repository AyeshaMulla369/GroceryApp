package com.rajendra.onlinedailygroceries.model;

public class CartItem {
    long id;
    String name;
    String price;
    int q;

    public CartItem(long id, String name, String price, int q) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.q = q;
    }

    public long getIId() {
        return id;
    }

    public void setIId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return name;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }
}
