package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OptionsPV extends AppCompatActivity {
//percussiona and vibrations exercise options displayed and ability to navigate there
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_pv);
    }
    public void sendMessagesChestPerc(View view) {
        Intent intent = new Intent(this, ChestPerc.class);
        startActivity(intent);
    }
    public void sendMessagesBackPerc(View view) {
        Intent intent = new Intent(this, BackPerc.class);
        startActivity(intent);
    }
    public void sendMessagesHighFreq(View view) {
        Intent intent = new Intent(this, HighFreq.class);
        startActivity(intent);
    }
    public void sendMessagesVibration(View view) {
        Intent intent = new Intent(this, Vibration.class);
        startActivity(intent);
    }

    public void sendMessagesExercises(View view) {
        Intent intent = new Intent(this, LungsActivity.class);
        startActivity(intent);
    }
}