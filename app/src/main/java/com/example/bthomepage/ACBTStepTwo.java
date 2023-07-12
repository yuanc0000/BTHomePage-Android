package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ACBTStepTwo extends AppCompatActivity {
//shows step 2 of acbt exericse
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acbtstep_two);
    }
    //button click leads to step 3
    public void sendMessagestepthreeACBT(View view) {
        Intent intent = new Intent(this, ACBTStepThree.class);
        startActivity(intent);
    }


}