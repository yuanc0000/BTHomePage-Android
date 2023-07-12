package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class AutoDrainage extends AppCompatActivity {
//autogenic drainage exerise page is displayed
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_drainage);

        TextView textView;

        textView = findViewById(R.id.textViewLinkAutogenic);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    //button click leads to step 1
    public void sendMessageAutoSteps(View view) {
        Intent intent = new Intent(this, AutoDrainageStepOne.class);
        startActivity(intent);
    }

}