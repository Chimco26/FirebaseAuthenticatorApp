package com.example.firebaseauthenticatorapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.firebaseauthenticatorapp.GoToFragDiscussionListener;
import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Discussion;
import com.example.firebaseauthenticatorapp.models.LittleDiscussion;
import com.example.firebaseauthenticatorapp.models.Message;
import com.example.firebaseauthenticatorapp.models.Users;
import com.example.firebaseauthenticatorapp.views.discussion.DiscussionAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Lists;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiscussionsFragment extends Fragment implements GoToFragDiscussionListener {

    private DatabaseReference myChatRef;
    private CollectionReference myUsersRef;
    private Map<String, String> mMapListUserDiscussionUid = new ArrayMap<>();
    private RecyclerView mRecyclerDiscussion;
    private SearchView mSearchViewOneDiscussiuon;
    private ImageView mNewDiscussionButton;
    private TextView mTitleTextView;
    private List<Discussion> discussionList;
    private DiscussionAdapter adapter;
    private ProgressBar progressBar;
    private  final String TAG = DiscussionsFragment.class.getName();

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
        myUsersRef = FirebaseFirestore.getInstance().collection("Users");
        myChatRef = FirebaseDatabase.getInstance().getReference("allChat");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_discussion, container, false);

        mRecyclerDiscussion = v.findViewById(R.id.recyclerView_list_users);
        mSearchViewOneDiscussiuon = v.findViewById(R.id.searchView_one_user);
        mNewDiscussionButton = v.findViewById(R.id.new_discussion_button);
        mTitleTextView = v.findViewById(R.id.textView_title_discussions);
        progressBar = v.findViewById(R.id.progressBar_all_disc);
        discussionList = new ArrayList<>();
        mNewDiscussionButton.setOnClickListener(v1 -> getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new NewDiscussionFragment())
                .addToBackStack(null)
                .commit());
    //    createOneMessageTest();
        getAllDiscussions();
        return v;
    }

    private void configureRecyclerView() {
        progressBar.setVisibility(View.GONE);
        if(discussionList.size() > 0){
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setReverseLayout(true);
            layoutManager.setStackFromEnd(true);
            adapter = new DiscussionAdapter(discussionList, this);
            mRecyclerDiscussion.setAdapter(adapter);
            mRecyclerDiscussion.setLayoutManager(layoutManager);
            adapter.notifyDataSetChanged();
        }
    }

    private void getAllDiscussions(){
        progressBar.setVisibility(View.VISIBLE);
        myUsersRef.document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                DocumentSnapshot document = task.getResult();
                if (document != null){
                    mMapListUserDiscussionUid = (Map<String, String>) document.get("list_user");
                    for (String disc : mMapListUserDiscussionUid.values()){
                        myChatRef.child(disc).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                discussionList.add(snapshot.getValue(Discussion.class));
                                configureRecyclerView();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }
        });

    }

    public void createOneMessageTest() {
        String textMessage = "hello my best friend!";
        String senderID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Message message = new Message(senderID, textMessage);
        LittleDiscussion discussion = new LittleDiscussion(message, Lists.newArrayList(
                new Users("Simon", "simon@gmail.com", "052", FirebaseAuth.getInstance().getCurrentUser().getUid()),
                new Users("simono", "simono@gmail.com", "058", "qwerty"
        )));
        myChatRef.child("uid").push().setValue(discussion);
    }

    @Override
    public void goToFragment(Discussion littleDiscussion) {
        OneDiscussionFragment oneDiscussionFragment = new OneDiscussionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("myLittleDiscussion", littleDiscussion);
        oneDiscussionFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, oneDiscussionFragment)
                .addToBackStack(DiscussionsFragment.class.getName())
                .commit();
    }
}