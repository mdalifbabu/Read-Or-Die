package com.example.readordie4.Model;

import com.google.firebase.database.PropertyName;

public class User {
    private String Name, Password, Phone;

    public User(){

    }

    public User(String name, String password, String phone) {
        Name = name;
        Password = password;
        Phone = phone;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    @PropertyName("Name")
    public String getName() {
        return Name;
    }

    @PropertyName("Name")
    public void setName(String name) {
        Name = name;
    }

    @PropertyName("Password")
    public String getPassword() {
        return Password;
    }

    @PropertyName("Password")
    public void setPassword(String password) {
        Password = password;
    }
}
