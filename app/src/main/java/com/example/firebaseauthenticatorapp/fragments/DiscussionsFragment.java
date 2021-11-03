package com.example.firebaseauthenticatorapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Discussion;
import com.example.firebaseauthenticatorapp.models.LittleDiscussion;
import com.example.firebaseauthenticatorapp.views.discussion.DiscussionAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class DiscussionsFragment extends Fragment {

    private FirebaseDatabase realtimeDB;
    private RecyclerView mRecyclerDiscussion;
    private SearchView mSearchViewOneDiscussiuon;
    private FloatingActionButton mFloatingActionButtonDiscussion;
    private TextView mTitleTextView;
    private List<LittleDiscussion> discussionList;
    private DiscussionAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiscussionsFragment() {
        // Required empty public constructor
    }

    public static DiscussionsFragment newInstance(String param1, String param2) {
        DiscussionsFragment fragment = new DiscussionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_discussion, container, false);

        realtimeDB = FirebaseDatabase.getInstance();
        mRecyclerDiscussion = v.findViewById(R.id.recyclerView_list_users);
        mSearchViewOneDiscussiuon = v.findViewById(R.id.searchView_one_user);
        mFloatingActionButtonDiscussion = v.findViewById(R.id.floatingActionButton_new_discussion);
        mTitleTextView = v.findViewById(R.id.textView_title_discussions);
        getAllDiscussions();
        return v;
    }

    private  void configureRecyclerView(List<LittleDiscussion> list) {
        discussionList = list;
        adapter = new DiscussionAdapter(discussionList);
        mRecyclerDiscussion.setAdapter(adapter);
        mRecyclerDiscussion.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.notifyDataSetChanged();
    }

    private void getAllDiscussions(){
        DatabaseReference myRef = realtimeDB
                .getReference("littleChat");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dsp : snapshot.getChildren()){
                    LittleDiscussion discussion = dsp.getValue(LittleDiscussion.class);
                    if(discussion.getmListUsers().get(0).getUserUid()
                            == FirebaseAuth.getInstance().getCurrentUser().getUid()
                            || discussion.getmListUsers().get(1).getUserUid()
                            == FirebaseAuth.getInstance().getCurrentUser().getUid()){
                        discussionList.add(discussion);
                    }
                }
                configureRecyclerView(discussionList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}