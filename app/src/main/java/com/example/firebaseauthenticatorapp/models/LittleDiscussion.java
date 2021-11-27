package com.example.firebaseauthenticatorapp.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chimco26 - RavTech on 01/11/2021.
 */
public class LittleDiscussion implements Serializable {
    private Message mLastMessage;
    private List<Users> mUsersList;

    public void setmLastMessage(Message mLastMessage) {
        this.mLastMessage = mLastMessage;
    }

    public void setmUsersList(List<Users> mUsersList) {
        this.mUsersList = mUsersList;
    }

    public Message getmLastMessage() {
        return mLastMessage;
    }

    public List<Users> getmUsersList() {
        return mUsersList;
    }

    public LittleDiscussion(Message mLastMessage, List<Users> mUsersList) {
        this.mLastMessage = mLastMessage;
        this.mUsersList = mUsersList;
    }

    public LittleDiscussion(){

    }
}
