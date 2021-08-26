package com.example.firebaseauthenticatorapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Message;
import com.example.firebaseauthenticatorapp.views.discussion.DiscussionAdapter;
import com.example.firebaseauthenticatorapp.views.message.MessageAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment {

    private FirebaseDatabase realtimeDB;
    private RecyclerView mRecyclerMessage;
    private EditText mMyEditTextMessage;
    private TextView mTitleDiscussion;
    private FloatingActionButton mSendMessageButton;
    private MessageAdapter adapter;
    private List<Message> messageList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MessageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OneDiscussionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
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
        View view = inflater.inflate(R.layout.message_fragment, container, false);

        realtimeDB = FirebaseDatabase.getInstance();
        mMyEditTextMessage = view.findViewById(R.id.editText_my_message);
        mTitleDiscussion = view.findViewById(R.id.title_of_discussion);
        mSendMessageButton = view.findViewById(R.id.floatingActionButton_new_discussion);
        mRecyclerMessage = view.findViewById(R.id.recyclerView_message);
        return view;
    }

    private  void configureRecyclerView() {
        messageList = new ArrayList<>();
        adapter = new MessageAdapter(messageList);
        mRecyclerMessage.setAdapter(adapter);
        mRecyclerMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.notifyDataSetChanged();
    }

    private void getAllMessage(){
        DatabaseReference myRef = realtimeDB.getReference("discussions/ +")

    }

    private void postNewMessage(){

        String textMessage = mMyEditTextMessage.getText().toString();
        Date dateMessage = Calendar.getInstance().getTime();
        String senderID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Message message = new Message(senderID, dateMessage, textMessage);
        messageList.add(message);
        DatabaseReference myRef = realtimeDB.getReference("discussions/" + );
        myRef.setValue(message);
    }
}