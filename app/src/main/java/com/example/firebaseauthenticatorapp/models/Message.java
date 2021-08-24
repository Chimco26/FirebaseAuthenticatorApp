package com.example.firebaseauthenticatorapp.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class Message {
    private String senderID;
    private String recipientID;
    private Date dateMessage;

    private StringBuilder textMessage;

    public Message() {
    }

    public Message(String senderID, String recipientID, Date dateMessage) {
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.dateMessage = dateMessage;
    }

    public String getSenderID() {
        return senderID;
    }

    public String getRecipientID() {
        return recipientID;
    }

    public StringBuilder getTextMessage() {
        return textMessage;
    }

    public Date getDateMessage() {
        return dateMessage;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }

    public void setTextMessage(StringBuilder textMessage) {
        this.textMessage = textMessage;
    }

    public void setDateMessage(Date dateMessage) {
        this.dateMessage = dateMessage;
    }
}
