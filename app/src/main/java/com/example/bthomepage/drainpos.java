package com.example.bthomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class drainpos extends AppCompatActivity {
//drain position exercises page is displayed
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drainpos);

        TextView textView;

        textView = findViewById(R.id.textViewLink);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    //button click leads to page for different drain positions

    public void sendMessagesOptionsDrainPos(View view) {
        Intent intent = new Intent(this, OptionsDrainPos.class);
        startActivity(intent);
    }

}