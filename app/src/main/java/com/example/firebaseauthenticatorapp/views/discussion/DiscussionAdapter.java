package com.example.firebaseauthenticatorapp.views.discussion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Discussion;

import java.util.List;

public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionViewHolder> {

    private List<Discussion> discussionList;

    public DiscussionAdapter(List<Discussion> discussionList) {
        this.discussionList = discussionList;
    }

    @NonNull
    @Override
    public DiscussionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_discussion, parent, false);

        return new DiscussionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionViewHolder holder, int position) {
        holder.updateItemWithDiscussion(this.discussionList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.discussionList.size();
    }
}
