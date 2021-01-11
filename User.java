package com.example.mini_projet_android;

import java.util.Date;

public class User {
    private String id;
    private String email;
    private String password;
    private String roles;
    private Date date;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User(String id, String email, String password, String roles, Date date) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String email, String password, String roles, Date date) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", date=" + date +
                '}';
    }
}
