package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OptionsDrainPos extends AppCompatActivity {
//different drain position options displayed and ability to navigate to specific exercises
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_drain_pos);
    }
    public void sendMessagesFrontLungs(View view) {
        Intent intent = new Intent(this, FrontLungs.class);
        startActivity(intent);
    }
    public void sendMessagesSideLungs(View view) {
        Intent intent = new Intent(this, SideLungs.class);
        startActivity(intent);
    }
    public void sendMessagesBackLungs(View view) {
        Intent intent = new Intent(this, BackLungs.class);
        startActivity(intent);
    }

    public void sendMessagesExercises(View view) {
        Intent intent = new Intent(this, LungsActivity.class);
        startActivity(intent);
    }


}