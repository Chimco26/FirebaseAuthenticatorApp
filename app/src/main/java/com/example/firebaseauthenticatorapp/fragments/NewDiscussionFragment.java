package com.example.firebaseauthenticatorapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.firebaseauthenticatorapp.GoToFragDiscussionListener;
import com.example.firebaseauthenticatorapp.GoToFragUserListener;
import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Discussion;
import com.example.firebaseauthenticatorapp.models.Users;
import com.example.firebaseauthenticatorapp.views.users.UsersAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewDiscussionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewDiscussionFragment extends Fragment implements GoToFragUserListener {

    private String mUserUid;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private List<Users> mListUsers;
    private RecyclerView mListUsersRecycler;
    private ProgressBar mProgressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewDiscussionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewDiscussionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewDiscussionFragment newInstance(String param1, String param2) {
        NewDiscussionFragment fragment = new NewDiscussionFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        mListUsersRecycler = view.findViewById(R.id.recyclerView_list_users);
        mProgressBar = view.findViewById(R.id.progressBar_new_disc);

        db = FirebaseFirestore.getInstance();
        mUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.e("TAG", ""+ mUserUid);

        initListUsers();

        DocumentReference docRef = db.collection("Users").document(mUserUid);

        docRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot document = task.getResult();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_discussion, container, false);
    }

    public void initListUsers(){
        mProgressBar.setVisibility(View.VISIBLE);
        mListUsers = new ArrayList<>();
        db.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                mListUsers = queryDocumentSnapshots.toObjects(Users.class);
                mProgressBar.setVisibility(View.GONE);
                initRecyclerView();
            }
        });
    }

    public void initRecyclerView(){
        mListUsersRecycler.setAdapter(new UsersAdapter(mListUsers, this));
        mListUsersRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void goToFragment(Users user) {
        OneDiscussionFragment oneDiscussionFragment = new OneDiscussionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("recipientUser", user);
        oneDiscussionFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, oneDiscussionFragment)
                .addToBackStack(NewDiscussionFragment.class.getName())
                .commit();
    }
}