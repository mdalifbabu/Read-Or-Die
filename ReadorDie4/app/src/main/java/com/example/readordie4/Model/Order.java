package com.example.readordie4.Model;

import com.google.firebase.database.PropertyName;

public class Order {
    private String ProductId;
    private String ProductName;
    private String Quantity;
    private String Price;
    private String Discount;

    public Order() {
    }

    public Order(String productId, String productName, String quantity, String price, String discount) {
        ProductId = productId;
        ProductName = productName;
        Quantity = quantity;
        Price = price;
        Discount = discount;
    }

    @PropertyName("ProductId")
    public String getProductId() {
        return ProductId;
    }

    @PropertyName("ProductId")
    public void setProductId(String productId) {
        ProductId = productId;
    }

    @PropertyName("ProductName")
    public String getProductName() {
        return ProductName;
    }

    @PropertyName("ProductName")
    public void setProductName(String productName) {
        ProductName = productName;
    }

    @PropertyName("Quantity")
    public String getQuantity() {
        return Quantity;
    }

    @PropertyName("Quantity")
    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    @PropertyName("Price")
    public String getPrice() {
        return Price;
    }

    @PropertyName("Price")
    public void setPrice(String price) {
        Price = price;
    }

    @PropertyName("Discount")
    public String getDiscount() {
        return Discount;
    }

    @PropertyName("Discount")
    public void setDiscount(String discount) {
        Discount = discount;
    }
}
