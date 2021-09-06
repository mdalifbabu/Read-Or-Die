package com.example.readordie4.Model;

import com.google.firebase.database.PropertyName;

public class Book {
    String Key, Name, Author, Image, Description, BorrowRate, SellRate, DeliveryCost, Discount, MenuId;

    public Book() {

    }

    public Book(String key, String name, String author, String image, String description, String borrowRate, String sellRate, String deliveryCost, String discount, String menuId) {
        Key = key;
        Name = name;
        Author = author;
        Image = image;
        Description = description;
        BorrowRate = borrowRate;
        SellRate = sellRate;
        DeliveryCost = deliveryCost;
        Discount = discount;
        MenuId = menuId;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    @PropertyName("Name")
    public String getName() {
        return Name;
    }

    @PropertyName("Name")
    public void setName(String name) {
        Name = name;
    }

    @PropertyName("Author")
    public String getAuthor() {
        return Author;
    }

    @PropertyName("Author")
    public void setAuthor(String author) {
        Author = author;
    }

    @PropertyName("Image")
    public String getImage() {
        return Image;
    }

    @PropertyName("Image")
    public void setImage(String image) {
        Image = image;
    }

    @PropertyName("Description")
    public String getDescription() {
        return Description;
    }

    @PropertyName("Description")
    public void setDescription(String description) {
        Description = description;
    }

    @PropertyName("BorrowRate")
    public String getBorrowRate() {
        return BorrowRate;
    }

    @PropertyName("BorrowRate")
    public void setBorrowRate(String borrowRate) {
        BorrowRate = borrowRate;
    }

    @PropertyName("SellRate")
    public String getSellRate() {
        return SellRate;
    }

    @PropertyName("SellRate")
    public void setSellRate(String sellRate) {
        SellRate = sellRate;
    }

    @PropertyName("DeliveryCost")
    public String getDeliveryCost() {
        return DeliveryCost;
    }

    @PropertyName("DeliveryCost")
    public void setDeliveryCost(String deliveryCost) {
        DeliveryCost = deliveryCost;
    }

    @PropertyName("Discount")
    public String getDiscount() {
        return Discount;
    }

    @PropertyName("Discount")
    public void setDiscount(String discount) {
        Discount = discount;
    }

    @PropertyName("MenuId")
    public String getMenuId() {
        return MenuId;
    }

    @PropertyName("MenuId")
    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
