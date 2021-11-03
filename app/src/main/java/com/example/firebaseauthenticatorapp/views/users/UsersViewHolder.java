package com.example.firebaseauthenticatorapp.views.users;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Users;

public class UsersViewHolder extends RecyclerView.ViewHolder {

    TextView userName;
    TextView userTel;
    ImageView userImage;

    public UsersViewHolder(@NonNull View itemView) {
        super(itemView);

        userName = itemView.findViewById(R.id.user_name);
        userTel = itemView.findViewById(R.id.user_tel);
        userImage = itemView.findViewById(R.id.user_image);
    }

    public void updateItemWithUser(Users user){
        userName.setText(user.getFullName());
      //  userTel.setText(user.getPhone());
    }
}
