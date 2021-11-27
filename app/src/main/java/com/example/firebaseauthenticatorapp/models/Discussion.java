package com.example.firebaseauthenticatorapp.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@IgnoreExtraProperties
public class Discussion implements Serializable {
    private Message mLastMessage;
    private List<Users> subject;
    private List<Message> messageList;
    private String mTitleDiscussion;
    private String uId;

    public Discussion() {
    }

    public Discussion(List<Users> subject) {
        this.subject = subject;
        messageList = new ArrayList<>();
    }

    public List<Users> getSubject() {
        return subject;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public Message getmLastMessage() {
        return mLastMessage;
    }

    public String getuId() {
        return uId;
    }

    public String getmTitleDiscussion() {
        return mTitleDiscussion;
    }

    public void setSubject(List<Users> subject) {
        this.subject = subject;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void setLastMessage(Message mLastMessage) {
        this.mLastMessage = mLastMessage;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setmTitleDiscussion(String mTitleDiscussion) {
        this.mTitleDiscussion = mTitleDiscussion;
    }

    public void addMessage(Message message){
        mLastMessage = message;
        messageList.add(message);
    }

    public void addSubject(Users user){
        subject.add(user);
    }
}
