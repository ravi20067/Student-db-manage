package com.first.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CustomUserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String userName;
    private String password;

    public CustomUserDetails() {
    }

    public CustomUserDetails(String userName, int id, String password) {
        this.userName = userName;
        this.id = id;
        this.password = password;
    }
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     
}
