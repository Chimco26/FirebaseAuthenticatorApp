package com.example.firebaseauthenticatorapp.models;

import com.google.firebase.firestore.auth.User;

import java.util.List;

/**
 * Created by Chimco26 - RavTech on 01/11/2021.
 */
public class LittleDiscussion {
    private Message mLastMessage;
    private List<Users> mListUsers;

    public void setmLastMessage(Message mLastMessage) {
        this.mLastMessage = mLastMessage;
    }

    public void setmListUsers(List<Users> mListUsers) {
        this.mListUsers = mListUsers;
    }

    public Message getmLastMessage() {
        return mLastMessage;
    }

    public List<Users> getmListUsers() {
        return mListUsers;
    }

    public LittleDiscussion(Message mLastMessage, List<Users> mListUsers) {
        this.mLastMessage = mLastMessage;
        this.mListUsers = mListUsers;
    }
}
