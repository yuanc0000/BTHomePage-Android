package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class BackLungs extends AppCompatActivity {
//back lungs exercises is displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_lungs);

        TextView textView;

        textView = findViewById(R.id.textViewLinkAutogenic);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    //button click leads to progress page

    public void sendMessagesProgress(View view) {
        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }
    //button click leads to exercises page

    public void sendMessagesBackDrainPos(View view) {
        Intent intent = new Intent(this, OptionsDrainPos.class);
        startActivity(intent);
    }
}