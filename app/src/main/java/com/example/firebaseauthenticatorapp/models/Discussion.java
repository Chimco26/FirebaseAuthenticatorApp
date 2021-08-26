package com.example.firebaseauthenticatorapp.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;
import java.util.List;

@IgnoreExtraProperties
public class Discussion {
    private List<User> subject;
    private List<Message> myDiscussion;
    private Date lastMessageDate;
    private String titleDiscussion;

    public Discussion() {
    }

    public Discussion(List<User> subject, String titleDiscussion) {
        this.subject = subject;
        this.titleDiscussion = titleDiscussion;
    }

    public List<User> getSubject() {
        return subject;
    }

    public List<Message> getMyDiscussion() {
        return myDiscussion;
    }

    public Date getLastMessageDate() {
        return lastMessageDate;
    }

    public String getTitleDiscussion() {
        return titleDiscussion;
    }

    public void setSubject(List<User> subject) {
        this.subject = subject;
    }

    public void setMyDiscussion(List<Message> myDiscussion) {
        this.myDiscussion = myDiscussion;
    }

    public void setLastMessageDate(Date lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public void setTitleDiscussion(String titleDiscussion) {
        this.titleDiscussion = titleDiscussion;
    }
}
