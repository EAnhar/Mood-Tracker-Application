package com.example.moodtrackerapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class whatsHapening extends AppCompatActivity {
    ActionBar actionBar; ImageView imgV; TextView txtV;
    Animation iconAnim, faceAnim;
    String face, txt; String[] reasons;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_hapening);
        actionBar = getSupportActionBar();
        assert actionBar != null; actionBar.hide();

        imgV = findViewById(R.id.moodFace2);
        txtV = findViewById(R.id.moodTxt2);

        iconAnim = AnimationUtils.loadAnimation(this, R.anim.iconselect_anim);
        faceAnim = AnimationUtils.loadAnimation(this, R.anim.updownslow_anim);

        Intent i = getIntent(); Bundle extra = i.getExtras();
        face = extra.getString("moodFace"); txt = extra.getString("moodTxt");
        reasons = new String[10];

        Resources res = imgV.getResources();

        int resID = res.getIdentifier(face, "drawable", this.getPackageName());
        imgV.setImageResource(resID); txtV.setText(txt);
        imgV.startAnimation(faceAnim);
    }

    public void insert(View v) {
        Intent iGet = getIntent();
        intent = new Intent(this,note.class);
        intent.putExtra("Username", iGet.getStringExtra("Username"));
        intent.putExtra("name", iGet.getStringExtra("name"));
        intent.putExtra("moodFace", face);
        intent.putExtra("moodTxt", txt);
        String fullReason = "For ";
        for (int i = 0; i<10; i++) {
            if(reasons[i] == "" || reasons[i] == null) continue;
            else fullReason += reasons[i] + ", ";
        }
        intent.putExtra("reasons", fullReason);
        startActivity(intent);
    }
    public void selected(View v) {
        if(v.getAlpha() == 1.0F) {
            v.setAlpha(0.5F);
            for (int i = 0; i<10; i++) {
                if(reasons[i] == v.getTag().toString()) { reasons[i] = ""; break;}
            }
        }
        else {
            v.setAlpha(1.0F);
            v.startAnimation(iconAnim);
            for (int x = 0; x < reasons.length; x++) {
                if(reasons[x] == "" || reasons[x] == null) {
                    reasons[x] = v.getTag().toString(); break;
                }
            }
        }
    }
}