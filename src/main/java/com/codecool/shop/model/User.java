package com.codecool.shop.model;

public class User {
    private String name;
    private String surname;

    private String email;
    private int id;
    private String password;
    private String adress;
    public User(String name, String surname, String email, String password, String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.adress = address;
    }
    public String getName() {
        return name;
    }
    public String getAdress(){
        return adress;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

}
