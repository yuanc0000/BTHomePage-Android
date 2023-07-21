package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AsthmaDescript extends AppCompatActivity {
//asthma exercise page is displayed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asthma_descript);
    }

    public void BackToDiagnoses(View view) {
        Intent intent = new Intent(this, DiagnosisActivity.class);
        startActivity(intent);
    }

}