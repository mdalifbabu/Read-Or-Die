package com.example.readordie.Model;

public class User {
    private String Name, Password;

    public User(){

    }

    public User(String Name, String Password){
        this.Name = Name;
        this.Password = Password;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

}
