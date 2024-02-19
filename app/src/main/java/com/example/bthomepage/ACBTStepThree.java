package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ACBTStepThree extends AppCompatActivity {
    //shows step one of acbt exercise
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acbtstep_three);
    }
    //button click leads to step 2
    public void sendMessagestepfourACBT(View view) {
        Intent intent = new Intent(this, ACBTStepFour.class);
        startActivity(intent);
    }
}

