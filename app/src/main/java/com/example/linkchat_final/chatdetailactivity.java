package com.example.linkchat_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.linkchat_final.databinding.ActivityChatdetailactivityBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class chatdetailactivity extends AppCompatActivity {
   FirebaseDatabase database;
   FirebaseAuth auth;

    ActivityChatdetailactivityBinding  binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

   binding =ActivityChatdetailactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();
        String senderId = auth.getUid();
        String reciceId = getIntent().getStringExtra("userid");
        String username = getIntent().getStringExtra("username");
        String ProfilePic = getIntent().getStringExtra("profilePic");

        binding.username.setText(username);
        Picasso.get().load(ProfilePic).placeholder(R.drawable.avtar).into(binding.profileImage);
    }


}