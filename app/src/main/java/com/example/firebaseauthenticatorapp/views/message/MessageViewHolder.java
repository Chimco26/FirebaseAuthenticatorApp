package com.example.firebaseauthenticatorapp.views.message;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Message;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView mMessageName;
    private TextView mMessageText;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);

        mMessageName = itemView.findViewById(R.id.message_name);
        mMessageText = itemView.findViewById(R.id.message_text);
    }

    public void updateItemWithMessage(Message message){
        mMessageName.setText(message.getSenderID());
        mMessageText.setText(message.getTextMessage());
    }
}
