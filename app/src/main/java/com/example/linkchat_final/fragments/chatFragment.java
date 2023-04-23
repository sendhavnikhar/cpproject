package com.example.linkchat_final.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.linkchat_final.Adapters.useradpter;
import com.example.linkchat_final.R;
import com.example.linkchat_final.databinding.ActivityHomepageBinding;
import com.example.linkchat_final.databinding.FragmentChatBinding;
import com.example.linkchat_final.model.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class chatFragment extends Fragment {
    public chatFragment() {
        // Required empty public constructor
    }

    FragmentChatBinding binding;
    ArrayList<user>  list = new ArrayList<>();
    FirebaseDatabase database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentChatBinding.inflate(inflater, container, false);

        database =FirebaseDatabase.getInstance();

        useradpter adapter = new useradpter(list,getContext());
        binding.chatRecycleView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.chatRecycleView.setLayoutManager(layoutManager);

        database.getReference().child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    user use = dataSnapshot.getValue(user.class);
                    use.getUserid(dataSnapshot.getKey());
                    list.add(use);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return  binding.getRoot();
    }
}