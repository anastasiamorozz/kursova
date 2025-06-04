package com.example.kursova.model;

public class Guide {
    private int id;
    private String name;
    private String language;
    private String phone;

    public Guide(int id, String name, String language, String phone) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.phone = phone;
    }

    // Геттери
    public int getId() { return id; }
    public String getName() { return name; }
    public String getLanguage() { return language; }
    public String getPhone() { return phone; }

    // Сеттери
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLanguage(String language) { this.language = language; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return name + " (" + language + ")";
    }
}
