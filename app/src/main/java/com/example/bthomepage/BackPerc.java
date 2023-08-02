package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BackPerc extends AppCompatActivity {
//back percussion exercise is displayed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_perc);
    }
    //button click leads to progress page

    public void sendMessagesPerc(View view) {
        Intent intent = new Intent(this, OptionsPV.class);
        startActivity(intent);
    }
    //button click leads to exercise page

    public void sendMessagesExercises(View view) {
        Intent intent = new Intent(this, LungsActivity.class);
        startActivity(intent);
    }
}