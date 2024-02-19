package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ACBTStepFour extends AppCompatActivity {
    //shows step 2 of acbt exericse
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acbtstep_four);
    }
    //button click leads to step 3
    public void sendMessagestepfiveACBT(View view) {
        Intent intent = new Intent(this, ACBTStepFive.class);
        startActivity(intent);
    }


}