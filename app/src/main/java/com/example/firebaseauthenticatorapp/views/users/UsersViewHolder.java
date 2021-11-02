package com.example.firebaseauthenticatorapp.views.users;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Message;
import com.example.firebaseauthenticatorapp.models.User;

/**
 * Created by Chimco26 - RavTech on 01/11/2021.
 */
public class UsersViewHolder extends RecyclerView.ViewHolder {
    private TextView mUserName;
    private ImageView mUserImage;

    public UsersViewHolder(@NonNull View itemView) {
        super(itemView);

        mUserImage = itemView.findViewById(R.id.user_image);
        mUserName = itemView.findViewById(R.id.user_name);
    }

    public void updateItemWithUser(User user){
        mUserName.setText(user.getFullName());
        mUserImage.setImageResource(R.drawable.ic_baseline_person_24);
    }
}
