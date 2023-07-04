package com.example.moodtrackerapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class insertMoodFace extends AppCompatActivity {
    ActionBar actionBar;
    public SeekBar seekBar;
    ImageView imgV; TextView txt;
    Animation faceAnim, titleAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_mood_face);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        seekBar = findViewById(R.id.seekBar);
        imgV = findViewById(R.id.moodFace);
        txt = findViewById(R.id.moodTxt);

        faceAnim = AnimationUtils.loadAnimation(this, R.anim.faces_anim);
        titleAnim = AnimationUtils.loadAnimation(this, R.anim.title_anim);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    case 0: imgV.setImageResource(R.drawable.excited); txt.setText("excited"); break;
                    case 1: imgV.setImageResource(R.drawable.love); txt.setText("love"); break;
                    case 2: imgV.setImageResource(R.drawable.good); txt.setText("good"); break;
                    case 3: imgV.setImageResource(R.drawable.shy); txt.setText("shy"); break;
                    case 4: imgV.setImageResource(R.drawable.okay); txt.setText("okay"); break;
                    case 5: imgV.setImageResource(R.drawable.lazy); txt.setText("lazy"); break;
                    case 6: imgV.setImageResource(R.drawable.not); txt.setText("not okay"); break;
                    case 7: imgV.setImageResource(R.drawable.sad); txt.setText("sad"); break;
                    case 8: imgV.setImageResource(R.drawable.angry); txt.setText("angry"); break;
                }
                imgV.startAnimation(faceAnim);
                txt.startAnimation(titleAnim);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void contFunc(View v) {
        Intent iGet = getIntent();
        Intent i = new Intent(this,whatsHapening.class);
        if(txt.getText() == "not okay") {
            i.putExtra("moodFace", "not");
            i.putExtra("moodTxt", txt.getText());
        }
        else {
            i.putExtra("moodFace", txt.getText());
            i.putExtra("moodTxt", txt.getText());
        }
        i.putExtra("Username", iGet.getStringExtra("Username"));
        i.putExtra("name", iGet.getStringExtra("name"));
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}