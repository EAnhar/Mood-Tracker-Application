package com.example.moodtrackerapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    ActionBar actionBar;
    EditText Email; private String email;
    Button ResetButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        actionBar = getSupportActionBar();
        actionBar.hide();

        Email = findViewById(R.id.email);
        ResetButton = findViewById(R.id.resetButton);

        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = Email.getText().toString();
                if (Email.getText().toString().isEmpty()) {
                    Toast.makeText(ForgetPassword.this, "Please enter your Email", Toast.LENGTH_SHORT).show();
                    Email.setError("Email is require");
                    Email.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(ForgetPassword.this, "Please enter valid Email ", Toast.LENGTH_SHORT).show();
                    Email.setError("Valid Email is require");
                    Email.requestFocus();
                } else {
                    restartPassword(email);
                }

            }
        });
    }

    private void restartPassword(String email) {
        auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgetPassword.this, "Please check your Email to reset the password ", Toast.LENGTH_SHORT).show();
                    Intent Done = new Intent(ForgetPassword.this, signIn.class);
                    Done.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    startActivity(Done);
                    finish();
                } else {
                    Toast.makeText(ForgetPassword.this, "Something wrong ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void MoveToSignUp(View view) {
        Intent signup = new Intent(this, signIn.class);
        startActivity(signup, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}