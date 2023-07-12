package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class PV extends AppCompatActivity {
//percussion and vibrations info is displayed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pv);
        TextView textView;

        textView = findViewById(R.id.textViewLinkAutogenic);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    //button click leads to percussions and viibrations options
    public void sendMessagesOptionsPV(View view) {
        Intent intent = new Intent(this, OptionsPV.class);
        startActivity(intent);
    }

}