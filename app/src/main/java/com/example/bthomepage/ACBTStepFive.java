package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ACBTStepFive extends AppCompatActivity {
    //shows step five of acbt exercise
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acbtstep_five);
    }
    //button click leads to step 6
    public void sendMessagestepsixACBT(View view) {
        Intent intent = new Intent(this, ACBTStepSix.class);
        startActivity(intent);
    }
}


