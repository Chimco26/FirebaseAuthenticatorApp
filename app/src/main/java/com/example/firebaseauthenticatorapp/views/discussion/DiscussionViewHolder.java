package com.example.firebaseauthenticatorapp.views.discussion;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Discussion;
import com.example.firebaseauthenticatorapp.models.LittleDiscussion;
import com.google.firebase.auth.FirebaseAuth;
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

    public void updateItemWithDiscussion(LittleDiscussion discussion){
        if(discussion.getmListUsers().get(0).getUserUid() == FirebaseAuth.getInstance().getCurrentUser().getUid()){
            mNameDiscussion.setText(discussion.getmListUsers().get(1).getFullName());
        } else {
            mNameDiscussion.setText(discussion.getmListUsers().get(0).getFullName());

        }
            mTimeDiscussion.setText(discussion.getmLastMessage().getDateMessage().toString());
        mIconDiscussion.getImageMatrix();
        mTextDiscussion.setText(discussion.getmLastMessage().getTextMessage());
    }
}
