package com.example.moodtrackerapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class signIn extends AppCompatActivity {
    ActionBar actionBar;
    EditText Username, Password;
    Button loginButton;
    private FirebaseAuth mAuth;
    private String emailFromDB, usernameFromDB, nameFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        actionBar = getSupportActionBar();
        actionBar.hide();

        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        mAuth = FirebaseAuth.getInstance();

        //checking if user is logged in
//        if (mAuth.getCurrentUser() != null) {
//            updateUI(mAuth.getCurrentUser());
//        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Password.getText().toString().isEmpty() || Username.getText().toString().isEmpty()) {
                    Toast.makeText(signIn.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (Password.length() < 6) {
                    Toast.makeText(signIn.this, "The password must be more than 6 characters ", Toast.LENGTH_SHORT).show();
                }

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                Query checkUserDatabase = reference.child("users");
                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String key = ds.getKey();
                            if (snapshot.child(key).child("userName").getValue(String.class).equals(Username.getText().toString())) {
                                nameFromDB = snapshot.child(key).child("fullName").getValue(String.class);
                                emailFromDB = snapshot.child(key).child("email").getValue(String.class);
                                usernameFromDB = snapshot.child(key).child("userName").getValue(String.class);

                                mAuth.signInWithEmailAndPassword(emailFromDB, Password.getText().toString()).addOnCompleteListener(signIn.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "The account is logged in.", Toast.LENGTH_SHORT).show();
                                            updateUI(nameFromDB, usernameFromDB);
                                        } else {
                                            Toast.makeText(signIn.this, "login failed, please create a new account", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

    }

    public void updateUI(String name, String userName) {
        Intent intent = new Intent(signIn.this, timeLinePage.class);
        intent.putExtra("Username", usernameFromDB);
        intent.putExtra("name", nameFromDB);
        startActivity(intent);
    }

//    public void passUserData() {
//        String userUsername = Username.getText().toString().trim();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUserDatabase = reference.orderByChild("userName").equalTo(userUsername);
//        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    String nameFromDB = snapshot.child(userUsername).child("fullName").getValue(String.class);
//                    String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
//                    String usernameFromDB = snapshot.child(userUsername).child("userName").getValue(String.class);
//                    Intent intent = new Intent(signIn.this, Setting.class);
//                    intent.putExtra("name", nameFromDB);
//                    intent.putExtra("email", emailFromDB);
//                    intent.putExtra("Username", usernameFromDB);
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }


    public void MoveToSignUp(View view) {
        Intent signup = new Intent(this, signup.class);
        startActivity(signup, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void MoveToReset(View view) {
        Intent rest = new Intent(this, ForgetPassword.class);
        startActivity(rest, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}