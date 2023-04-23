package com.example.linkchat_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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


public class registration extends AppCompatActivity {

    TextView textView;
    ActivityMainBinding binding;


    Button button;
    EditText editText,editText1,editText2;

    private FirebaseAuth auth;
    FirebaseDatabase database;

    ProgressDialog progressDialog;

    int count = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        textView =findViewById(R.id.txt);
        button = findViewById(R.id.sign);

        editText =findViewById(R.id.mail);
        editText1= findViewById(R.id.pass);
        editText1= findViewById(R.id.cpass);
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(registration.this);
        progressDialog.setTitle("Register succesfully");
        progressDialog.setMessage("your are register successfully");



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                auth.createUserWithEmailAndPassword
                        (editText.getText().toString(), editText1.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            user u= new user(editText.getText().toString(),editText1.getText().toString(),editText2.getText().toString());

                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("users").child(id).setValue(u);
                            Toast.makeText(registration.this,"user regsiter successfully",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(registration.this,homepage.class);
                            startActivity(i);
                        }
                        else {

                            Toast.makeText(registration.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });




    }
}