package com.example.firebaseauthenticatorapp.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.List;

@IgnoreExtraProperties
public class Users implements Serializable {
    private String fullName;
    private String email;
    private String phone;
    private String userUid;

    public Users() {
    }

    public Users(String fullName, String email, String phone, String userUid) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.userUid = userUid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }
}
