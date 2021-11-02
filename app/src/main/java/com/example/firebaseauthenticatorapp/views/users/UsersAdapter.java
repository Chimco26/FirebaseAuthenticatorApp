package com.example.firebaseauthenticatorapp.views.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.User;

import java.util.List;

/**
 * Created by Chimco26 - RavTech on 01/11/2021.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {

    private List<User> mUserList;

    public UsersAdapter(List<User> userList) {
        mUserList = userList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_user, parent, false);
        return new  UsersViewHolder(view);
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
