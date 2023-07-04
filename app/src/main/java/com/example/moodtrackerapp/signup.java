package com.example.moodtrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    private static final String USERS = "users";
    EditText FullName, Email, Username, Password;
    ActionBar actionBar; Button registerButton;
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    String username, fname, email, password, TAG = "signup";
    private FirebaseAuth mAuth; private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        actionBar = getSupportActionBar();
        actionBar.hide();

        // Assign user variables
        FullName = findViewById(R.id.fullname);
        Username = findViewById(R.id.username);
        Email = findViewById(R.id.userEmail);
        Password = findViewById(R.id.password);
        registerButton = findViewById(R.id.signupButton);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USERS);
        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert data into firebase database
                if (Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty() || FullName.getText().toString().isEmpty()) {
                    Toast.makeText(signup.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (Password.length() < 6) {
                    Toast.makeText(signup.this, "The password must be more than 6 characters ", Toast.LENGTH_SHORT).show();
                } else {
                    database = FirebaseDatabase.getInstance();
                    mDatabase = database.getReference("users");
                    fname = FullName.getText().toString();
                    username = Username.getText().toString();
                    email = Email.getText().toString();
                    password = Password.getText().toString();
                    user = new User(fname, username, email);

                    registerUser();
                }
            }
        });

    }


    public void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    Toast.makeText(signup.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    SignUp();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(signup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void SignUp() {
        mDatabase.child(username).setValue(user);
        Log.d("TAG", user.getUserName() + ":un -- fn:" + user.getFullName());
        Intent i = new Intent(this, timeLinePage.class);
        i.putExtra("Username", user.getUserName());
        i.putExtra("name", user.getFullName());
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        startActivity(i);
    }


    public void MoveToSignIn(View view) {
        Intent signIn = new Intent(this, signIn.class);
        startActivity(signIn);

    }
}