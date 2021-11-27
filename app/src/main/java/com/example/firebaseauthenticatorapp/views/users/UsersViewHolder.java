package com.example.firebaseauthenticatorapp.views.users;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthenticatorapp.GoToFragUserListener;
import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Users;

public class UsersViewHolder extends RecyclerView.ViewHolder {

    TextView userName;
    TextView userTel;
    ImageView userImage;
    Users mUser;

    public UsersViewHolder(@NonNull View itemView, GoToFragUserListener fragUserListener) {
        super(itemView);
        userName = itemView.findViewById(R.id.user_name);
        userTel = itemView.findViewById(R.id.user_tel);
        userImage = itemView.findViewById(R.id.user_image);

        itemView.setOnClickListener(v -> fragUserListener.goToFragment(mUser));
    }

    public void updateItemWithUser(Users user){
        userName.setText(user.getFullName());
        mUser = user;
      //  userTel.setText(user.getPhone());
    }
}
