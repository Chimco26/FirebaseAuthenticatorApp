package com.example.firebaseauthenticatorapp.views.discussion;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Discussion;
import com.squareup.picasso.Picasso;

public class DiscussionViewHolder extends RecyclerView.ViewHolder {

    private ImageView mIconDiscussion;
    private TextView mNameDiscussion;
    private TextView mTextDiscussion;
    private TextView mTimeDiscussion;
    private Picasso picasso;

    public DiscussionViewHolder(@NonNull View itemView) {
        super(itemView);
        mIconDiscussion = itemView.findViewById(R.id.disc_image);
        mNameDiscussion = itemView.findViewById(R.id.disc_name);
        mTextDiscussion = itemView.findViewById(R.id.disc_text);
        mTimeDiscussion = itemView.findViewById(R.id.disc_time);
        picasso = Picasso.with(itemView.getContext());
    }

    public void updateItemWithDiscussion(Discussion discussion){
        mNameDiscussion.setText(discussion.getTitleDiscussion());
        mTimeDiscussion.setText(discussion.getLastMessageDate().toString());
        mIconDiscussion.getImageMatrix();
        mTextDiscussion.setText(discussion
                .getMyDiscussion()
                .get(discussion.getMyDiscussion().size())
                .getTextMessage());
    }
}
