package com.example.moodtrackerapp.model;

import androidx.appcompat.app.AppCompatActivity;

public class moodModel extends AppCompatActivity {
    private String faceMood, txtMood, note, day, month;
//    public moodModel(faceMood, txtMood, note, day, month)

    public String getFaceMood() {
        return faceMood;
    }

    public void setFaceMood(String faceMood) {
        this.faceMood = faceMood;
    }

    public String getTxtMood() {
        return txtMood;
    }

    public void setTxtMood(String txtMood) {
        this.txtMood = txtMood;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
