package com.sakti.model;

public abstract class user {

    private int id;
    private String username;
    private String password;
    private String nama;

    public user() {
    }

    public user(int id, String username, String password, String nama) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
    }

    public abstract void tampilkanMenu();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    private String role;

public String getRole() {
    return role;
}

public void setRole(String role) {
    this.role = role;
}
}