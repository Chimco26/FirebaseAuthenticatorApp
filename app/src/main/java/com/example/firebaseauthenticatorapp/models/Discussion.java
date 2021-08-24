package com.example.firebaseauthenticatorapp.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Discussion {
    List<User> subject;
    List<Message> myDiscussion;

    public Discussion() {
    }

    public Discussion(List<User> subject, List<Message> myDiscussion) {
        this.subject = subject;
        this.myDiscussion = myDiscussion;
    }

    public List<User> getSubject() {
        return subject;
    }

    public List<Message> getMyDiscussion() {
        return myDiscussion;
    }

    public void setSubject(List<User> subject) {
        this.subject = subject;
    }

    public void setMyDiscussion(List<Message> myDiscussion) {
        this.myDiscussion = myDiscussion;
    }
}
