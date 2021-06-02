package com.example.a69_18028791_tranvuhoangson;

public class Product {
    private int id;
    private String type;
    private int price;
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Product(int id, String type, int price, String country) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.country = country;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", country='" + country + '\'' +
                '}';
    }
}
