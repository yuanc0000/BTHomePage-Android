package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AutoDrainageStepOne extends AppCompatActivity {
//step 1 of autogenic exercises is displayed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_drainage_step_one);
    }
    //button click leads to step 2
    public void sendMessagesAutoStepTwo(View view) {
        Intent intent = new Intent(this, AutoDrainageStepTwo.class);
        startActivity(intent);
    }

}