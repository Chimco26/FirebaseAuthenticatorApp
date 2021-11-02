package com.example.firebaseauthenticatorapp.models;

import java.util.List;

/**
 * Created by Chimco26 - RavTech on 01/11/2021.
 */
public class LittleDiscussion {
    private Message mLastMessage;
    private List<User> mListUsers;

    public void setmLastMessage(Message mLastMessage) {
        this.mLastMessage = mLastMessage;
    }

    public void setmListUsers(List<User> mListUsers) {
        this.mListUsers = mListUsers;
    }

    public Message getmLastMessage() {
        return mLastMessage;
    }

    public List<User> getmListUsers() {
        return mListUsers;
    }

    public LittleDiscussion(Message mLastMessage, List<User> mListUsers) {
        this.mLastMessage = mLastMessage;
        this.mListUsers = mListUsers;
    }
}
