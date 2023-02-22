package com.codecool.shop.model;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String passwor;

    private String addres;

    public User(Long id, String name, String surname, String email, String passwor, String addres) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwor = passwor;
        this.addres = addres;
    }
    public User( String name, String surname, String email, String passwor, String addres) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwor = passwor;
        this.addres = addres;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return passwor;
    }

    public boolean isAdmin() {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPasswor() {
        return passwor;
    }

    public String getAddres() {
        return addres;
    }

    public String getAddress() {
        return addres;
    }

    public Long getId() {
        return id;
    }
}
