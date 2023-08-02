package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FrontLungs extends AppCompatActivity {
//front lungs exercise information displayed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_lungs);
    }
    //button click leads to progress page
    public void sendMessagesProgress(View view) {
        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }
    //button click leads to exercise page

    public void sendMessagesBackDrainPos(View view) {
        Intent intent = new Intent(this, OptionsDrainPos.class);
        startActivity(intent);
    }
}