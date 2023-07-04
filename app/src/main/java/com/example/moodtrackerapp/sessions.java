package com.example.moodtrackerapp;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class sessions extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2;
    MediaPlayer mediaPlayer3;
    MediaPlayer mediaPlayer4;
    MediaPlayer mediaPlayer5;
    MediaPlayer mediaPlayer6;
    MediaPlayer mediaPlayer7;
    MediaPlayer mediaPlayer8;
    MediaPlayer mediaPlayer9;
    MediaPlayer mediaPlayer10;
    MediaPlayer mediaPlayer11;
    MediaPlayer mediaPlayer12;
    MediaPlayer mediaPlayer13;
    MediaPlayer mediaPlayer14;
    MediaPlayer mediaPlayer15;
    ActionBar actionBar;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);
        actionBar = getSupportActionBar();
        actionBar.hide();

        scrollView = findViewById(R.id.scrollVer);
        scrollView.fullScroll(View.DRAG_FLAG_GLOBAL);
        scrollView.setSmoothScrollingEnabled(true);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.relaxsound1);
        mediaPlayer1 = MediaPlayer.create(getApplicationContext(), R.raw.relaxsound2);
        mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.relaxsound3);
        mediaPlayer3 = MediaPlayer.create(getApplicationContext(), R.raw.relaxsound4);
        mediaPlayer4 = MediaPlayer.create(getApplicationContext(), R.raw.sleepsound1);
        mediaPlayer5 = MediaPlayer.create(getApplicationContext(), R.raw.sleepsound2);
        mediaPlayer6 = MediaPlayer.create(getApplicationContext(), R.raw.sleepsound3);
        mediaPlayer7 = MediaPlayer.create(getApplicationContext(), R.raw.sleepsound4);
        mediaPlayer8 = MediaPlayer.create(getApplicationContext(), R.raw.rainsound1);
        mediaPlayer9 = MediaPlayer.create(getApplicationContext(), R.raw.rainsound2);
        mediaPlayer10 = MediaPlayer.create(getApplicationContext(), R.raw.rainsound3);
        mediaPlayer11 = MediaPlayer.create(getApplicationContext(), R.raw.rainsound4);
        mediaPlayer12 = MediaPlayer.create(getApplicationContext(), R.raw.waterfallsound1);
        mediaPlayer13 = MediaPlayer.create(getApplicationContext(), R.raw.waterfallsound2);
        mediaPlayer14 = MediaPlayer.create(getApplicationContext(), R.raw.waterfallsound3);
        mediaPlayer15 = MediaPlayer.create(getApplicationContext(), R.raw.waterfallsound4);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);
    }

    public void playptn(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn");
            mediaPlayer.stop();
        }
    }

    public void playptn1(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn1")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer1.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn1");
            mediaPlayer1.stop();
        }
    }

    public void playptn2(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn2")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer2.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn2");
            mediaPlayer2.stop();
        }
    }

    public void playptn3(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn3")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer3.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn3");
            mediaPlayer3.stop();
        }
    }

    public void playptn4(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn4")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer4.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn4");
            mediaPlayer4.stop();
        }
    }

    public void playptn5(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn5")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer5.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn5");
            mediaPlayer5.stop();
        }
    }

    public void playptn6(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn6")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer6.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn6");
            mediaPlayer6.stop();
        }
    }

    public void playptn7(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn7")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer7.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn7");
            mediaPlayer7.stop();
        }
    }

    public void playptn8(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn8")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer8.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn8");
            mediaPlayer8.stop();
        }
    }

    public void playptn9(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn9")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer9.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn9");
            mediaPlayer9.stop();
        }
    }

    public void playptn10(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn10")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer10.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn10");
            mediaPlayer10.stop();
        }
    }

    public void playptn11(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn11")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer11.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn11");
            mediaPlayer11.stop();
        }
    }

    public void playptn12(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn12")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer12.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn12");
            mediaPlayer12.stop();
        }
    }

    public void playptn13(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn13")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer13.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn13");
            mediaPlayer13.stop();
        }
    }

    public void playptn14(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn14")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer14.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn14");
            mediaPlayer14.stop();
        }
    }

    public void playptn15(View v) {
        ImageView btn = (ImageView) v;
        if (btn.getTag().toString().equals("playbtn15")) {
            btn.setImageResource(R.drawable.stopbtn);
            btn.setTag("stopbtn");
            mediaPlayer15.start();
        } else {
            btn.setImageResource(R.drawable.play_btn);
            btn.setTag("playbtn15");
            mediaPlayer15.stop();
        }
    }

    public void goSetting(View v) {
        Intent iGet = getIntent();
        Intent i = new Intent(getApplicationContext(), Setting.class);
        i.putExtra("Username", iGet.getStringExtra("Username"));
        i.putExtra("name", iGet.getStringExtra("name"));
        startActivity(i);
    }

    public void goTimeLine(View v) {
        Intent iGet = getIntent();
        Intent i = new Intent(getApplicationContext(), timeLinePage.class);
        i.putExtra("Username", iGet.getStringExtra("Username"));
        i.putExtra("name", iGet.getStringExtra("name"));
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}

