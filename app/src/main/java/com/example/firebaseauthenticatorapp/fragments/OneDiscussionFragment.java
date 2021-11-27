package com.example.firebaseauthenticatorapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Discussion;
import com.example.firebaseauthenticatorapp.models.Message;
import com.example.firebaseauthenticatorapp.models.Users;
import com.example.firebaseauthenticatorapp.views.message.MessageAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OneDiscussionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneDiscussionFragment extends Fragment {

    private DatabaseReference mChatRef;
    private CollectionReference myUsersRef;
    private Map<String, String> mMapListUserDiscussionUid = new ArrayMap<>();
    private RecyclerView mRecyclerMessage;
    private EditText mMyEditTextMessage;
    private TextView mTitleDiscussion;
    private List<Message> messageList = new ArrayList<>();
    private Discussion mDiscussion;
    private Users currentUser;
    private Users recipientUser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    public OneDiscussionFragment() {
        // Required empty public constructor
    }

    public static OneDiscussionFragment newInstance(String param1, String param2) {
        OneDiscussionFragment fragment = new OneDiscussionFragment();
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
            if(getArguments().containsKey("myLittleDiscussion")){
                mDiscussion = (Discussion)getArguments().getSerializable("myLittleDiscussion");
                recipientUser = mDiscussion.getSubject().get(0).getUserUid().equals
                        (FirebaseAuth.getInstance().getCurrentUser().getUid())?
                        mDiscussion.getSubject().get(1) : mDiscussion.getSubject().get(0);
            }
            if(getArguments().containsKey("recipientUser")){
                recipientUser = (Users)getArguments().getSerializable("recipientUser");
            }
        }
        myUsersRef = FirebaseFirestore.getInstance().collection("Users");
        mChatRef = FirebaseDatabase.getInstance().getReference("allChat");

        initCurrentDiscussion();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one_discussion_fragment, container, false);

        initViews(view);
        return view;
    }

    private void initCurrentDiscussion() {
        myUsersRef.document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                DocumentSnapshot document = task.getResult();
                if (document != null){
                    currentUser = document.toObject(Users.class);
                    mMapListUserDiscussionUid = (Map<String, String>) document.get("list_user");
                    if (mMapListUserDiscussionUid.containsKey(recipientUser.getUserUid())){
                        mChatRef.child(mMapListUserDiscussionUid.get(recipientUser.getUserUid()))
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        mDiscussion = snapshot.getValue(Discussion.class);
                                        getAllMessage();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                    //    mDiscussion = getDiscussion(mMapListUserDiscussionUid.get(recipientUser.getUserUid()));
                    }
                }
            }
        });
    }

    private Discussion getDiscussion(String userUid) {
        DatabaseReference myRef = mChatRef.child(userUid);
        return myRef.get().getResult().getValue(Discussion.class);
    }

    private void initViews(View view) {
        mMyEditTextMessage = view.findViewById(R.id.editText_my_message);
        mTitleDiscussion = view.findViewById(R.id.title_of_discussion);
        FloatingActionButton mSendMessageButton = view.findViewById(R.id.floatingActionButton_send_action);
        mRecyclerMessage = view.findViewById(R.id.recyclerView_message);
        mSendMessageButton.setOnClickListener(l -> postNewMessage());
        setTitleDiscussion();
    }

    private void setTitleDiscussion() {
            mTitleDiscussion.setText(recipientUser.getFullName());
    }

    private void getAllMessage(){
        if(mDiscussion != null){
            mChatRef.child(mDiscussion.getuId()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Discussion discussion = snapshot.getValue(Discussion.class);
                    messageList = discussion.getMessageList();
                    configureRecyclerView();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    private void postNewMessage(){
        Message message = createNewMessage();
        if(mDiscussion == null){
            mDiscussion = createNewDiscussion();
            mDiscussion.addMessage(message);
            mChatRef.push().setValue(mDiscussion, (databaseError, databaseReference) -> {
                mDiscussion.setuId(databaseReference.getKey());
                mChatRef.child(databaseReference.getKey()).setValue(mDiscussion);
                mMapListUserDiscussionUid.put(recipientUser.getUserUid(), databaseReference.getKey());
                myUsersRef.document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .update("list_user", mMapListUserDiscussionUid);
            });

           // getAllMessage();
        } else {
            mDiscussion.addMessage(message);
            DatabaseReference myRef = mChatRef.child(mDiscussion.getuId());
            myRef.setValue(mDiscussion);
        }
    }

    private Message createNewMessage(){
        String textMessage = mMyEditTextMessage.getText().toString();
        String senderID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mMyEditTextMessage.setText("");
        return new Message(senderID, textMessage);
    }

    private Discussion createNewDiscussion(){
        return new Discussion(new ArrayList<>(Arrays.asList(currentUser, recipientUser)));
    }

    private  void configureRecyclerView() {
        if(messageList.size() > 0){
            MessageAdapter adapter = new MessageAdapter(messageList);
            mRecyclerMessage.setAdapter(adapter);
            mRecyclerMessage.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter.notifyDataSetChanged();
        }
    }


    // private void initUsers() {
    //     myCollectionRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
    //         mListUsers = queryDocumentSnapshots.toObjects(Users.class);
    //         for (Users user : mListUsers){
    //             if (user.getUserUid().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
    //                 currentUser = user;
    //                 return;
    //             }
    //         }
    //     });
    // }
}
