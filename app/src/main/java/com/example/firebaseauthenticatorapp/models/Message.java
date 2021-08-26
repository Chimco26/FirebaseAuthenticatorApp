package com.example.firebaseauthenticatorapp.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class Message {
    private String senderID;
    private boolean isSent;
    private boolean isReceived;
    private boolean isRead;

    private Date dateMessage;

    private String textMessage;

    public Message() {
    }

    public Message(String senderID, Date dateMessage, String textMessage) {
        this.senderID = senderID;
        this.dateMessage = dateMessage;
        this.textMessage = textMessage;
    }

    public String getSenderID() {
        return senderID;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public Date getDateMessage() {
        return dateMessage;
    }

    public boolean isSent() {
        return isSent;
    }

    public boolean isReceived() {
        return isReceived;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public void setDateMessage(Date dateMessage) {
        this.dateMessage = dateMessage;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
