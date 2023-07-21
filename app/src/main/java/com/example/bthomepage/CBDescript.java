package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CBDescript extends AppCompatActivity {
//chronic bronchitis info page displayed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbdescript);
    }

    public void BackToDiagnoses(View view) {
        Intent intent = new Intent(this, DiagnosisActivity.class);
        startActivity(intent);
    }
}