package com.example.firebaseauthenticatorapp.views.discussion;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthenticatorapp.GoToFragDiscussionListener;
import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Discussion;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class DiscussionViewHolder extends RecyclerView.ViewHolder {

    private ImageView mIconDiscussion;
    private TextView mNameDiscussion;
    private TextView mTextDiscussion;
    private TextView mTimeDiscussion;
    private Picasso picasso;
    private Discussion mLittleDiscussion;

    public DiscussionViewHolder(@NonNull View itemView, GoToFragDiscussionListener goToFragDiscussionListener) {
        super(itemView);
        mIconDiscussion = itemView.findViewById(R.id.disc_image);
        mNameDiscussion = itemView.findViewById(R.id.disc_name);
        mTextDiscussion = itemView.findViewById(R.id.disc_text);
        mTimeDiscussion = itemView.findViewById(R.id.disc_time);
        picasso = Picasso.with(itemView.getContext());
        itemView.setOnClickListener(v -> {
            if(mLittleDiscussion != null)
                goToFragDiscussionListener.goToFragment(mLittleDiscussion);
        });
    }

    public void updateItemWithDiscussion(Discussion discussion){
        mLittleDiscussion = discussion;
        if(discussion.getSubject().get(0).getUserUid().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
            mNameDiscussion.setText(discussion.getSubject().get(1).getFullName());
        } else {
            mNameDiscussion.setText(discussion.getSubject().get(0).getFullName());

        }
            mTimeDiscussion.setText(discussion.getmLastMessage().getDateMessage().toString());
       // mIconDiscussion.getImageMatrix();
        mTextDiscussion.setText(discussion.getmLastMessage().getTextMessage());
    }
}
