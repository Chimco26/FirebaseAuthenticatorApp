package com.example.firebaseauthenticatorapp.views.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Users;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {

    List<Users> mUserList;


    public UsersAdapter(List<Users> userList) {
        mUserList = userList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_user, parent, false);

        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.updateItemWithUser(mUserList.get(position));

    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
