package com.example.linkchat_final;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linkchat_final.databinding.ActivityMainBinding;
import com.example.linkchat_final.model.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TextView textView;
    ImageView imageView;
    Button button, btn;
    EditText editText,editText1;

    private FirebaseAuth auth;
    FirebaseDatabase database;

    ProgressDialog progressDialog;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.b1);
        btn =findViewById(R.id.btn2);
        editText =findViewById(R.id.Email);
        editText1= findViewById(R.id.password);



        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("login in succesfully");
        progressDialog.setMessage("your account created succesfully");




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),registration.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               progressDialog.show();
                auth.createUserWithEmailAndPassword
                        (editText.getText().toString(), editText1.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            user u= new user(editText.getText().toString(),editText1.getText().toString());

                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("users").child(id).setValue(u);
                            Toast.makeText(MainActivity.this,"user created successfully",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),homepage.class);
                            startActivity(i);

                        }
                        else {

                            Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), homepage.class);
                startActivity(i);
            }
        });










    }
}



