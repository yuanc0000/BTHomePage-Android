package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class ACBT extends AppCompatActivity {

//    creates acbt screen for exercise
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acbt);

        TextView textView;

        textView = findViewById(R.id.textViewLink);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
//button click leads to first step of exercise
    public void sendMessagesteponeACBT(View view) {
        Intent intent = new Intent(this, ACBTStepOne.class);
        startActivity(intent);
    }
}