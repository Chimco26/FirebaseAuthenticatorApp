package com.example.firebaseauthenticatorapp.views.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Message;

import java.util.List;
import java.util.zip.Inflater;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Message> messageList;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.updateItemWithMessage(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
