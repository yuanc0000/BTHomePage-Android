package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FaqActivity extends AppCompatActivity {
//faq information displayed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
    }

    public void sendBack1(View view) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

}