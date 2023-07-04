package com.example.moodtrackerapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    Animation logoAnim, signAnim;
    ImageView logo;
    Button signIn, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.hide();

        logo = findViewById(R.id.logo);
        signIn = findViewById(R.id.signInBtn);
        signUp = findViewById(R.id.signUpBtn);
        logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_anim);
        signAnim = AnimationUtils.loadAnimation(this, R.anim.sign_anim);

        logo.startAnimation(logoAnim);
        signIn.startAnimation(signAnim);
        signUp.startAnimation(signAnim);
    }

    public void signIn(View V){
        Intent i = new Intent(this,signIn.class);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void signUp(View V){
        Intent i = new Intent(this,signup.class);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}