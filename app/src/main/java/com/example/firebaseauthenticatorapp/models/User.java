package com.example.firebaseauthenticatorapp.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class User {
    private String fullName;
    private String email;
    private String phone;
    private String userUid;
    private List<Discussion> myListDiscussion;

    public User() {
    }

    public User(String fullName, String email, String phone, String userUid) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.userUid = userUid;
    }

    public User(String fullName, String email, String phone, String userUid, List<Discussion> myListDiscussion) {
        this(fullName, email, phone, userUid);
        this.myListDiscussion = myListDiscussion;
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

    public List<Discussion> getMyListDiscussion() {
        return myListDiscussion;
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

    public void setMyListDiscussion(List<Discussion> myListDiscussion) {
        this.myListDiscussion = myListDiscussion;
    }
}
